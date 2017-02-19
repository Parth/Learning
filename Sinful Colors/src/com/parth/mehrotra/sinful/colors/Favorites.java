package com.parth.mehrotra.sinful.colors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;

public class Favorites extends SherlockListActivity {

	Button top, bottom;
//	static final int COLORS[][] = new int[119][3];
//	static final String NAMES[] = new String[119];
	private static final String FILENAME = "wishlist";
	
	Catalog catalog;
	
	int[] wishlist;

	@Override
	public void onCreate(Bundle ParthMehrotra) {
		super.onCreate(ParthMehrotra);
		setContentView(R.layout.favorites);
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
		title.setText("WishList!");

		getSupportActionBar().setCustomView(v);

		BufferedReader input = null;
		StringBuffer buffer = null;
		try {
			input = new BufferedReader(new InputStreamReader(
					openFileInput(FILENAME)));
			String line;
			buffer = new StringBuffer();
			while ((line = input.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {

		} finally {
			if (input != null) {
				try {
					System.out.println(buffer);
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
		if (input == null || buffer == null || buffer.toString().equals("")) {
			wishlistIsEmpty();
			return;
		}
		
		wishlist = getCurrentWishlist(buffer.toString());
		int[] _wishlist = getCurrentWishlist(buffer.toString());

		if (wishlist == null) {
			Toast.makeText(getApplicationContext(),
					"There are no items in your wishlist!", Toast.LENGTH_LONG)
					.show();

			finish();
		}

		for (int i = 0; i < wishlist.length; i++) {
			wishlist[i] = _wishlist[(wishlist.length - 1) - i];
		}

		int length = wishlist.length;

		String[] wishListNames = new String[length];
		int[][] colorMatches = new int[length][3];

		for (int i = 0; i < wishListNames.length; i++) {
			wishListNames[i] = catalog.NAMES[wishlist[i]]; 
			for (int a = 0; a < 3; a++) { 
				colorMatches[i][a] = catalog.COLORS[wishlist[i]][a];
			}
		}

		ColorArrayAdapter myColorArrayAdapter = new ColorArrayAdapter(this,
				wishListNames);
		myColorArrayAdapter.setMatches(wishlist);
		
		setListAdapter(myColorArrayAdapter);

		ListView list = (ListView) findViewById(android.R.id.list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int a,
					long arg3) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(Favorites.this, MatchDisplay.class);

				bundle.putInt("shadeID", wishlist[(int) arg3]);

				intent.putExtras(bundle);

				startActivityForResult(intent, 0);

			}
		});

	}
	
	public void wishlistIsEmpty() {
		Toast.makeText(getApplicationContext(), "Your Wishlist is empty!", Toast.LENGTH_LONG).show();
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}

	public int[] getCurrentWishlist(String input) {
		String file = input;

		String[] favString = (file.trim()).split(" ");
		int[] favs = new int[favString.length];

		if (!file.equals("")) {

			for (int i = 0; i < favString.length; i++) {
				favs[i] = Integer.parseInt(favString[i]);
			}
		}
		return favs;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		onCreate(null);
	}



}
