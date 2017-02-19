package com.parth.mehrotra.sinful.colors;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.actionbarsherlock.app.SherlockFragment;

public class HSVPicker extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle ParthMehrotra) {
		if (container == null) {
			return null;
		}

		ScrollView sv = new ScrollView(getSherlockActivity());
		sv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		sv.setFillViewport(true);
		sv.setBackgroundColor(Color.BLACK);

		LinearLayout ll = new LinearLayout(getSherlockActivity());
		ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		ll.setOrientation(LinearLayout.VERTICAL);

		ImageView iv = new ImageView(getSherlockActivity());
		iv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		iv.setImageResource(R.drawable.banner);
		ll.addView(iv);

		Button test1 = new Button(getSherlockActivity());
		test1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 500));
		test1.setText("Test1");

		Button test2 = new Button(getSherlockActivity());
		test1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 500));
		test2.setText("Test2");

		ll.addView(test1);
		ll.addView(test2);
		sv.addView(ll);
		return sv;
	}
}
