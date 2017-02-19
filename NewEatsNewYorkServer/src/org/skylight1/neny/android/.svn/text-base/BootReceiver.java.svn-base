package org.skylight1.neny.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	static final String TAG = "BootReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG,"onReceive setAlarms");
		new AlarmUtils().setAlarms(context);	
	}
}