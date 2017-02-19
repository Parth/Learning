package org.skylight1.neny.database.utils;

public class DatabaseQueryUtils {
	
	public static String buildSelectionString(final String columnName, int count){
		
		final StringBuilder aBuilder = new StringBuilder();
		aBuilder.append(columnName+ " IN (");
		for(int i=0;i<count;i++){
			aBuilder.append("?,");
		}
		if(count > 0)
			aBuilder.deleteCharAt(aBuilder.length()-1);
		aBuilder.append(")");
		return aBuilder.toString();
	}

}
