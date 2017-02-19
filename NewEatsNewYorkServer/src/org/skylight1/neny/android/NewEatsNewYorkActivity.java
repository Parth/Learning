package org.skylight1.neny.android;


import org.skylight1.neny.android.database.RestaurantDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewEatsNewYorkActivity extends Activity {

	RestaurantDatabase restaurantDatabase = new RestaurantDatabase(this);
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_view);
	}

	public void showRestaurants(final View aButton) {
		final Intent showRestaurantsIntent = new Intent(this, ShowRestaurantListActivity.class);

		startActivity(showRestaurantsIntent);
	}

	
	public void selectDiningTimes(final View aButton) {
		//final Intent showTimeIntent = new Intent(this, SelectDiningTimesActivity.class);
		final Intent showTimeIntent = new Intent(this, SelectMealTimesActivity.class);
		startActivity(showTimeIntent);
	}

	public void selectNeighborhoods(final View aButton) {
		final Intent showNeighborhoodIntent = new Intent(this, SelectNeighborhoodsActivity.class);

		startActivity(showNeighborhoodIntent);
	}

	public void selectCuisines(final View aButton) {
		final Intent showCuisineIntent = new Intent(this, SelectCuisinesActivity.class);

		startActivity(showCuisineIntent);
	}	
	
	public void refreshRestaurantList(final View v) {
		
		new GetNewRestaurantsTask(this).execute((String[]) null);
			
	}
	
}