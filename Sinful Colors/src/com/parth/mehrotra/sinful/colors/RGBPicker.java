package com.parth.mehrotra.sinful.colors;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.actionbarsherlock.app.SherlockFragment;

public class RGBPicker extends SherlockFragment {

	SeekBar rSeek, gSeek, bSeek;
	EditText rEdit, gEdit, bEdit;

	LinearLayout rgb;

	boolean editing = false;
	
	Button go;

	int r = 0, g = 0, b = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle ParthMehrotra) {
		if (container == null) {
			return null;
		}

		Random random = new Random();

		r = random.nextInt(255);
		g = random.nextInt(255);
		b = random.nextInt(255);

		View view = (LinearLayout) inflater.inflate(R.layout.rgbpicker,
				container, false);

		rSeek = (SeekBar) view.findViewById(R.id.rSeekbarValue);
		rSeek.setMax(255);
		rSeek.setProgress(r);

		gSeek = (SeekBar) view.findViewById(R.id.gSeekbarValue);
		gSeek.setMax(255);
		gSeek.setProgress(g);

		bSeek = (SeekBar) view.findViewById(R.id.bSeekbarValue);
		bSeek.setMax(255);
		bSeek.setProgress(b);

		rEdit = (EditText) view.findViewById(R.id.rEditValue);
		rEdit.setText(String.valueOf(r));

		gEdit = (EditText) view.findViewById(R.id.gEditValue);
		gEdit.setText(String.valueOf(g));

		bEdit = (EditText) view.findViewById(R.id.bEditValue);
		bEdit.setText(String.valueOf(b));

		rgb = (LinearLayout) view.findViewById(R.id.rgbBackground);
		rgb.setBackgroundColor(Color.rgb(r, g, b));
		
		go = (Button) view.findViewById(R.id.launchEvaluate);
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Bundle bundle = new Bundle();
				bundle.putInt("r", r);
				bundle.putInt("g", g);
				bundle.putInt("b", b);
				
				Intent myIntent = new Intent(getActivity(), Evaluate.class);
				myIntent.putExtras(bundle);

				startActivityForResult(myIntent, 0);
			}
		});

		rSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					editing = false;
				}
				r = progress;
				update();
			}
		});
		gSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				g = progress;
				if (fromUser) {
					editing = false;
				}
				update();
			}
		});
		bSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				b = progress;
				if (fromUser) {
					editing = false;
				}
				update();
			}
		});

		rEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editing = true;
			}
		});
		gEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editing = true;
			}
		});
		bEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editing = true;
			}
		});

		rEdit.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event)
					throws java.lang.NumberFormatException {

				try {
					r = Integer.parseInt(rEdit.getText().toString());
				} catch (Exception e) {

				}
				update();
				return false;
			}
		});
		gEdit.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event)
					throws java.lang.NumberFormatException {

				try {
					g = Integer.parseInt(gEdit.getText().toString());
				} catch (Exception e) {

				}
				update();
				return false;
			}
		});
		bEdit.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event)
					throws java.lang.NumberFormatException {

				try {
					b = Integer.parseInt(bEdit.getText().toString());
				} catch (Exception e) {

				}
				update();
				return false;
			}
		});
		return view;
	}

	public void update() {
		if (!editing) {
			rEdit.setText(String.valueOf(r));
			gEdit.setText(String.valueOf(g));
			bEdit.setText(String.valueOf(b));
		}

		if (editing) {
			rSeek.setProgress(r);
			gSeek.setProgress(g);
			bSeek.setProgress(b);
		}

		rgb.setBackgroundColor(Color.rgb(r, g, b));
	}

}
