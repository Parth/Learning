package org.skylight1.neny.android;

import java.util.Calendar;

import org.skylight1.neny.android.notification.RestaurantNotifier;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NewEatsNewYorkApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		new AlarmUtils().setAlarms(this);		
	}		
}
