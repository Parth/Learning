package com.parth.mehrotra.sinful.colors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

@SuppressLint("ParserError")
public class EvaluatePictureFromGallery extends SherlockActivity {
	private static final int REQUEST_CODE = 1;
	private Bitmap bitmap;
	private ImageView imageView;

	Drawable d;

	Crosshairs ch;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluate_picture);
		pickImageFromGallery();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
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
		imageView = (ImageView) findViewById(R.id.picture);
		ch = new Crosshairs(this);
	}

	public void pickImageFromGallery() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			try {
				// We need to recyle unused bitmaps
				if (bitmap != null) {
					bitmap.recycle();
				}
				InputStream stream = getContentResolver().openInputStream(
						data.getData());
				bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(stream), 300, 300, false);
				stream.close();

				Intent intent = new Intent(this,
						EvaluatePictureFromCamera.class);
				intent.putExtra("bitmap", bitmap);
				startActivityForResult(intent, 0);

				// ch.se

				// ch.setBackgroundDrawable(d);

			} catch (FileNotFoundException e) {
				Log.e("Error", "" + e);
			} catch (IOException e) {
				Log.e("Error", "" + e);
			}
			super.onActivityResult(requestCode, resultCode, data);
		} else {
			finish();
		}
	}
}