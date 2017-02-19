import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class StartingPoint extends JFrame implements Runnable {

	int x = 0;
	int y = 25;

	double dx = 20;
	double dy = 0;

	int radius = 20;

	private Image i;
	private Graphics doubleG;

	double gravity = 15;
	double energyloss = 0.65;
	double dt = 0.2;
	double xFriction = 0.9;

	public static int WIDTH = 800, HEIGHT = 600;

	public StartingPoint() {

		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Physics Demos");
		Thread thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		new StartingPoint();

	}

	@Override
	public void run() {
		while (true) {
			if (x + dx > WIDTH - radius - 1) {
				x = WIDTH - radius - 1;
				dx = -dx;
			} else if (x + dx < 0 + radius) {
				x = 0 + radius;
				dx = -dx;
			} else {
				x += dx;
			}

			if (y == HEIGHT - radius - 1) {
				dx *= xFriction;
				if (Math.abs(dx) < 0.8) {
					dx = 0;
				}
			}

			if (y > HEIGHT - radius - 1) {
				y = HEIGHT - radius - 1;
				dy *= energyloss;
				dy = -dy;

			} else {
				dy = dy + gravity * dt;
				y += dy * dt + 0.5 * gravity * dt * dt;
			}

			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(WIDTH, HEIGHT);
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.green);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

	}
}
