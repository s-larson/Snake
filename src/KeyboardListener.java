import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	private int x_dir = 0;
	private int y_dir = 0;
	public boolean registerInput;

	@Override
	public void keyPressed(KeyEvent e) {
		if (!registerInput) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && x_dir != -1) {
				x_dir = 1;
				y_dir = 0;
				registerInput = true;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT && x_dir != 1) {
				x_dir = -1;
				y_dir = 0;
				registerInput = true;
			} else if (e.getKeyCode() == KeyEvent.VK_UP && y_dir != 1) {
				x_dir = 0;
				y_dir = -1;
				registerInput = true;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN && y_dir != -1) {
				x_dir = 0;
				y_dir = 1;
				registerInput = true;
			}
		}
	}

	// Called by snake when updating movement
	public int[] getSnakeDirection() {
		int[] direction = new int[] { x_dir, y_dir };
		return direction;
	}
	
	// Resets boolean to take new keyboard inputs
	public void registerInput() {
		registerInput = false;
	}
	
	// Gives a direction when starting the game
	public void initiateDirection() {
		x_dir = 1;
	}

	// Inherited methods from KeyListener. Not used
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
