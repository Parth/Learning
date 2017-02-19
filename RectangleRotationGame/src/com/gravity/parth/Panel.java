package com.gravity.parth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread th;
	public static int theta = 0;

	public static Paddle paddle;
	public static Ball ball;
	public static BlackHole hole;

	private CheckCollisions checkCollisions;

	private Image i;
	private Graphics doubleBuffer;

	boolean gameIsInSession = true;

	public Panel() {
		paddle = new Paddle(25, 100, Color.blue);
		ball = new Ball(25, Color.WHITE);
		hole = new BlackHole();
		checkCollisions = new CheckCollisions(ball, paddle, hole);

		th = new Thread(this);
		th.start();
		paddle.move(theta);
		this.setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (gameIsInSession) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Frame.W, Frame.H);
			ball.draw(g);
			paddle.draw(g);
			hole.draw(g);
		} else {

		}
	}

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(Frame.W, Frame.H);
			doubleBuffer = i.getGraphics();
		}

		doubleBuffer.setColor(getBackground());
		doubleBuffer.fillRect(0, 0, Frame.W, Frame.H);

		doubleBuffer.setColor(getForeground());
		paint(doubleBuffer);

		g.drawImage(i, 0, 0, this);
	}

	@Override
	public void run() {

		while (true) {
			gameLoop();
			tick();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void gameLoop() {
		if (gameIsInSession) {
			ball.move();
			if (checkCollisions.gameOver())
				gameOver();
			if (checkCollisions.ballHitPaddle())
				ball.linearBounceXY();
		}
		repaint();
	}

	public void tick() {
	}

	public void drawGuideLines(int numberOfXLines, int numberOfYLines,
			Color color, Graphics g) {

		int nX = numberOfXLines + 1;
		int nY = numberOfYLines + 1;

		g.setColor(color);

		for (int x = 0; x < nX; x++) {
			g.drawLine((Frame.W / nX) + (x * (Frame.W / nX)), 0, (Frame.W / nX)
					+ (x * (Frame.W / nX)), Frame.H);
		}

		for (int y = 0; y < nY; y++) {
			g.drawLine(0, (Frame.H / nY) + (y * (Frame.H / nY)), Frame.W,
					(Frame.H / nY) + (y * (Frame.H / nY)));
		}
	}

	public void drawDebugComponents(Graphics g) {
		g.drawOval(Frame.W / 2 - 200 / 2, Frame.H / 2 - 200 / 2, 200, 200);
		drawGuideLines(5, 5, Color.white, g);
	}

	public void gameOver() {
		ball.stop();
	}
}
