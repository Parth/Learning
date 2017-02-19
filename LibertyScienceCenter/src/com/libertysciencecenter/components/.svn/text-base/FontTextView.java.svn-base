package com.libertysciencecenter.components;

import com.libertysciencecenter.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontTextView extends TextView {

	public FontTextView(Context context) {
		super(context);
	}

	public FontTextView(Context context, AttributeSet attrs) {
		super(context, attrs); 
		setCustomFont(context, attrs);
	}
 
	public FontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

	@SuppressLint("ParserError")
	private void setCustomFont(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.FontTextView);
		 
		String customFont = a.getString(R.styleable.FontTextView_customFont);
		setCustomFont(context, customFont);
		a.recycle();
	}
	
	public boolean setCustomFont(Context context, String asset){
		Typeface tf = null;
		
		try {
			tf = Typeface.createFromAsset(context.getAssets(), asset);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		setTypeface(tf);
		return true;
	}

}
