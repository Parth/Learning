package com.mime.minefront.graphics;

import java.util.Random;

import com.mime.minefront.Game;

public class Screen extends Render {

	private Render test;
	private Render3D render;

	public Screen(int width, int height) {
		super(width, height);

		Random random = new Random();

		test = new Render(256, 256);
		render = new Render3D(width, height);

		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt() * (random.nextInt(5)/4);
		}
	}

	public void render(Game game) {

		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}

//		for (int i = 0; i < 50; i++) {
//			
//			//int anim0 = (int) (Math.sin(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 100);
//			
//			int changeInX = (int) (Math.sin((game.time + i * 2) % 1000.0 / 100) * 100);
//			int changeInY = (int) (Math.cos((game.time + i * 2) % 1000.0 / 100) * 100);
//
//			draw(test, (width - 256) / 2 + changeInX, (height - 256) / 2
//					+ changeInY);
//		}
		
		render.floor(game);
		render.renderDistanceLimiter();
		render.renderWall(0, 0.5, 1.5, 0);
		draw(render, 0, 0);
	}
}
