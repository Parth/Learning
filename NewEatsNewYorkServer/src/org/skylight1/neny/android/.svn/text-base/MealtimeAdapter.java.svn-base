package org.skylight1.neny.android;

import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.DayAndTime;
import org.skylight1.neny.android.database.model.MealDayTime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MealtimeAdapter extends ArrayAdapter<MealDayTime> {
	
	private Context aContext;
	private MealDayTime[] mealSelections;
	private PreferencesDao preferences;
	public MealtimeAdapter(Context context,MealDayTime[] selections,PreferencesDao preferences) {
		super(context, R.layout.diningtime_row,selections);
		aContext = context;
		this.mealSelections = selections;
		this.preferences = preferences;
	}
	
	 @Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
		 LayoutInflater inflater = (LayoutInflater) aContext
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 View rowView = inflater.inflate(R.layout.diningtime_row, parent, false);
		 CheckBox lunchBox = (CheckBox)rowView.findViewById(R.id.lunchCheckBox);
		 TextView mealDay = (TextView)rowView.findViewById(R.id.mealDay);
		 CheckBox dinnerBox = (CheckBox)rowView.findViewById(R.id.dinnerCheckBox);
		 
		 lunchBox.setChecked(mealSelections[position].isLunch());
		 mealDay.setText(mapToDay(mealSelections[position].getDayOfWeek()));
		 dinnerBox.setChecked(mealSelections[position].isDinner());
		 
		 lunchBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				preferences.setPreferences(mapPositionToMealTime(position,true).name(), isChecked);
			}
			 
		 });
		 dinnerBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					preferences.setPreferences(mapPositionToMealTime(position,false).name(), isChecked);
					
				}
				 
			 });
		 
		 return rowView;
	 }
	 
	 private CharSequence mapToDay(int dayOfWeek) {
		switch(dayOfWeek){
		case 1 : { return aContext.getString(R.string.sunday);}
		case 2 : { return aContext.getString(R.string.monday);}
		case 3 : { return aContext.getString(R.string.tuesday);}
		case 4 : { return aContext.getString(R.string.wednesday);}
		case 5 : { return aContext.getString(R.string.thursday);}
		case 6 : { return aContext.getString(R.string.friday);}
		case 7 : { return aContext.getString(R.string.saturday);}
		}
		return null;
	}

	private DayAndTime mapPositionToMealTime(int position, boolean isLunch){
		 DayAndTime result = null;
		 if(isLunch){
			 switch(position){
			 case 0: {result = DayAndTime.SUNDAY_LUNCH;break;}
			 case 1: {result = DayAndTime.MONDAY_LUNCH;break;}
			 case 2: {result = DayAndTime.TUESDAY_LUNCH;break;}
			 case 3: {result = DayAndTime.WEDNESDAY_LUNCH;break;}
			 case 4: {result = DayAndTime.THURSDAY_LUNCH;break;}
			 case 5: {result = DayAndTime.FRIDAY_LUNCH;break;}
			 case 6: {result = DayAndTime.SATURDAY_LUNCH;break;}
			 
			 }
		 }else{
			 switch(position){
			 case 0: {result = DayAndTime.SUNDAY_DINNER;break;}
			 case 1: {result = DayAndTime.MONDAY_DINNER;break;}
			 case 2: {result = DayAndTime.TUESDAY_DINNER;break;}
			 case 3: {result = DayAndTime.WEDNESDAY_DINNER;break;}
			 case 4: {result = DayAndTime.THURSDAY_DINNER;break;}
			 case 5: {result = DayAndTime.FRIDAY_DINNER;break;}
			 case 6: {result = DayAndTime.SATURDAY_DINNER;break;}
			 
			 }
		 }
		 return result;
	 }

}
