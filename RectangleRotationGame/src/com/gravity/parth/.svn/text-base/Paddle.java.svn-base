package com.gravity.parth;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Paddle {

	private int startingTheta, startingThetaSuplement;
	private double[] theta_D = new double[4];
	private double[] theta_R = new double[4];
	private double diagonal;
	private final double RAD = 100;

	private int[] xPoints = new int[4];
	private int[] yPoints = new int[4];
	private final int nPOINTS = 4;

	private int width, height;
	private Polygon paddle;
	private Color color;

	public Paddle(int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;

		Point origin = new Point(Frame.W / 2, Frame.H / 2);
		diagonal = Math.sqrt(Math.pow(width / 2, 2)
				+ Math.pow(height / 2, 2));

		startingTheta = (int) Math.toDegrees(Math.atan2((height), (width)));
		startingThetaSuplement = 180 - startingTheta;

		theta_D[0] = startingTheta;
		theta_D[1] = startingThetaSuplement;
		theta_D[2] = theta_D[1] + 180;
		theta_D[3] = theta_D[2] + 180;

		for (int i = 0; i < nPOINTS; i++) {
			theta_R[i] = Math.toRadians(theta_D[i]);
		}

		for (int i = 0; i < nPOINTS; i++) {
			xPoints[i] = ((int) (Math.cos(theta_R[i]) * diagonal)) + origin.x;
			yPoints[i] = ((int) (Math.sin(theta_R[i]) * diagonal)) + origin.y;
		}

		paddle = new Polygon(xPoints, yPoints, nPOINTS);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillPolygon(paddle);
	}
	
	public void move (double theta) {
		
		rotate(theta);
		
		for (int i = 0; i < nPOINTS; i++) {
			paddle.xpoints[i] +=(int) (Math.cos(Math.toRadians(theta)) * RAD);
			paddle.ypoints[i] += (int) (Math.sin(Math.toRadians(theta)) * RAD);
		}
		
		paddle.invalidate(); 
	}

	public void rotate(double d) {
		theta_D[0] = d + (startingTheta);
		theta_D[1] = d + (startingThetaSuplement);
		theta_D[2] = d + (startingTheta + 180);
		theta_D[3] = d + (startingThetaSuplement + 180);

		for (int i = 0; i < nPOINTS; i++) {
			theta_R[i] = Math.toRadians(theta_D[i]);
		}

		// Angle in degrees
		Point origin = new Point(Frame.W / 2, Frame.H / 2);
		double diagonal = Math.sqrt(Math.pow(width / 2, 2)
				+ Math.pow(height / 2, 2));

		for (int i = 0; i < nPOINTS; i++) {
			paddle.xpoints[i] = ((int) (Math.cos(theta_R[i]) * diagonal))
					+ origin.x;
			paddle.ypoints[i] = ((int) (Math.sin(theta_R[i]) * diagonal))
					+ origin.y;
		}

		paddle.invalidate();
	}
	
	public Point getCenter() {
		
		int xCenter = (paddle.xpoints[0] + paddle.xpoints[1] / 2) + Frame.origin.x;
		int yCenter = (paddle.ypoints[0] + paddle.ypoints[1] / 2) + Frame.origin.y;
		
		return new Point(xCenter, yCenter);
	}

	public String toString() {
		String a = "a: (" + paddle.xpoints[0] + ", " + paddle.ypoints[0] + ")";
		String b = "b: (" + paddle.xpoints[1] + ", " + paddle.ypoints[1] + ")";
		String c = "c: (" + paddle.xpoints[2] + ", " + paddle.ypoints[2] + ")";
		String d = "d: (" + paddle.xpoints[3] + ", " + paddle.ypoints[3] + ")";

		return a + "\n" + b + "\n" + c + "\n" + d;
	}
	
	public Polygon getPaddle() {
		return paddle;
	}
}
