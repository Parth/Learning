package org.skylight1.neny.android.database.dao;



import java.util.ArrayList;
import java.util.List;

import org.skylight1.neny.android.database.model.Neighborhood;

import android.content.Context;

public class NeighborhoodPreferences extends AbstractPreferences {

	
	public NeighborhoodPreferences(Context aContext){
		preferences = aContext.getSharedPreferences("neighborhoods", Context.MODE_PRIVATE);
	}
	
	public List<Neighborhood> getAllUserSelectedNeighborhoods(){
		List<Neighborhood> selectedNeighborhoods = new ArrayList<Neighborhood>();
		for(Neighborhood hood : Neighborhood.values()){
			if(getPreference(hood.getLabel(),true)){
				selectedNeighborhoods.add(hood);
			}
		}
		return selectedNeighborhoods;
	}

}
