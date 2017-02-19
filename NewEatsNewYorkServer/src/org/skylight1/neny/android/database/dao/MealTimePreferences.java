package org.skylight1.neny.android.database.dao;

import android.content.Context;

public class MealTimePreferences extends AbstractPreferences {

	public MealTimePreferences(Context aContext){
		preferences = aContext.getSharedPreferences("mealtimes", Context.MODE_PRIVATE);
	}
}
