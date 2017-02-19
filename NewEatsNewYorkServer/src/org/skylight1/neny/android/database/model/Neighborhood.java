package org.skylight1.neny.android.database.model;

public enum Neighborhood {
	INWOOD("Inwood"), 
	HARLEM("Harlem"), 
	EAST_HARLEM("East Harlem"), 
	UWS("Upper West Side"), 
	UES("Upper East Side"), 
	CHELSEA("Chelsea"), 
	GRAMERCY("Gramercy"), 
	GREENWICH_SOHO("Greenwich/Soho"), 
	LES("Lower East Side"), 
	EAST_VILLAGE("East Village"), 
	WALL_ST("Wall St");

	private final String label;

	private Neighborhood(String aLabel) {
		label = aLabel;
	}

	public String getLabel() {
		return label;
	}
}
