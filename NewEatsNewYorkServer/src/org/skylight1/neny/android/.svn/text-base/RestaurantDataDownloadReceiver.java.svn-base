package org.skylight1.neny.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class RestaurantDataDownloadReceiver extends BroadcastReceiver {

	private static final String TAG = RestaurantDataDownloadReceiver.class.getSimpleName();
	
	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i(TAG, "onReceive");
		
		if (isNetworkAvailable(context)) {
			
			Log.i(TAG, "Getting new restaurant data");
		
            new GetNewRestaurantsTask(context).execute((String[]) null);
		} else {
			Log.i(TAG, "No internet connection");

		}
	}
	
	private boolean isNetworkAvailable(Context context) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}	

}
