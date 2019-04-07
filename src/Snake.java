import java.awt.Color;
import java.awt.Graphics2D;

// Subclass of GridEntity
public class Snake extends GridEntity {

	// Constructor
	public Snake(int x_start, int y_start, int start_length, KeyboardListener kbl, int scale, Game game) {
		super(x_start, y_start, start_length, kbl, scale);
		createBody();
	}

	// Initiate coordinates for all body parts
	private void createBody() {
		for (int i = 0; i <= length; i++) {
			// Same X-coordinate as previous position in array, minus grid size
			position_x[i + 1] = position_x[i] - mScale;
			// Whole body gets same Y-coordinate as head
			position_y[i] = position_y[0];
		}
	}

	// Draw snake
	public void draw(Graphics2D g2) {
		for (int i = 0; i <= length; i++) {
			g2.setColor(Color.GREEN);
			g2.fillRect(position_x[i], position_y[i], mScale / 2, mScale / 2);
		}
	}

	// Primary method called by Game to update position
	// First, listen to key press, then update direction. Lastly update position
	public void update() {
		int[] direction = listener.getSnakeDirection();
		this.x_dir = direction[0];
		this.y_dir = direction[1];

		// Move head
		this.position_x[0] += x_dir * mScale;
		this.position_y[0] += y_dir * mScale;

		// Move body relative to head
		for (int i = length; i > 0; i--) {
			this.position_x[i] = this.position_x[i - 1];
			this.position_y[i] = this.position_y[i - 1];
		}
		// Flag for Keylistener an input has been made to avoid multiple inputs
		listener.registerInput();
	}

	// Checks if head has collided with any part of the body
	public boolean selfCollision() {
		boolean gameOver = false;
		for (int i = 2; i <= length; i++) {
			if (position_x[0] == position_x[i] && position_y[0] == position_y[i]) {
				gameOver = true;
			}
		}
		return gameOver;
	}

	// Called after eating food
	public void grow() {
		length++;
		position_x[length] = position_x[length - 1];
		position_y[length] = position_y[length - 1];
	}

	// Inherited from super class
	@Override
	public int getPositionX() {
		return super.position_x[0];
	}

	@Override
	public int getPositionY() {
		return super.position_y[0];
	}

	@Override
	public int[] getPosXArray() {
		return super.position_x;
	}

	@Override
	public int[] getPosYArray() {
		return super.position_y;
	}

	@Override
	public void setPositionX(int x_pos) {
		this.position_x[0] = x_pos;
	}

	@Override
	public void setPositionY(int y_pos) {
		this.position_y[0] = y_pos;
	}

	public int getLength() {
		return super.length;
	}
}
