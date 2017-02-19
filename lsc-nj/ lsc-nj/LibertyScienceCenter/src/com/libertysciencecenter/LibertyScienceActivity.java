package com.libertysciencecenter;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class LibertyScienceActivity extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liberty_science);
		
		BitmapDrawable actionbarBack = new BitmapDrawable(
				BitmapFactory.decodeResource(getResources(),
						R.drawable.ab_liberty_light));
		getSupportActionBar().setBackgroundDrawable(actionbarBack);
	}
}
