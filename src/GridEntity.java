import java.awt.Graphics2D;

import javax.swing.JPanel;

public abstract class GridEntity extends JPanel {
	// Arrays for determining position
	// Position in index 0 for snake are always the heads' position
	protected int[] position_x = new int[800];
	protected int[] position_y = new int[800];
	// Direction. Set by KeyboardListener class
	protected int x_dir;
	protected int y_dir;

	protected int length;
	protected int mScale;
	protected KeyboardListener listener;

	protected boolean initiated;

	// Constructor for Snake
	public GridEntity(int x_start, int y_start, int start_length, KeyboardListener kbl, int scale) {
		this.position_x[0] = x_start;
		this.position_y[0] = y_start;
		this.length = start_length;
		this.listener = kbl;
		this.mScale = scale;
		this.setFocusable(true);
	}

	// Constructor for Food
	public GridEntity(int x_start, int y_start, int scale) {
		this.position_x[0] = x_start;
		this.position_y[0] = y_start;
		this.mScale = scale;
	}

	// Abstract set/get methods
	protected abstract int getPositionX();
	protected abstract int getPositionY();
	protected abstract int[] getPosXArray();
	protected abstract int[] getPosYArray();
	protected abstract void setPositionX(int pos_x);
	protected abstract void setPositionY(int pos_y);
	protected abstract void draw(Graphics2D g2);
}
