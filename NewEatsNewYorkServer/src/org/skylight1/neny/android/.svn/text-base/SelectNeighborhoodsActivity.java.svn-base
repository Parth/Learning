package org.skylight1.neny.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.skylight1.neny.android.database.dao.NeighborhoodPreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.Neighborhood;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SelectNeighborhoodsActivity extends Activity {
	public class ImageAdapter extends BaseAdapter {

		private final Context context;

		private final List<Integer> listOfActiveResourceIds;

		private List<Integer> listOfInactiveResourceIds;

		private final List<Boolean> listOfSelectedNeighborhoods;
		

		public ImageAdapter(final Context aContext, final List<Integer> aListOfActiveResourceIds, final List<Boolean> aListOfSelectedNeighborhoods,
				List<Integer> aListOfInactiveResiourceIds) {
			context = aContext;
			listOfActiveResourceIds = aListOfActiveResourceIds;
			listOfInactiveResourceIds = aListOfInactiveResiourceIds;
			listOfSelectedNeighborhoods = aListOfSelectedNeighborhoods;
		}

		@Override
		public int getCount() {
			return listOfActiveResourceIds.size();
		}

		@Override
		public Object getItem(int position) {
			return listOfActiveResourceIds.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			final ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setAdjustViewBounds(true);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View aV) {
					final boolean newState = !listOfSelectedNeighborhoods.get(position);
					listOfSelectedNeighborhoods.set(position, newState);

					preferencesDao.setPreferences(SelectNeighborhoodsActivity.mapImagePositionsToEnums(position).getLabel(), newState);
					imageView.setImageResource(listOfSelectedNeighborhoods.get(position) ? listOfActiveResourceIds.get(position) : listOfInactiveResourceIds
							.get(position));
				}

				
			});
			imageView.setImageResource(listOfSelectedNeighborhoods.get(position) ? listOfActiveResourceIds.get(position) : listOfInactiveResourceIds
					.get(position));
			return imageView;
		}
	}

	private List<Boolean> listOfSelectedNeighborhoods = new ArrayList<Boolean>();

	private PreferencesDao preferencesDao;

	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);
		
		

		setContentView(R.layout.neighborhoods_view);
		preferencesDao = new NeighborhoodPreferences(this);
		final GridView grid = (GridView) findViewById(R.id.neighbourhoodGrid);
		final List<Integer> neighborhoodActiveImageResources =
				Arrays.asList(R.drawable.n_inwood_active, R.drawable.n_harlem_active, R.drawable.n_east_harlem_active, R.drawable.n_uws_active, R.drawable.n_ues_active, R.drawable.n_chelsea_active, R.drawable.n_gramercy_active, R.drawable.n_greenwich_soho_active, R.drawable.n_les_active, R.drawable.n_east_village_active, R.drawable.n_wall_st_active);
		final List<Integer> neighborhoodInactiveImageResources =
				Arrays.asList(R.drawable.n_inwood_inactive, R.drawable.n_harlem_inactive, R.drawable.n_east_harlem_inactive, R.drawable.n_uws_inactive, R.drawable.n_ues_inactive, R.drawable.n_chelsea_inactive, R.drawable.n_gramercy_inactive, R.drawable.n_greenwich_soho_inactive, R.drawable.n_les_inactive, R.drawable.n_east_village_inactive, R.drawable.n_wall_st_inactive);
		for (final int dummy : neighborhoodActiveImageResources) {
			listOfSelectedNeighborhoods.add(false);
		}

		// load preferences
		listOfSelectedNeighborhoods = new ArrayList<Boolean>();
		for (int i = 0; i < neighborhoodActiveImageResources.size(); i++) {
			//listOfSelectedNeighborhoods.add(preferences.getBoolean(String.valueOf(i), true));
			listOfSelectedNeighborhoods.add(preferencesDao.getPreference(mapImagePositionsToEnums(i).getLabel(), true));
		}

		grid.setAdapter(new ImageAdapter(this, neighborhoodActiveImageResources, listOfSelectedNeighborhoods, neighborhoodInactiveImageResources));
	}
	//Is there a better way of doing this?
	public static Neighborhood mapImagePositionsToEnums(int position) {
		Neighborhood neighborhood = null;
		switch(position){
		case 0 :{ neighborhood = Neighborhood.INWOOD;break;}
		case 1 :{ neighborhood = Neighborhood.HARLEM;break;}
		case 2 :{ neighborhood = Neighborhood.EAST_HARLEM;break;}
		case 3 :{ neighborhood = Neighborhood.UWS;break;}
		case 4 :{ neighborhood = Neighborhood.UES;break;}
		case 5 :{ neighborhood = Neighborhood.CHELSEA;break;}
		case 6 :{ neighborhood = Neighborhood.GRAMERCY;break;}
		case 7 :{ neighborhood = Neighborhood.GREENWICH_SOHO;break;}
		case 8 :{ neighborhood = Neighborhood.LES;break;}
		case 9 :{ neighborhood = Neighborhood.EAST_VILLAGE;break;}
		case 10:{ neighborhood = Neighborhood.WALL_ST;break;}
		}
		return neighborhood;
	}
}
     