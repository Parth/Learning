package com.parth.mehrotra.sinful.colors;

import java.io.File;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class CameraMiddleMan extends Activity {

	private Uri imageUri;
	Bitmap bitmap;
	Bitmap newBitmap;

	Uri selectedImage;
	ContentResolver cr;

	boolean finished = false;

	@Override
	public void onCreate(Bundle ParthMehrotra) {
		super.onCreate(ParthMehrotra);
		takePhoto();
	}

	public void takePhoto() {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		File photo = new File(Environment.getExternalStorageDirectory(),
				"Pic.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
		imageUri = Uri.fromFile(photo);
		startActivityForResult(intent, 1);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == 1) {
				if (resultCode == Activity.RESULT_OK) {
					selectedImage = imageUri;
					getContentResolver().notifyChange(selectedImage, null);
					cr = getContentResolver();
					try {
						bitmap = Bitmap.createScaledBitmap(
								android.provider.MediaStore.Images.Media
										.getBitmap(cr, selectedImage), 300,
								300, false);
						
						
						
						Intent intent = new Intent(this,
								EvaluatePictureFromCamera.class);
						intent.putExtra("bitmap", bitmap);
						finished = true;
						startActivityForResult(intent, 0);

					} catch (Exception e) {
						Log.e("Error", e + "");
					}
				} else {
					finish();
				}
			} else {

			}

		} catch (Exception e) {
			Log.e("Error", e + "");

		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (finished) {
			System.gc();
			finish();
		}
	}
}
