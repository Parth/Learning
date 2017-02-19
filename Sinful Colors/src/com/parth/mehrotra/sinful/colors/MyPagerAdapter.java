package com.parth.mehrotra.sinful.colors;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyPagerAdapter extends FragmentPagerAdapter {

	private final List<Fragment> fragments;

	public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

	@Override
	public String getPageTitle(int pos) {
		if (pos == 0) {//TODO add string resources
			return "Featured";
		}
		if (pos == 1) {
			return "RGB";
		} else
			return "HSV";
	}
}
