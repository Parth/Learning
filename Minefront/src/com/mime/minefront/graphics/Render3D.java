package com.mime.minefront.graphics;

import java.util.Random;

import com.mime.minefront.Game;
import com.mime.minefront.input.Controller;

public class Render3D extends Render {

	public double[] zBuffer;
	public double renderDistance = 5000;
	private double foreward, right, cosine, sine;
	private double up;

	public Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];
	}

	public void floor(Game game) {

		double floorPosition = 8;
		double ceilingPosition = 8;

		foreward = game.controls.z;// game.time % 100 / 20.0;
		right = game.controls.x;
		up = game.controls.y;
		double walking = Math.sin(game.time / 6.0) * 0.5;

		if (Controller.crouchWalk) {
			walking = Math.sin(game.time / 6.0) * 0.25;
		}
		if (Controller.runWalk) {
			walking = Math.sin(game.time / 4.0) * 0.8;
		}

		double rotation = game.controls.rotation;
		cosine = Math.cos(rotation);
		sine = Math.sin(rotation);

		for (int y = 0; y < height; y++) {
			double ceilling = (y + -height / 2.0) / height;

			double z = (floorPosition + up) / ceilling;

			if (Controller.walk) {
				z = (floorPosition + up + walking) / ceilling;
			}

			if (ceilling < 0) {
				z = (ceilingPosition - up) / -ceilling;
				if (Controller.walk) {
					z = (ceilingPosition - up - walking) / -ceilling;
				}
			}

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;
				double xx = (depth * cosine + z * sine);// + right);
				double yy = (z * cosine - depth * sine);// + foreward);
				int xPix = (int) (xx + right);
				int yPix = (int) (yy + foreward);
				zBuffer[x + y * width] = z;
				pixels[x + y * width] = Texture.floor.pixels[(xPix & 7)
						+ (yPix & 7) * 8];

				if (z > 500) {
					pixels[x + y * width] = 0;
				}
			}
		}

	}

	public void renderWall(double xLeft, double xRight, double zDistance,
			double yHeight) {
		double xcLeft = ((xLeft) - right) * 2;
		double zcLeft = ((zDistance) - foreward) * 2;

		double rotLeftSideX = xcLeft * cosine - zcLeft * sine;
		double yCornerTL = ((-yHeight) - up) * 2;
		double yCornerBL = ((+0.5 - yHeight) - up) * 2;
		double rotLeftSideZ = zcLeft * cosine + zcLeft * sine;

		double xcRight = ((xRight) - right) * 2;
		double zcRight = ((zDistance) - foreward) * 2;

		double rotRightSideX = xcRight * cosine - zcRight * sine;
		double yCornerTR = ((-yHeight) - up) * 2;
		double yCornerBR = ((+0.5 - yHeight) - up) * 2;
		double rotRightSideZ = zcRight * cosine + xcRight * sine;

		double xPixelLeft = (rotLeftSideX / rotLeftSideZ * height + width / 2);
		double xPixelRight = (rotRightSideX / rotRightSideZ * height + width / 2);

		if (xPixelLeft >= xPixelRight) {
			return;
		}

		int xPixelLeftInt = (int) (xPixelLeft);
		int xPixelRightInt = (int) (xPixelRight);

		if (xPixelLeftInt < 0) {
			xPixelLeftInt = 0;
		}
		if (xPixelLeftInt > width) {
			xPixelRightInt = width;
		}

		double yPixelLeftTop = (int) (yCornerTL / rotLeftSideZ * height + height / 2);
		double yPixelLeftBottom = (int) (yCornerBL / rotLeftSideZ * height + height / 2);
		double yPixelRightTop = (int) (yCornerTR / rotRightSideZ * height + height / 2);
		double yPixelRightBottom = (int) (yCornerBR / rotRightSideZ * height + height / 2);

		for (int x = xPixelLeftInt; x < xPixelRightInt; x++) {
			double pixelRotation = (x - xPixelLeft)
					/ (xPixelRight - xPixelLeft);
			double yPixelTop = yPixelLeftTop + (yPixelRightTop - yPixelLeftTop)
					* pixelRotation;
			double yPixelBottom = yPixelLeftBottom
					+ (yPixelRightBottom - yPixelLeftBottom) * pixelRotation;

			int yPixelTopInt = (int) (yPixelTop);
			int yPixelBottomInt = (int) yPixelBottom;

			if (yPixelTopInt < 0) {
				yPixelTopInt = 0;
			}

			if (yPixelBottomInt > height) {
				yPixelBottomInt = height;
			}

			for (int y = yPixelTopInt; y < yPixelBottomInt; y++) {
				pixels[x + y * width] = 0x00ff00;
				zBuffer[x + y * width] = 0;
			}
		}
	}

	public void renderDistanceLimiter() {
		for (int i = 0; i < width * height; i++) {
			int colour = pixels[i];
			int brightness = (int) (renderDistance / (zBuffer[i]));

			if (brightness < 0) {
				brightness = 0;
			}

			if (brightness > 255) {
				brightness = 255;
			}

			int r = (colour >> 16) & 0xff;
			int g = (colour >> 8) & 0xff;
			int b = (colour) & 0xff;

			r = r * brightness / 255;
			g = g * brightness / 255;
			b = b * brightness / 255;

			pixels[i] = r << 16 | g << 8 | b;
		}
	}
}
