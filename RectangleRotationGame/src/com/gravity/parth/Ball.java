package com.gravity.parth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Ball {

	Polygon ball;
	private int x = 400, y = 25, rad;
	private Color color;
	private double velocityX = 20;
	private double velocityY = 0;

	private final double GRAVITY = 15;
	private final double ENERGY_LOSS = 0.65;
	private final double DT = 0.2;
	private final double FRICTION = 0.9;

	public Ball(int rad, Color color) {
		this.rad = rad;
		this.color = color;
		x = 0;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - rad, y - rad, rad * 2, rad * 2);
	}

	public void fall() {
		if (x + velocityX > Frame.W - rad - 1) {
			x = Frame.W - rad - 1;
			velocityX = -velocityX;
		} else if (x + velocityX < 0 + rad) {
			x = 0 + rad;
			velocityX = -velocityX;
		} else {
			x += velocityX;
		}

		if (y == Frame.H - rad - 1) {
			velocityX *= FRICTION;
			if (Math.abs(velocityX) < 0.8) {
				velocityX = 0;
			}
		}

		if (y > Frame.H - rad - 1) {
			y = Frame.H - rad - 1;
			velocityY *= ENERGY_LOSS;
			velocityY = -velocityY;

		} else {
			velocityY = velocityY + GRAVITY * DT;
			y += velocityY * DT + 0.5 * GRAVITY * DT * DT;
		}
	}

	private double xVel = 2.0, yVel = 2.0;

	public void move() {
		x += xVel;
		y += yVel;

		if (x >= Frame.W || x <= 0) {
			linearBounceX();
		}
		if (y >= Frame.H || y <= 0) {
			linearBounceY();
		}
	}
	
	public void linearBounceX() {
		xVel = -xVel;
	}
	
	public void linearBounceY() {
		yVel = -yVel;
	}
	
	public void linearBounceXY() {
		xVel = -xVel;
		yVel = -yVel;
	}

	public void setPosition(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public double getSlopeOfTrajectory() {
		return (double) yVel / (double) xVel;
	}
	

	private static final int distanceFromOrigin = 85;

	public void moveInACircle(double deg) {
		double coorX = (Math.cos(Math.toRadians(deg)) * rad) + Frame.origin.x
				+ (Math.cos(Math.toRadians(deg)) * distanceFromOrigin);
		double coorY = (Math.sin(Math.toRadians(deg)) * rad) + Frame.origin.y
				+ (Math.sin(Math.toRadians(deg)) * distanceFromOrigin);

		x = (int) coorX;
		y = (int) coorY;

	}

	public double getDegRelativeToCenter() {
		int coorX = x - Frame.origin.x;
		int coorY = y - Frame.origin.y;

		return Math.toDegrees(Math.atan2(coorY, coorX));
	}

	public double getRadRelativeToCenter() {
		int coorX = x - Frame.origin.x;
		int coorY = y - Frame.origin.y;

		return Math.atan2(coorY, coorX);
	}

	public Point getPointOnBallClosestToBlackHole() {
		double radians = (getRadRelativeToCenter());

		double relX = -Math.cos(radians) * rad;
		double relY = -Math.sin(radians) * rad;

		return new Point((int) relX, (int) relY);
	}

	public Point getPointClosestToBlackHole() {
		Point onBall = getPointOnBallClosestToBlackHole();
		return new Point(onBall.x + x, onBall.y + y);
	}

	public Point getOnscreenPoint() {
		return new Point(x, y);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void stop() {
		velocityX = 0;
		velocityY = 0;

		xVel = yVel = 0;
	}
}
