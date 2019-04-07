import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel {
	// Properties for game panel
	private final static int BORDER_X = 800;
	private final static int BORDER_Y = 800;
	private final Dimension BORDERS = new Dimension(BORDER_X, BORDER_Y);
	public final static int GRID_SIZE = BORDER_X / 40;
	
	// Properties for snake
	private final int SNAKE_X_START = 400;
	private final int SNAKE_Y_START = 400;
	private final int SNAKE_LENGTH = 10;
	private KeyboardListener listener = new KeyboardListener();
	private Snake snake;
	private Food food;
	
	// General game settings
	private boolean gameOver;
	private long gameSpeed = 150;
	private long maxSpeed = 5;
	private boolean initiatedDirection = false;
	
	// Game-over screen
	private ImageIcon endScreenImage = new ImageIcon("gameover.jpg");
	private Image endScreen = endScreenImage.getImage();

	// Constructor
	public Game(KeyboardListener k) {
		listener = k;
		initiateComponents();
	}

	// Creates main frame and initiates objects
	private void initiateComponents() {
		// Create panel within window
		setBackground(Color.BLACK);
		setFocusable(true);
		setPreferredSize(BORDERS);

		// Create objects
		snake = new Snake(SNAKE_X_START, SNAKE_Y_START, SNAKE_LENGTH, listener, GRID_SIZE, this);
		food = new Food(RandomGenerator.foodPosition(BORDER_X, GRID_SIZE),
				RandomGenerator.foodPosition(BORDER_Y, GRID_SIZE), GRID_SIZE);
		gameOver = false;
	}

	// GAME LOOP
	public void run() {
		drawing();
		while (!gameOver) {
			updateGame();
			drawing();
			
			// Sets starting direction to snake
			if (!initiatedDirection) {
				listener.initiateDirection();
				initiatedDirection = true;
			}
		}
	}

	// Update snake movement and check for self collision
	private void updateGame() {
		snake.update();
		if (snake.selfCollision()) {
			gameOver = true;
		} else {
			constrainPosition();
			snakeFoodCollision(snake, food);
		}

		// Speed between each frame regulated by gameSpeed (lower value = faster movement)
		try {
			Thread.sleep(gameSpeed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Wrap around edges if they are hit
	private void constrainPosition() {
		if (snake.getPositionX() > BORDER_X - GRID_SIZE) {
			snake.setPositionX(0 - GRID_SIZE);
		} else if (snake.getPositionX() < 0) {
			snake.setPositionX(BORDER_X);
		} else if (snake.getPositionY() > BORDER_Y) {
			snake.setPositionY(0 - GRID_SIZE);
		} else if (snake.getPositionY() < 0) {
			snake.setPositionY(BORDER_Y);
		}
	}

	// Automatically calls paintComponent() from JComponent
	private void drawing() {
		repaint();
	}
	
	// Main method to paint components
	@Override
	public void paintComponent(Graphics g) {
		if(!gameOver) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		food.draw(g2);
		snake.draw(g2);
		}
		else {
			g.drawImage(endScreen, 0, 0, BORDER_X, BORDER_Y, this);
		}
	}
	
	// Called every time the snake eats food. Lower value = faster
	private void increaseGameSpeed() {
		if (gameSpeed > maxSpeed) {
			gameSpeed -= 5;
		}
	}
	
	// Collision detection for snake head and food. 
	// If eaten, snake increases in size and game speeds up
	// lastly picks a new location for food
	private void snakeFoodCollision(Snake snakeEntity, Food foodEntity) {
		if (snakeEntity.getPositionX() == foodEntity.getPositionX()
				&& snakeEntity.getPositionY() == foodEntity.getPositionY()) {
			snakeEntity.grow();
			increaseGameSpeed();
			relocateFood(snakeEntity, foodEntity);
		}
	}
	
	// Spawns a piece of food at a random location
	// Will only accept a coordinate not currently occupied by snake
	private void relocateFood(Snake snakeEntity, Food foodEntity) {
		foodEntity.initiated = false;
		int food_x = RandomGenerator.foodPosition(BORDER_X, GRID_SIZE);
		int food_y = RandomGenerator.foodPosition(BORDER_Y, GRID_SIZE);
		int[] snake_x = snakeEntity.getPosXArray();
		int[] snake_y = snakeEntity.getPosYArray();
		while (!foodEntity.initiated) {
			for (int i = 0; i < snakeEntity.getLength(); i++) {
				if (snake_x[i] == food_x && snake_y[i] == food_y) {
					food_x = RandomGenerator.foodPosition(BORDER_X, GRID_SIZE);
					food_y = RandomGenerator.foodPosition(BORDER_Y, GRID_SIZE);
					if (snake_x[i] != food_x && snake_y[i] != food_y) {
						foodEntity.initiated = true;
					}
				} else {
					foodEntity.initiated = true;
				}
			}
		}
		foodEntity.setPositionX(food_x);
		foodEntity.setPositionY(food_y);
	}
}