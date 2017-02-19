package org.skylight1.neny.android.notification;

import java.util.Calendar;

import org.skylight1.neny.android.AlarmUtils;
import org.skylight1.neny.android.R;
import org.skylight1.neny.android.ShowRestaurantListActivity;
import org.skylight1.neny.android.database.dao.MealTimePreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.DayAndTime;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RestaurantNotifier extends BroadcastReceiver {

	private PreferencesDao mealTimePreferences;

	@Override
	public void onReceive(Context aContext, Intent anIntent) {
		mealTimePreferences = new MealTimePreferences(aContext);
		final NotificationManager notificationManager = (NotificationManager) aContext.getSystemService(Context.NOTIFICATION_SERVICE);
		final Notification notification = new Notification(R.drawable.nyne_logo2, "Feed Me!", System.currentTimeMillis());

		final Intent showRestaurantsIntent = new Intent(aContext, ShowRestaurantListActivity.class);
		final PendingIntent broadcast = PendingIntent.getActivity(aContext, 0, showRestaurantsIntent, 0);

		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
		
		final int mealTime = anIntent.getIntExtra(AlarmUtils.MEAL_TIME_EXTRA_NAME, 0);
		final int dayAndTimeCode = Integer.valueOf(String.valueOf(day)+String.valueOf(mealTime));
		if(mealTimePreferences.getPreference(DayAndTime.findByCode(dayAndTimeCode).name(), false)){
			notification.setLatestEventInfo(aContext, "Feed me", "Feed me", broadcast);
			notificationManager.notify(1, notification);
		}
		
	}
}
