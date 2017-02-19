package org.skylight1.neny.android;

import java.util.Calendar;

import org.skylight1.neny.android.notification.RestaurantNotifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmUtils {
	public static final String MEAL_TIME_EXTRA_NAME = "mealTime";
	public static final int LUNCH = 1;
	public static final int DINNER = 2;
	
	private void setAlarmForHour(final AlarmManager anAlarmManager, int anHour, int aMealTimeIndicator, Context aContext) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, anHour);
		if (calendar.before(Calendar.getInstance())) {
			calendar.add(Calendar.DATE, 1);
		}
		final long msToElevenAm = calendar.getTime().getTime() - System.currentTimeMillis();
		
		final Intent intent = new Intent(aContext, RestaurantNotifier.class);
		intent.putExtra(MEAL_TIME_EXTRA_NAME, aMealTimeIndicator);
		final PendingIntent broadcast = PendingIntent.getBroadcast(aContext, 0, intent, 0);
		anAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, msToElevenAm, AlarmManager.INTERVAL_DAY, broadcast);
	}

	public void setAlarms(Context aContext) {

		Intent alarmIntent = new Intent(aContext, RestaurantDataDownloadReceiver.class);
		
		PendingIntent pendingIntent = PendingIntent.getBroadcast(aContext, 0 , alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		final AlarmManager alarmManager = (AlarmManager) aContext.getSystemService(Context.ALARM_SERVICE);
		
		// TODO make this at midnight, and if no network then, then register for network state change
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
		
		setAlarmForHour(alarmManager, 11, AlarmUtils.LUNCH, aContext);
		setAlarmForHour(alarmManager, 17, AlarmUtils.DINNER, aContext);
	}
}
