package org.skylight1.neny.android;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.skylight1.neny.android.database.RestaurantDatabase;
import org.skylight1.neny.android.database.model.Address;
import org.skylight1.neny.android.database.model.Borough;
import org.skylight1.neny.android.database.model.Grade;
import org.skylight1.neny.android.database.model.Restaurant;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

class GetNewRestaurantsTask extends AsyncTask<String, Integer, String> {

	private final Context context;
	private List<Restaurant> aRestaurants = new ArrayList<Restaurant>();

	/**
	 * @param aContext
	 */
	GetNewRestaurantsTask(final Context aContext) {
		context = aContext;
	}

	protected void onPreExecute() {
		// super.onPreExecute();

		Toast.makeText(context, "Starting restaurant list retrieve...",
				Toast.LENGTH_SHORT).show();

	}

	protected String doInBackground(final String... urls) {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MMM dd, yyyy HH:mm:ss a");
		final List<String> result = new ArrayList<String>();

		String status = "Connecting...";
		String camis = "";

		try {

			final URL url = new URL(context.getResources().getString(
					R.string.server));

			final StringBuilder stringBuilder = new StringBuilder();

			final HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			try {

				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
				}

				final String testString = stringBuilder.toString();

				final JSONTokener tokener = new JSONTokener(
						stringBuilder.toString());
				final JSONArray arrayOfRestaurants = (JSONArray) tokener
						.nextValue();
				for (int i = 0; i < arrayOfRestaurants.length(); i++) {

					final JSONObject restaurant = arrayOfRestaurants
							.getJSONObject(i);

					// camis
					// doingBusinessAs
					// borough

					/* part of address object */
					// building
					// street
					// zipCode

					// phone
					// cuisineCode
					// currentGrade
					// gradeDate

					camis = restaurant.getString("camis");
					final String restaurantName = restaurant
							.getString("doingBusinessAs");

					final String sBorough = restaurant.getString("borough");

					// NOT findByCode
					final Borough borough = Borough.findByName(sBorough);

					// The address is an embedded JSON object
					final String address = restaurant.getString("address");

					final JSONTokener addrTokener = new JSONTokener(address);
					final JSONObject oAddress = (JSONObject) addrTokener
							.nextValue();

					final String building = oAddress.getString("building");
					final String street = oAddress.getString("street");
					final String zipCode = oAddress.getString("zipCode");

					// back to our restaurants
					final String phone = restaurant.getString("phone");
					
					final String cuisineCode = restaurant
							.getString("cuisineCode");
					
					String gradeName = "NOT_YET_GRADED";
					
					if(restaurant.has("currentGrade")) {
						gradeName = restaurant.getString("currentGrade");
					}
					
					// NOT findByCode
					final Grade currentGrade = Grade.findByName(gradeName);

					final Date gradeDate;
					String dateString = "";

					if (restaurant.has("gradeDate")) {
						dateString = restaurant.getString("gradeDate");
					}

					if (dateString == null || dateString == "") {
						gradeDate = null;
					} else {

						// Sample date format:
						// Aug 30, 2012 9:12:15 AM
						gradeDate = simpleDateFormat.parse(dateString);

					}

					aRestaurants.add(new Restaurant(camis, restaurantName,
							borough, new Address(building, street, zipCode),
							phone, cuisineCode, currentGrade, gradeDate, "", ""));

					result.add(restaurantName);
				}
			} finally {

				urlConnection.disconnect();

				if (aRestaurants.size() > 0) {

					new RestaurantDatabase(context)
							.saveRestaurants(aRestaurants);
					status = aRestaurants.size() + " restaurants retrieved.";
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			status = "Error retrieving restaurants: " + e.getMessage() + " camis:" + camis;

		}

		return status;
	}

	protected void onPostExecute(String result) {

		Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

	}

}