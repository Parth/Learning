//@author: Parth Mehrotra
package com.parth.mehrotra.sinful.colors;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

@SuppressLint({ "ParserError", "NewApi", "ParserError", "ParserError" })
public class DashBoardActivity extends SherlockActivity implements
		OnClickListener {

	LinearLayout root;

	boolean capturePressed = false, explorePressed = false,
			favoritePressed = false;

	// These linear layouts are the three buttons presented at the dashboard of
	// the app

	LinearLayout capture, explore, obtain;
	LinearLayout captureBackground, exploreBackground, favoritesBackground;

	TextView captureText, exploreText, favoritesText;

	// Radio Buttons in the "Capture" LL that decide how the image will be
	// evaluated
	RadioButton cameraRB, galleryRB;

	@SuppressLint({ "NewApi", "NewApi" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		initUI();
		setListeners();

		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.abtitle, null);
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));
		title.setText("SinfulColors");

		getSupportActionBar().setCustomView(v);
	}

	@SuppressWarnings("deprecation")
	public void setListeners() {
		capture.setOnClickListener(this);
		explore.setOnClickListener(this);
		obtain.setOnClickListener(this);

		capture.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!capturePressed)
					captureBackground
							.setBackgroundDrawable(getRandomBackgroundBitmap());
				capturePressed = true;
				if (!touchInBounds(v, event)) {
					captureBackground.setBackgroundColor(Color.WHITE);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					captureBackground.setBackgroundColor(Color.WHITE);
					noFingersTouching();
				}

				System.out.println(event.toString());
				return false;
			}
		});

		explore.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!explorePressed) {
					exploreBackground
							.setBackgroundDrawable(getRandomBackgroundBitmap());
				}
				explorePressed = true;

				if (!touchInBounds(v, event)) {
					exploreBackground.setBackgroundColor(Color.WHITE);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					exploreBackground.setBackgroundColor(Color.WHITE);
					noFingersTouching();
				}
				return false;
			}
		});
		obtain.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!favoritePressed) {
					favoritesBackground
							.setBackgroundDrawable(getRandomBackgroundBitmap());
				}
				favoritePressed = true;

				if (!touchInBounds(v, event)) {
					favoritesBackground.setBackgroundColor(Color.WHITE);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					favoritesBackground.setBackgroundColor(Color.WHITE);
					noFingersTouching();
				}
				return false;
			}
		});
	}

	public boolean touchInBounds(View v, MotionEvent event) {
		if (event.getX() > v.getWidth() || event.getY() > v.getHeight()
				|| event.getX() <= 0 || event.getY() <= 0)
			return false;
		else
			return true;
	}

	public BitmapDrawable getRandomBackgroundBitmap() {
		Catalog catalog = new Catalog();
		Random rand = new Random();
		int randomShade = rand.nextInt(Catalog.LENGTH);
		int chosenShadeRes = catalog.SHADE_ID[randomShade];
		BitmapDrawable back = new BitmapDrawable(BitmapFactory.decodeResource(
				getResources(), chosenShadeRes));
		back.setTileModeXY(Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
		return back;
	}

	public void dimCaptureAnimation(int color) {// The thread is too unreliable,
												// maybe another day
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 255;
				while (i >= 20) {
					i -= 20;
					final int _i = i;
					captureBackground.post(new Runnable() {

						@Override
						public void run() {
							final int x = _i;
							captureBackground.setBackgroundColor(Color.rgb(255,
									x, x));
						}
					});
					try {

						Thread.sleep(5);
					} catch (Exception e) {

					}
				}
			}
		}).start();
	}

	public void initUI() {
		capture = (LinearLayout) findViewById(R.id.dashboard_capture);
		explore = (LinearLayout) findViewById(R.id.dashboard_explore);
		obtain = (LinearLayout) findViewById(R.id.dashboard_obtain);

		captureBackground = (LinearLayout) findViewById(R.id.captureBackground);
		exploreBackground = (LinearLayout) findViewById(R.id.exploreBackground);
		favoritesBackground = (LinearLayout) findViewById(R.id.favoritesBackground);
		root = (LinearLayout) findViewById(R.id.dashboardRoot);

		// Maybe another day
		// int colorChooser = (int) (Math.random() * 100);
		//
		// if (colorChooser > 90 && colorChooser < 94) {
		// capture.setBackgroundResource(R.drawable.red_one_button);
		// explore.setBackgroundResource(R.drawable.red_two_button);
		// obtain.setBackgroundResource(R.drawable.red_three_button);
		// } else if (colorChooser >= 94 && color)

		cameraRB = (RadioButton) findViewById(R.id.cameraRB);
		cameraRB.setChecked(true);
		cameraRB.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));

		galleryRB = (RadioButton) findViewById(R.id.galleryRB);
		galleryRB.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));

		captureText = (TextView) findViewById(R.id.captureText);
		captureText.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));

		exploreText = (TextView) findViewById(R.id.exploreText);
		exploreText.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));

		exploreText = (TextView) findViewById(R.id.favoritesText);
		exploreText.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));
	}

	public void launchFavorites() {
		Intent intent = new Intent(DashBoardActivity.this, Favorites.class);
		startActivityForResult(intent, 0);
	}

	public void launchGallery() {
		Intent myIntent = new Intent(DashBoardActivity.this,
				EvaluatePictureFromGallery.class);
		startActivityForResult(myIntent, 0);
	}

	public void launchCamera() {
		Intent myIntent = new Intent(DashBoardActivity.this,
				CameraMiddleMan.class);
		startActivityForResult(myIntent, 0);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.dashboard_capture && cameraRB.isChecked()) {
			launchCamera();
		}

		if (v.getId() == R.id.dashboard_capture && galleryRB.isChecked()) {
			launchGallery();
		}

		if (v.getId() == R.id.dashboard_explore) {
			launchExplorer();
		}

		if (v.getId() == R.id.dashboard_obtain) {
			launchFavorites();
		}
	}

	public void launchExplorer() {
		Intent myIntent = new Intent(DashBoardActivity.this,
				ExploreShades.class);
		startActivityForResult(myIntent, 0);
	}

	@Override 
	public void onPause() {
		super.onPause();
		noFingersTouching();
	}

	public void noFingersTouching() {
		captureBackground.setBackgroundColor(Color.WHITE);
		exploreBackground.setBackgroundColor(Color.WHITE);
		favoritesBackground.setBackgroundColor(Color.WHITE);

		capturePressed = false;
		explorePressed = false;
		favoritePressed = false;
	}

}
// @author: Parth Mehrotra