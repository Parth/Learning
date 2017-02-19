package com.parth.mehrotra.experiments.physics;

import java.awt.Point;

/*
 * Vectors: fully described by magnitude and direction
 * Scalar: described by magnitude alone
 */

public class Force {

	private double magnitude;
	private double degrees;
	private boolean enabled;
	
	private double xComponent;
	private double yComponent;

	public Force(double magnitude, double degrees, Object placeHolder) {
		this.magnitude = magnitude;
		this.degrees = degrees;
		enabled = true;
	}
	
	public Force(double xComponent, double yComponent) {
		this.magnitude = (Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2)));
		this.degrees = Math.toDegrees(Math.atan2(yComponent, xComponent));
		enabled = true;
	}
	
	public double getXComponent() {
		return Math.cos(Math.toRadians(degrees)) * magnitude;
	}
	
	public double getYComponent() {
		return Math.sin(Math.toRadians(degrees)) * magnitude;
	}
	
	public String toString() {
		return (getXComponent()+", "+getYComponent());
		
	}
}
