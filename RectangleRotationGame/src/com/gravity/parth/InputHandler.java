package com.gravity.parth;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseMotionListener {

	private static double theta = 0;
	private static int mouseX, mouseY;

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		double _theta = Math.toDegrees(Math.atan2(
				getMousePointRelativeToOrigin().x,
				getMousePointRelativeToOrigin().y));

		theta = -_theta + 90;
		Panel.paddle.move(theta);
		Panel.theta = (int) (-theta + 90);
	}

	public static double getMouseTheta() {
		return theta;
	}

	public static Point getMousePointRelativeToTopLeft() {
		return new Point(mouseX, mouseY);
	}

	public Point getMousePointRelativeToOrigin() {
		return new Point(mouseX - Frame.origin.x, mouseY - Frame.origin.y);
	}

}
