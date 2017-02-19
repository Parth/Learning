package org.skylight1.neny.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.skylight1.neny.android.database.RestaurantDatabase;
import org.skylight1.neny.android.database.dao.CuisinePreferences;
import org.skylight1.neny.android.database.dao.NeighborhoodPreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.Cuisine;
import org.skylight1.neny.android.database.model.Neighborhood;
import org.skylight1.neny.android.database.model.Restaurant;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ShowRestaurantListActivity extends ListActivity {
	
	private static final String TAG = ShowRestaurantListActivity.class.getSimpleName();
	ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	private class LoadLocalRestaurantsTask extends AsyncTask<String, Integer, String> {
		
		private final List<Neighborhood> neighborhoods;
		private final List<Cuisine> cuisines;
		
		public LoadLocalRestaurantsTask(Context aContext){
			neighborhoods = new NeighborhoodPreferences(aContext).getAllUserSelectedNeighborhoods();
			cuisines = new CuisinePreferences(aContext).getAllUserSelectedCuisines();
			
		}
		protected String doInBackground(String... unused) {
					
			String status = "retrieving restaurants from local database...";
			
			try {
				Log.d(TAG, neighborhoods.toString());
				Log.d(TAG,cuisines.toString());
				restaurants = new RestaurantDatabase(ShowRestaurantListActivity.this).getRestaurantsByUserPrefs(neighborhoods, cuisines);
				
				status = restaurants.size() + " restaurants retrieved";
				
			} catch (Exception e) {
				
				status = e.getMessage();
				e.printStackTrace();
				
				
			}
			
			return status;
		}
		
		// We don't need to pass this array around
		protected void onPostExecute(String result) {
			
			ListView restaurantsListView = (ListView) findViewById(android.R.id.list);
		
			restaurantsListView.setAdapter(new RestaurantArrayAdapter(ShowRestaurantListActivity.this, restaurants));
			
		}
		
	}
	
	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);

		setContentView(R.layout.restaurant_list_view);

		new LoadLocalRestaurantsTask(this).execute((String[]) null);
		
	}
	
	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		
		Restaurant restaurant = restaurants.get(position);
		
		String camis = restaurant.getCamis();
		
		Intent intent = new Intent(this, ShowRestaurantDetailActivity.class);
		
		intent.putExtra("camis", camis);
		
		startActivity(intent);
		
	}
	
		
}
