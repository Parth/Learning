package org.skylight1.neny.android.database.dao;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class AbstractPreferences implements PreferencesDao {

	@Override
	public boolean setPreferences(String value, boolean isSelected) {
		final Editor edit = preferences.edit();
		
		edit.putBoolean(value, isSelected);
		return edit.commit();
	}
	
	@Override
	public boolean getPreference(String key, boolean defaultValue) {
		return preferences.getBoolean(key, defaultValue);
	}

	protected SharedPreferences preferences;

}
