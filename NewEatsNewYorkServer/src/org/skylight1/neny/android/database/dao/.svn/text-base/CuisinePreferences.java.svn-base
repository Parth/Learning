package org.skylight1.neny.android.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.skylight1.neny.android.database.model.Cuisine;

import android.content.Context;


public class CuisinePreferences extends AbstractPreferences implements
		PreferencesDao {

	public CuisinePreferences(Context aContext){
		preferences = aContext.getSharedPreferences("cuisines", Context.MODE_PRIVATE);
	}
	
	public List<Cuisine> getAllUserSelectedCuisines(){
		List<Cuisine> selectedCuisines = new ArrayList<Cuisine>();
		//Log.d("temp-tag",preferences.toString());
		for(Cuisine cuisine : Cuisine.values()){
			//Log.d("temp-tag", "Searching by " + cuisine.getLabel());
			
			if(getPreference(cuisine.getLabel(),true)){
				selectedCuisines.add(cuisine);
			}
		}
		return selectedCuisines;
	}

}
