package com.parth.mehrotra.sinful.colors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	Catalog catalog;
	private Context mContext;

	public ImageAdapter(Context c) {
		mContext = c;
		catalog = new Catalog();
	}

	@Override
	public int getCount() {
		return catalog.SHADE_ID.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView iv;
		if (convertView == null) {
			iv = new ImageView(mContext);
            iv.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv.setPadding(1, 1, 1, 1);
		} else {
			iv = (ImageView) convertView;
		}
		
		iv.setImageResource(catalog.SHADE_ID[position]);
		return iv;
	}


}
