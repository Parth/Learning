package com.parth.mehrotra.experiments.physics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
	Ball ball;

	public Panel() {
		ball = new Ball();
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, Frame.W, Frame.H);
		ball.draw(g);
	}

	@Override
	public void run() {
		while (true) {
			tick();
			render();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void tick() {
		Frame.clock+= .01;
		ball.physics();
	}

	private void render() {
		repaint();
	}
}
