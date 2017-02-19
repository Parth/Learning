package com.libertysciencecenter;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class LibertyScienceActivity extends SherlockActivity implements
		OnClickListener, OnTouchListener {

	public static final int LIBERTY_LIGHT_THEME = 0xff2c737f;
	public static final int TRANSPARENT = 0x00FFFFFF;
	public static final int HOLO_ORANGE = 0xFFFF4444;
	public static final int HOLO_BLUE = 0xFF00DDFF;

	private LinearLayout tours, events, exhibits;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liberty_science);

		@SuppressWarnings("deprecation")
		BitmapDrawable actionbarBack = new BitmapDrawable(
				BitmapFactory.decodeResource(getResources(),
						R.drawable.ab_liberty_light));
		getSupportActionBar().setBackgroundDrawable(actionbarBack);

		initLinearLayouts();
		setListeners();
	}

	private void initLinearLayouts() {
		tours = (LinearLayout) findViewById(R.id.dashboard_tour_touchzone);
		events = (LinearLayout) findViewById(R.id.dashboard_events_touchzone);
		exhibits = (LinearLayout) findViewById(R.id.dashboard_exhibits_touchzone);
	}

	private void setListeners() {
		tours.setOnClickListener(this);
		tours.setOnTouchListener(this);
		
		events.setOnClickListener(this);
		events.setOnTouchListener(this);
		
		exhibits.setOnClickListener(this);
		exhibits.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.dashboard_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(getApplicationContext(), "Camera button pressed",
				Toast.LENGTH_LONG).show();
		return true;
	}

	@Override
	public void onClick(View arg0) {

	}
	
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		v.setBackgroundColor(HOLO_BLUE);
		if (e.getAction() == MotionEvent.ACTION_UP) {
			v.setBackgroundColor(TRANSPARENT);
		}
		return false;
	}

	private void hilightItem(View view, int color) {
		view.setBackgroundColor(LIBERTY_LIGHT_THEME);
	}
}
