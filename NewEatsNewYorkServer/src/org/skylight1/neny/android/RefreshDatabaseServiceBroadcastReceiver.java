package org.skylight1.neny.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RefreshDatabaseServiceBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context aContext, Intent aIntent) {
		final Intent refreshIntent = new Intent(aContext, RefreshDatabaseService.class);
		aContext.startService(refreshIntent);
	}

}