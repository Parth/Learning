package org.skylight1.neny.android;

import org.skylight1.neny.android.database.RestaurantDatabase;
import org.skylight1.neny.android.database.model.Address;
import org.skylight1.neny.android.database.model.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowRestaurantDetailActivity extends Activity {
	
	private String camis = null;
	private String phoneNumber = null;
	
	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		
		super.onCreate(aSavedInstanceState);
		
		setContentView(R.layout.restaurant_detail);
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
			
			camis = extras.getString("camis");
			
			if (camis != null) {
				
				Restaurant restaurant = new RestaurantDatabase(this).getRestaurantByCamis(camis);
				
				if (restaurant != null) {
					showRestaurantDetail(restaurant);
				}
				
			}
			
		}

	}
	
	private void showRestaurantDetail(Restaurant restaurant) {
		
		TextView tvRestaurantName = (TextView) findViewById(R.id.tv_detail_restaurant_name);
		
		tvRestaurantName.setText(restaurant.getDoingBusinessAs());
		
		TextView tvRestaurantPhone = (TextView) findViewById(R.id.tv_detail_restaurant_phone);
		
		phoneNumber = restaurant.getPhone();
		
		tvRestaurantPhone.setText(phoneNumber);
		
		Address address = restaurant.getAddress();
		
		TextView tvStreet = (TextView) findViewById(R.id.tv_detail_restaurant_street);
		TextView tvZipCode = (TextView) findViewById(R.id.tv_detail_restaurant_zipcode);
		
		String street = address.getBuilding() + " " + address.getStreet();
		
		tvStreet.setText(street);
		tvZipCode.setText(address.getZipCode());		
	}
	
	public void callRestaurant(View v) {
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + phoneNumber));
		startActivity(callIntent);
		
	}
		
}
