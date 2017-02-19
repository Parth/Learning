package com.parth.mehrotra.sinful.colors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Crosshairs extends View {

	Paint lines;
	Paint circle;

	static final int offset = 15;
	int cX = 200, cY = 200;
	int maxX, maxY;

	int FRAME_CONSTANT;
	static final int SCALE_CONSTANT = 5000;
	static final int ALPHA_CONSTANT = 200;

	private boolean linesAreVisible = false;

	public Crosshairs(Context context) {
		super(context);
		lines = new Paint();
		lines.setColor(Color.RED);
		lines.setStrokeWidth(5);
		// lines.setShadowLayer(radius, dx, dy, color)

		circle = new Paint();
		circle.setStrokeWidth(20);
		circle.setStyle(Paint.Style.STROKE);
	}

	@SuppressLint("ParserError")
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		FRAME_CONSTANT = (getWidth() * getHeight()) / SCALE_CONSTANT;
		if (linesAreVisible) {
			canvas.drawLine(cX, 0, cX, getHeight(), lines);
			canvas.drawLine(0, cY, getWidth(), cY, lines);

			canvas.drawCircle(cX, cY, 100, circle);

		}
		// canvas.drawRect(0, FRAME_CONSTANT, FRAME_CONSTANT, getHeight()
		// - FRAME_CONSTANT, button);
		//
		// canvas.drawRect(FRAME_CONSTANT, 0, getWidth() - FRAME_CONSTANT,
		// FRAME_CONSTANT, button);
		//
		// canvas.drawRect(getWidth() - FRAME_CONSTANT, FRAME_CONSTANT,
		// getWidth(), getHeight() - FRAME_CONSTANT, button);
		//
		// canvas.drawRect(FRAME_CONSTANT, getHeight() - FRAME_CONSTANT,
		// getWidth() - FRAME_CONSTANT, getHeight(), button);
	}

	public void makeVisisble() {
		linesAreVisible = true;
	}

	public void makeInvisible() {
		linesAreVisible = false;
	}

	public int getW() {
		return getWidth();
	}

	public int getH() {
		return getHeight();
	}

	public void touched(float x, float y) {
		// if ((x > 0 && x < FRAME_CONSTANT)
		// && (y > FRAME_CONSTANT && y < getHeight() - FRAME_CONSTANT)) {
		// //if (cX > 0) {
		// cX -= offset;
		// //}
		// }
		//
		// if ((x > FRAME_CONSTANT && x < getWidth() - FRAME_CONSTANT)
		// && (y > 0 && y < FRAME_CONSTANT)) {
		// //if (cY > 0) {
		// cY -= offset;
		// //}
		// }
		//
		// if ((x > getWidth() - FRAME_CONSTANT)
		// && (y > FRAME_CONSTANT && y < getHeight() - FRAME_CONSTANT)) {
		// //if (cX < maxX) {
		// cX += offset;
		// //}
		// }
		//
		// if ((x > FRAME_CONSTANT && x < getWidth() - FRAME_CONSTANT)
		// && (y > getHeight() - FRAME_CONSTANT)) {
		// //if (cY < maxY) {
		// cY += offset;
		// //}
		// }

		cX = (int) x;
		cY = (int) y;

		invalidate();
	}

	public void setColor(int r, int g, int b) {
		circle.setColor(Color.rgb(r, g, b));
	}

	public void setMaxDim(int w, int h) {
		maxX = w;
		maxY = h;
	}

	public int getCrosshairsX() {
		return cX;
	}

	public int getCrosshairsY() {
		return cY;
	}
}