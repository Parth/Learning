package org.skylight1.neny.android;


import org.skylight1.neny.android.database.dao.MealTimePreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.DayAndTime;
import org.skylight1.neny.android.database.model.MealDayTime;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SelectMealTimesActivity extends ListActivity{
  private MealtimeAdapter mealAdapter;
  private PreferencesDao mealTimePreferences;
  
  
  @Override
	protected void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);

		setContentView(R.layout.diningtimes_view);
		mealTimePreferences = new MealTimePreferences(this);
		MealDayTime[] mealtimes = createSomeData(mealTimePreferences);
		  
		mealAdapter = new MealtimeAdapter(this.getApplicationContext(),mealtimes,mealTimePreferences);
		ListView mealList = (ListView)findViewById(android.R.id.list);
		
		mealList.setAdapter(mealAdapter);
		
	}
  	
  
  private MealDayTime[] createSomeData(PreferencesDao preferences){
	  MealDayTime[] times =  new MealDayTime[7];
	  DayAndTime[] dayAndTimes = DayAndTime.values();
	  for(int i=0,j=1;i<dayAndTimes.length;i=i+2,j++){
		  DayAndTime lunch = dayAndTimes[i];
		  DayAndTime dinner = dayAndTimes[i+1];
		  MealDayTime mDt = new MealDayTime(j,preferences.getPreference(lunch.name(), true),preferences.getPreference(dinner.name(), true));
		  times[j-1]= mDt;
	  }
	  
	  return times;
  }
  
}
