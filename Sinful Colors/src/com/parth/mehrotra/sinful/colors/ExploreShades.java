package com.parth.mehrotra.sinful.colors;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TitlePageIndicator;

public class ExploreShades extends SherlockFragmentActivity {

	private PagerAdapter mPagerAdapter;
	private TitlePageIndicator title;

	int titleBackground;

	@Override
	protected void onCreate(Bundle ParthMehrotra) {
		super.onCreate(ParthMehrotra);
		super.setContentView(R.layout.explore_colors);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
//
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.abtitle, null);
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));
// 
		title.setText("Explore Shades");
		
		getSupportActionBar().setCustomView(v);
		this.initialisePaging();
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

	private void initialisePaging() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, ShadeDisplay.class.getName()));
		fragments.add(Fragment.instantiate(this, RGBPicker.class.getName()));
		//fragments.add(Fragment.instantiate(this, HSVPicker.class.getName()));

		this.mPagerAdapter = new MyPagerAdapter(
				super.getSupportFragmentManager(), fragments);
		ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
		pager.setAdapter(this.mPagerAdapter);

		title = (TitlePageIndicator) findViewById(R.id.titles);
		title.setFooterColor(Color.RED);
		title.setBackgroundColor(Color.BLACK);
		title.setSelectedColor(Color.WHITE);
		title.setTextColor(Color.GRAY);
		title.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/sinful.ttf"));
		title.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int x) {
				if (x == 0) {
					animateTitleColor(85, 0);
				} 
				if (x == 1){
					animateTitleColor(0, 85);
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		title.setViewPager(pager);
	}

	public void animateTitleColor(final int start, final int finish) {
		new Thread(new Runnable() {
			public void run() {
 
				boolean down = start > finish;
				titleBackground = start;
 
				while (titleBackground != finish) {
					if (down) { 
						titleBackground--;
					} else {
						titleBackground++;
					}
					try {
						Thread.sleep(5);
					} catch (Exception e) {
					}

					title.post(new Runnable() {
						public void run() {
							title.setBackgroundColor(Color.rgb(titleBackground,
									titleBackground, titleBackground));
						}
					});
				}
			}
		}).start();
	}
}