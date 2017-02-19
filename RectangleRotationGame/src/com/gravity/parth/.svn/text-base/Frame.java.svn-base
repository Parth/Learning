package com.gravity.parth;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

public class Frame {

	public static InputHandler inputHandler;

	public static final int W = 800, H = 600;
	private static final int padding = 20;
	public static final Point origin = new Point(W / 2, H / 2);

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(W, H);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Rectangle Rotation");
		frame.setResizable(false);
		inputHandler = new InputHandler();
		frame.addMouseMotionListener(inputHandler);
		frame.add(new Panel());
		frame.setVisible(true);

		frame.setBackground(Color.BLACK);
	}
	
	public static Point translateFromOrigin(Point p) {
		return new Point(p.x + origin.x, p.y + origin.y);
	}
	
	public static int translateXCoordinateFromOrigin(int x) {
		return x + origin.x;
	}
	
	public static int translateYCoordinateFromOrigin(int y) {
		return y + origin.y;
	}
}
