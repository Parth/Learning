package org.skylight1.neny.android.database.model;


public class MealDayTime {
	private boolean lunch;
	private boolean dinner;
	private int dayOfWeek;
	
	public MealDayTime(int dayOfWeek){
		this(dayOfWeek,true,true);
	}
	
	public MealDayTime(int dayOfWeek,boolean isLunchSet,boolean isDinnerSet){
		lunch = isLunchSet;
		dinner = isDinnerSet;
		this.dayOfWeek = dayOfWeek;
	}

	public boolean isLunch() {
		return lunch;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

	public boolean isDinner() {
		return dinner;
	}

	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
