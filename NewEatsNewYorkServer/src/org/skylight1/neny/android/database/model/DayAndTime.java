package org.skylight1.neny.android.database.model;

public enum DayAndTime {
	
	SUNDAY_LUNCH(11),
	SUNDAY_DINNER(12),
	MONDAY_LUNCH(21),
	MONDAY_DINNER(22),
	TUESDAY_LUNCH(31),
	TUESDAY_DINNER(32),
	WEDNESDAY_LUNCH(41),
	WEDNESDAY_DINNER(42),
	THURSDAY_LUNCH(51),
	THURSDAY_DINNER(52),
	FRIDAY_LUNCH(61),
	FRIDAY_DINNER(62),
	SATURDAY_LUNCH(71),
	SATURDAY_DINNER(72);
	
	
	
	private int code;
	private DayAndTime(final int aCode){
		code = aCode;
	}
	public int getCode() {
		return code;
	}
	
	public static DayAndTime findByCode(int code){
		for (final DayAndTime dayAndtime : values()) {
			if (dayAndtime.code == code) {
				return dayAndtime;
			}
		}
		return null;
	}

}
