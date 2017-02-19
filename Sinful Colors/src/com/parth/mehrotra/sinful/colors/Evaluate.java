package com.parth.mehrotra.sinful.colors;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;

public class Evaluate extends SherlockListActivity {

	View display;

	int r = 0, g = 0, b = 0;
	boolean favorite = false;

	int matches[] = new int[20];
	String[] matchNames = new String[20];
	int[][] colorMatches = new int[20][3];

	Catalog catalog;

	private static final int LENGTH = 118;

	ImageView star;

	boolean matchesComplete = false;
	int matchPlace = 0;
	int allowance = 0;

	ListView list;

	@Override
	public void onCreate(Bundle ParthMehrotra) {
		super.onCreate(ParthMehrotra);
		setContentView(R.layout.evaluate_color);
		catalog = new Catalog();
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
		title.setText("SinfulColors");

		getSupportActionBar().setCustomView(v);

		Bundle bundle = getIntent().getExtras();

		r = bundle.getInt("r");
		g = bundle.getInt("g");
		b = bundle.getInt("b");

		// Alright we are going to consider the rgb values as points in 3-D
		// space

		int[][] differenceSquared = new int[Catalog.LENGTH][3];

		for (int i = 0; i < Catalog.LENGTH; i++) {
			differenceSquared[i][0] = (int) (Math.pow(r - catalog.COLORS[i][0],
					2));
			differenceSquared[i][1] = (int) (Math.pow(g - catalog.COLORS[i][1],
					2));
			differenceSquared[i][2] = (int) (Math.pow(b - catalog.COLORS[i][2],
					2));
		}

		int[] distance = new int[Catalog.LENGTH];

		// The distance is stored in distance[] values ranging from 0 - ~441

		for (int i = 0; i < Catalog.LENGTH; i++) {
			distance[i] = (int) (Math.sqrt((differenceSquared[i][0])
					+ (differenceSquared[i][1]) + (differenceSquared[i][2])));
		}
		
		// Let's sort this
		
		int[] sortPos = new int[Catalog.LENGTH];
		
		for (int i = 0; i < sortPos.length; i++) {
			sortPos[i] = i;
		}
		
		for (int x = 0; x < distance.length - 1; x++) {
			for (int y = 1; y < distance.length - x; y++) {
				if (distance[y - 1] > distance[y]) {
					int temp = distance[y - 1];
					distance[y - 1] = distance[y];
					distance[y] = temp;
					int temp2 = sortPos[y - 1];
					sortPos[y - 1] = sortPos[y];
					sortPos[y] = temp2;
				}
			}
		}
		
		for (int i = 0; i < 20; i++) {
			matches[i] = sortPos[i];
		}

		for (int a = 0; a < matches.length; a++) {
			matchNames[a] = catalog.NAMES[matches[a]];
			for (int b = 0; b < 3; b++) {
				colorMatches[a][b] = catalog.COLORS[matches[a]][b];
			}
		}

		AssetManager assets = getAssets();

		ColorArrayAdapter myColorArrayAdapter = new ColorArrayAdapter(this,
				matchNames);

		myColorArrayAdapter.setMatches(matches);

		setListAdapter(myColorArrayAdapter);

		list = (ListView) findViewById(android.R.id.list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(Evaluate.this, MatchDisplay.class);

				bundle.putInt("shadeID", matches[(int) arg3]);
				intent.putExtras(bundle);

				startActivityForResult(intent, 0);
			}

		});

		display = (View) findViewById(R.id.evaluateColorDisplay);
		display.setBackgroundColor(Color.rgb(r, g, b));

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

}
/*
 * Favor
 */
