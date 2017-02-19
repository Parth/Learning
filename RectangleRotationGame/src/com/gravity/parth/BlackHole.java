package com.gravity.parth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlackHole {

	private int x, y, rad;
	private Image holeImage;

	public BlackHole() {
		x = Frame.origin.x;
		y = Frame.origin.y;

		rad = 80;
		try {
			holeImage = ImageIO.read(new File("res/blackhole.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(holeImage, x - rad, y - rad, rad * 2, rad * 2, null);
		g.setColor(Color.RED);
		g.fillPolygon(collisionZone(Panel.ball));
	}

	public Point getPointClosestToBallRelativeToOrigin(Ball ball) {
		double radians = ball.getRadRelativeToCenter();
		double relX = Math.cos(radians) * rad;
		double relY = Math.sin(radians) * rad;

		return new Point((int) relX, (int) relY);
	}

	public Point getPointClosestToBallRelativeToTopLeft(Ball ball) {
		int x = getPointClosestToBallRelativeToOrigin(ball).x;
		int y = getPointClosestToBallRelativeToOrigin(ball).y;

		return new Point(x + Frame.origin.x, y + Frame.origin.y);
	}

	public Polygon collisionZone(Ball ball) {
		int n = 4;
		int[] xPoints = new int[n];
		int[] yPoints = new int[n];

		double[] degrees = new double[n];

		degrees[0] = ball.getDegRelativeToCenter();
		degrees[1] = 180 - ball.getDegRelativeToCenter();
		degrees[2] = -degrees[1];
		degrees[3] = -degrees[0];

		for (int i = 0; i < n; i++) {
			xPoints[i] = Frame.translateXCoordinateFromOrigin((int) (Math
					.cos(Math.toRadians(degrees[i])) * rad));
			yPoints[i] = Frame.translateYCoordinateFromOrigin((int) (Math
					.sin(Math.toRadians(degrees[i])) * rad));
		}
		return new Polygon(xPoints, yPoints, n);
	}
}