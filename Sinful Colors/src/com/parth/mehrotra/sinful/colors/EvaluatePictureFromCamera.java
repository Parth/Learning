package com.parth.mehrotra.sinful.colors;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

@SuppressLint({ "ParserError", "ParserError" })
public class EvaluatePictureFromCamera extends SherlockActivity implements
		OnTouchListener {

	private Uri imageUri;
	ImageView picture;
	Crosshairs ch;

	Drawable d;

	Bitmap bitmap;
	Bitmap newBitmap;
	Bitmap bit;

	int r, g, b;

	boolean first = true;
	boolean gone = false;

	Uri selectedImage;
	ContentResolver cr;

	LinearLayout swatch;
	FrameLayout fl, initOverlay;

	Button launch;

	Color swatchColor;

	Animation frameAnimation, buttonAnimation;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle inState) {
		super.onCreate(inState);

		setContentView(R.layout.evaluate_picture);
		init();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.abtitle, null);
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));
		title.setText("Capture!");

		getSupportActionBar().setCustomView(v);
		bitmap = (Bitmap) getLastNonConfigurationInstance();

		if (bitmap == null) {

			bitmap = (Bitmap) this.getIntent().getParcelableExtra("bitmap");

			d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
					bitmap, 300, 300, false));
			ch.setBackgroundDrawable(d);
			ch.makeInvisible();
		} else {
			restore();
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void init() {

		initOverlay = (FrameLayout) findViewById(R.id.evaluate_overlay_help);

		ch = new Crosshairs(this);
		ch.setOnTouchListener(this);

		// initOverlay.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// initOverlay.setVisibility(View.GONE);
		// }
		//
		//
		// });

		swatch = (LinearLayout) findViewById(R.id.swatch);
		picture = (ImageView) findViewById(R.id.picture);
		fl = (FrameLayout) findViewById(R.id.frameparent);

		fl.addView(ch);
		launch = (Button) findViewById(R.id.launchEvaluate);
		launch.setVisibility(View.INVISIBLE);
		launch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putInt("r", r);
				bundle.putInt("g", g);
				bundle.putInt("b", b);

				Intent myIntent = new Intent(EvaluatePictureFromCamera.this,
						Evaluate.class);
				myIntent.putExtras(bundle);

				startActivityForResult(myIntent, 0);
			}
		});
	}

	int x = 0;
	int y = 0;

	private void dismissFrameIfNessisary() {
		if (!gone) {
			dismissFrame();
			showButton();
		}
		gone = true;
	}

	int f = 255;

	private void dismissFrame() {
		frameAnimation = new AlphaAnimation(1, 0);
		frameAnimation.setDuration(750);
		frameAnimation.setInterpolator(new LinearInterpolator());
		initOverlay.setAnimation(frameAnimation);

		frameAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				initOverlay.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void showButton() {
		buttonAnimation = new AlphaAnimation(0, 1);
		buttonAnimation.setStartOffset(20);
		buttonAnimation.setInterpolator(new LinearInterpolator());
		buttonAnimation.setDuration(2000);
		launch.setAnimation(buttonAnimation);
	}

	@SuppressLint("NewApi")
	private void dismissFrameViaSetAlpha() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (; f >= 0; f--) {

					initOverlay.post(new Runnable() {
						public void run() {

							initOverlay.getBackground().setAlpha(f);

						}
					});
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				initOverlay.post(new Runnable() {
					public void run() {
						initOverlay.setVisibility(View.GONE);
					}
				});
			}
		}).start();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		dismissFrameIfNessisary();

		ch.makeVisisble();
		launch.setVisibility(View.VISIBLE);
		first = false;

		// int x = ch.getW() / 2;
		// int y = ch.getH() / 2;

		if (event.getX() >= 0 && event.getX() < ch.getW()) {
			x = (int) event.getX();
			System.out.println("X: " + x + ", Y: " + y + ", WIDTH: "
					+ ch.getW() + ", HEIGHT: " + ch.getH());
		}

		if (event.getY() >= 0 && event.getY() < ch.getH()) {
			y = (int) event.getY();
		}

		ch.touched(x, y);
		updateSwatch();
		return true;
	}

	public void updateSwatch() {
		int chX = ch.getCrosshairsX();
		int chY = ch.getCrosshairsY();

		Bitmap chBitmap = Bitmap.createScaledBitmap(
				Bitmap.createScaledBitmap(bitmap, 300, 300, false), ch.getW(),
				ch.getH(), false);

		r = Color.red(chBitmap.getPixel(chX, chY));
		g = Color.green(chBitmap.getPixel(chX, chY));
		b = Color.blue(chBitmap.getPixel(chX, chY));

		swatch.setBackgroundColor(Color.rgb(r, g, b));
		ch.setColor(r, g, b);

		chBitmap.recycle();
	}

	public void restore() {
		d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
				bitmap, 300, 300, false));
		ch.setBackgroundDrawable(d);
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return bitmap;
	}
}
