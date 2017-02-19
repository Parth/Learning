package com.parth.mehrotra.sinful.colors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

@SuppressLint({ "WorldWriteableFiles", "ParserError" })
public class MatchDisplay extends SherlockActivity {

	ImageView background;
	Catalog catalog;

	private static final String FILENAME = "wishlist";

	boolean favorite = false;
	int shadeID;

	boolean empty = false;

	@Override
	public void onCreate(Bundle ParthMehrotra) {
		super.onCreate(ParthMehrotra);
		catalog = new Catalog();
		setupView();

		if (getAllFavorites() != null)
			checkIfThisShadeIsInWishlist();
		else
			empty = true;
	}

	public void addShadeToFavorites(int id) {
		String content = "";
		if (!empty) {
			int wish[] = getAllFavorites();

			for (int i = 0; i < wish.length; i++) {
				if (wish[i] == shadeID) {
					favorite = true;
					invalidateOptionsMenu();
					return;
				}
			}

			for (int i = 0; i < wish.length; i++) {
				content = content + wish[i] + " ";
			}
			content = content + shadeID;
		} else {
			content = "" + shadeID;
		}

		FileOutputStream fos = null;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fos.write(content.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void removeShadeFromFavorites(int id) {
		int wish[] = getAllFavorites();
		String content = "";
		System.out.println(content);

		
		for (int i = 0; i < wish.length; i++) {
			content = content + wish[i] + " ";
			System.out.println(content);

		}
		
		CharSequence replace1 = ""+shadeID+" ";
		CharSequence replace2 = ""+shadeID;
		
		CharSequence blank = "";
		
		System.out.println(content);
		
		content = content.replace(replace1, blank);
		content = content.replace(replace2, blank);
		
		System.out.println(content);
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			fos.write(content.trim().getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkIfThisShadeIsInWishlist() {
		int wish[] = getAllFavorites();

		for (int i = 0; i < wish.length; i++) {
 			if (wish[i] == shadeID) {
				favorite = true;
				invalidateOptionsMenu();
				return;
			}
		}
	}

	@SuppressWarnings("unused")
	public int[] getAllFavorites() {

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
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
					if (input == null) {
						return null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (input == null) {
			return null;
		}
		String file = buffer.toString();
		
		if (file.equals("")) {
			return null;
		}

		String[] favString = file.trim().split(" ");
		int[] favs = new int[favString.length];

		for (int i = 0; i < favString.length; i++) {
			favs[i] = Integer.parseInt(favString[i]);
		}

		return favs;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.terminal_menu, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem fav = menu.findItem(R.id.favorite);
		MenuItem notFav = menu.findItem(R.id.notFavorite);

		fav.setVisible(favorite);
		notFav.setVisible(!favorite);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.favorite:
			favorite = false;
			removeShadeFromFavorites(shadeID);
			supportInvalidateOptionsMenu();
			break;
		case R.id.notFavorite:
			favorite = true;
			addShadeToFavorites(shadeID);
			supportInvalidateOptionsMenu();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public void setupView() {
		setContentView(R.layout.matchdisplay);
		Bundle data = getIntent().getExtras();

		background = (ImageView) findViewById(R.id.matchdisplay_background);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
//		getSupportActionBar().setDisplayShowCustomEnabled(true);
//		getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//		LayoutInflater inflator = (LayoutInflater) this
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View v = inflator.inflate(R.layout.abtitle, null);
//		TextView title = (TextView) v.findViewById(R.id.title);
//		title.setTypeface(Typeface.createFromAsset(getAssets(),
//				"fonts/sinful.ttf"));
// 
		BitmapDrawable tile = new BitmapDrawable();
		if (data != null) {
			if (data.containsKey("shadeID")) { 
				shadeID = data.getInt("shadeID");

				tile = new BitmapDrawable(BitmapFactory.decodeResource(
						getResources(), catalog.SHADE_ID[shadeID]));

				setTitle(catalog.NAMES[shadeID]);

			} else {
				tile = new BitmapDrawable(BitmapFactory.decodeResource(
						getResources(), R.drawable.stripesrgb));
			}
			//getSupportActionBar().setCustomView(v);
			tile.setTileModeY(Shader.TileMode.MIRROR);
			tile.setTileModeX(Shader.TileMode.MIRROR);

			background.setBackgroundDrawable(tile);

		}
	}

}
