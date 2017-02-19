package com.parth.mehrotra.sinful.colors;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ColorArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	int[][] colors = new int[20][3];

	int[] matchedValues = new int[20];
	
	Catalog catalog;

	public ColorArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list, values);
		this.context = context;
		this.values = values;
		catalog = new Catalog();
	}

	public void setMatches(int[] matches) {
		for (int i = 0; i < matches.length; i++) {
			matchedValues[i] = catalog.SHADE_ID[matches[i]];
		} 
	}

	@Override 
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.shade_name);
		ImageView view = (ImageView) rowView.findViewById(R.id.shade_color);
		textView.setText(values[position]);
		// Change the icon for Windows and iPhone
		String s = values[position];

		view.setBackgroundResource(matchedValues[position]);

		return rowView;
	}

}