package com.parth.mehrotra.experiments.physics;

import java.awt.Point;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public static final int W = 800;
	public static final int H = 600;
	private final String TITLE = "Some Physics Experiments";
	public static double clock = 0.0;

	public Frame() {

		this.setTitle(TITLE);
		this.setSize(W, H);
		this.setLocationRelativeTo(null); //XXX for debug only
		
		this.add(new Panel());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		JFrame frame = new Frame();
	}
}
