package com.parth.mehrotra.sinful.colors;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

@SuppressLint({ "ParserError", "ParserError" })
public class ShadeDisplay extends SherlockFragment {


	int r;
	Catalog catalog;
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle ParthMehrotra) {
		if (container == null)
			return null;

		Random random = new Random();
		catalog = new Catalog();

		r = random.nextInt(catalog.LENGTH);
		for (int i = 0; i < 10; i++) {
			System.out.println(catalog.SHADE_ID[r]);
		}

		View view = (LinearLayout) inflater.inflate(R.layout.shadedisplay,
				container, false);
		ImageView iv = (ImageView) view.findViewById(R.id.displayBackground);

		BitmapDrawable tile = new BitmapDrawable(BitmapFactory.decodeResource(
				getResources(), catalog.SHADE_ID[r]));

		tile.setTileModeXY(Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

		iv.setBackgroundDrawable(tile);

		Button go = (Button) view.findViewById(R.id.goButton);
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(getActivity(), MatchDisplay.class);
				bundle.putInt("shadeID", r);

				intent.putExtras(bundle);

				startActivityForResult(intent, 0);
			}
		});

		return view;
	}

	public int dips(int x) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x,
				getResources().getDisplayMetrics());
	}



}
// Going to miss this pattern
//
// Display display;
//
// Pattern[] shades = new Pattern[36];
// Pattern pattern;
// Animation animation;
//
// boolean up;
// int count;
//
// @Override
// public View onCreateView(LayoutInflater inflater, ViewGroup container,
// Bundle ParthMehrotra) {
// if (container == null) {
// return null;
// }
//
// Display display = this.getSherlockActivity().getWindowManager()
// .getDefaultDisplay();
// int w = display.getWidth(); // deprecated
// int h = display.getWidth();
//
// ScrollView sv = new ScrollView(getSherlockActivity());
// sv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
// LayoutParams.FILL_PARENT));
// sv.setFillViewport(true);
// sv.setBackgroundColor(Color.rgb(0, 0, 0));
//
// LinearLayout ll = new LinearLayout(getSherlockActivity());
// ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
// LayoutParams.WRAP_CONTENT));
// ll.setOrientation(LinearLayout.VERTICAL);
//
// ImageView iv = new ImageView(getSherlockActivity());
// iv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
// LayoutParams.WRAP_CONTENT));
// iv.setBackgroundColor(Color.BLACK);
// iv.setImageResource(R.drawable.banner);
// ll.addView(iv);
//
// pattern = new Pattern(getSherlockActivity(), w);
// // pattern.run();
// pattern.setLayoutParams(new LayoutParams(w, w));
//
// animation = new AlphaAnimation(1, 0);
// animation.setDuration(2500);
// animation.setInterpolator(new LinearInterpolator());
// animation.setRepeatCount(Animation.INFINITE);
// animation.setRepeatMode(Animation.REVERSE);
// pattern.setAnimation(animation);
// count = 2;
// up = true;
// animation.setAnimationListener(new AnimationListener() {
//
// @Override
// public void onAnimationStart(Animation animation) {
//
// }
//
// @Override
// public void onAnimationRepeat(Animation animation) {
//
// if (count / 2 > 34) {
// up = false;
// }
// if (count / 2 < 1) {
// up = true;
// }
// if (!up) {
// count--;
// }
// System.out.println(count/2+", "+up);
// pattern.createBitmaps((count / 2));
//
//
// if (up) {
// count++;
// }
//
//
//
// }
//
// @Override
// public void onAnimationEnd(Animation animation) {
// }
// });
//
// ll.addView(pattern);
//
// /*
// * Pattern patern2 = new Pattern(getSherlockActivity(), 5, 6, 7, 8, 9);
// * patern2.setLayoutParams(new LayoutParams(w, h));
// */
//
// //
// // for (int i = 0; i < shades.length - 4; i++) {
// //
// // int x = i*5;
// //
// // shades[i] = new Pattern(getSherlockActivity(), x, x+1, x+2, x+3,
// // x+4);
// // shades[i].setLayoutParams(new LayoutParams(w, h));
// // ll.addView(shades[i]);
// //
// // }
//
// sv.addView(ll);
// return sv;
// }
//
// // public void thread() {
// // new Thread(new Runnable() {
// //
// // public void run() {
// // animation = new AlphaAnimation(1, 0);
// // animation.setDuration(1000);
// // animation.setInterpolator(new LinearInterpolator());
// // animation.setRepeatCount(1);
// // animation.setRepeatMode(Animation.REVERSE);
// // pattern.setAnimation(animation);
// // try {
// // Thread.sleep(1000);
// // pattern.createBitmaps(1);
// // animation.setRepeatCount(2);
// // } catch (InterruptedException e) {
// // // TODO Auto-generated catch block
// // e.printStackTrace();
// // }
// // }
// // }).start();
// // }
//
// @Override
// public void onResume() {
// super.onResume();
// // pattern.run();
// }
//
// public void onPause() {
// super.onPause();
//
// pattern.freeUpMemory();
// pattern.removeCallbacks(null);
// }
//
// @Override
// public void onDestroy() {
// super.onDestroy();
//
// pattern.freeUpMemory();
// pattern.removeCallbacks(null);
// }

