package com.gravity.parth;

public class CheckCollisions {
	
	Ball ball;
	Paddle paddle;
	BlackHole hole;
	
	public CheckCollisions() {
		ball = Panel.ball;
		paddle = Panel.paddle;
		hole = Panel.hole;
	}
	
	public CheckCollisions(Ball ball, Paddle paddle, BlackHole hole) {
		this.ball = ball;
		this.paddle = paddle;
		this.hole = hole;
	}
	
	public boolean gameOver() {
		return ballIsInHole();
	}
	
	public boolean ballHitPaddle() {
		return ballPaddle();
	}
	
	private boolean ballIsInHole() {
		if (hole.collisionZone(ball).contains(ball.getPointClosestToBlackHole())) return true;
		else return false;
	}

	private boolean ballPaddle() {
		if (paddle.getPaddle().contains(ball.getPointClosestToBlackHole())) return true;
		else return false;
	}
}
