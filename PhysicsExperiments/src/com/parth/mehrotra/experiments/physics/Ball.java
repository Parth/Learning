package com.parth.mehrotra.experiments.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ball {

	private int radius;
	private double x, y;
	private ArrayList<Force> allTheForcesActingOnBall;

	private Force gravity;
	private Force fnet;
	private double mass = 100;
	private double aX, aY;
	private Force bounce;
	private boolean bounceComplete = false;
	private double yVel;

	public Ball() {
		x = Frame.W / 2;
		y = 0;
		radius = 25;
		allTheForcesActingOnBall = new ArrayList<Force>();
		gravity = new Force(0.5, 90, null);
		bounce = new Force(1, 270, null);

		allTheForcesActingOnBall.add(gravity);
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval((int) (x - radius), (int) (y - radius), radius * 2,
				radius * 2);
	}

	public void physics() {
		checkBounces();
		applyForces();
	}

	public void applyForces() {
		Force fNet = getfNet(); // a = fNet / mass
		aY = fNet.getYComponent() / 5; // d = 1/2*a*^2

		yVel = 0.5 * aY * Math.pow(Frame.clock, 2);
		
		y+= yVel;
		
		System.out.println("A: "+aY+" Y: " + y);
	}

	private Force getfNet() {

		double fNetX = 0;
		double fNetY = 0;

		for (int i = 0; i < allTheForcesActingOnBall.size(); i++) {
			fNetX += allTheForcesActingOnBall.get(i).getXComponent();
			fNetY += allTheForcesActingOnBall.get(i).getYComponent();
		}

		Force f = new Force(fNetX, fNetY);


		return f;
	}

	private void bounceFloor() {
		allTheForcesActingOnBall.add(bounce);
	}

	private void postBounceFloor() {
		allTheForcesActingOnBall.remove(bounce);
			bounceComplete = false;
	}

	private void checkBounces() {
		if (y + radius > Frame.H) {
			
				bounceFloor();
			
		} else {
			// hasBounced = false;
			// postBounceFloor();
			postBounceFloor();
		}
	}
}
