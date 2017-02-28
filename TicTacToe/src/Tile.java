import java.awt.Graphics;
import java.awt.Color;

public class Tile {

	private int state;
	public static final int notSelected = 0;
	public static final int xSelected = 1;
	public static final int oSelected = 2;

	private int x, y;
	private int padding = 25;
	private int size;
	
	private Color color;

	public static final Color victory = Color.RED;
	public static final Color normal = Color.WHITE;

	private boolean hover = false;

	public Tile(int x, int y) {
		state = notSelected;
		this.x = x;
		this.y = y;
		size = TicTacToeRunner.WIDTH / 3;
		color = normal;
	}

	public boolean setState(int state) {
		if (this.state == notSelected) {
			this.state = state;
			return true;
		} else {
			return false;
		}
	}

	public void victory() {
		color = victory;
	}

	public int getState() {
		return state;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		if (hover) {
			//TODO later
		}
		if (state == notSelected) {
			return;
		} else if (state == xSelected) {
			g.drawLine(x+padding, y+padding, x+size-padding, y+size-padding);
			g.drawLine(x+size-padding, y+padding, x+padding, y+size-padding);
		} else if (state == oSelected) {
			g.drawOval(x+(padding/2), y+(padding/2), size - padding, size-padding);
		}
		
	}

	public String toString() {
		if (getState() == notSelected) {
			return "N";
		} else if (getState() == xSelected) {
			return "X";
		} else {
			return "O";
		}

	}

	public void setHover(boolean h) {
		hover = h;
	}

	@Override
	public boolean equals(Object obj) {
		Tile t = (Tile) obj;
		if (t.getState() == notSelected || this.getState() == notSelected) 
			return false;
		else if (t.getState() == this.getState()) {
			return true;
		} else {
			return false;
		}
	}
}
