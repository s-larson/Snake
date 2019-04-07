import java.awt.Color;
import java.awt.Graphics2D;

// Subclass of GridEntity
public class Food extends GridEntity {
	
	// Constructor
	public Food(int x_start, int y_start, int scale) {
		super(x_start, y_start, scale);
		initiated = true;
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

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillOval(position_x[0], position_y[0], mScale / 2, mScale / 2);

	}
}
