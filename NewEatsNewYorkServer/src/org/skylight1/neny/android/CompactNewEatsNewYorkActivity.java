package org.skylight1.neny.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.skylight1.neny.android.database.dao.CuisinePreferences;
import org.skylight1.neny.android.database.dao.MealTimePreferences;
import org.skylight1.neny.android.database.dao.NeighborhoodPreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.Cuisine;
import org.skylight1.neny.android.database.model.DayAndTime;
import org.skylight1.neny.android.database.model.MealDayTime;
import org.skylight1.neny.android.database.model.Neighborhood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CompactNewEatsNewYorkActivity extends Activity implements
		OnClickListener, OnCheckedChangeListener {

	public static final int SUNDAY = 0, MONDAY = 1, TUESDAY = 2, WEDNESDAY = 3,
			THURSDAY = 4, FRIDAY = 5, SATURDAY = 6, LUNCH = 0, DINER = 1;

	private Button eat;

	private LinearLayout selectCuisine, selectHood;
	private PreferencesDao hoodPreferences, cuisinePreferences,
			mealtimePreferences;

	private List<Boolean> listOfSelectedNeighborhoods = new ArrayList<Boolean>();
	private List<Integer> neighborhoodActiveImageResources,
			neighborhoodInactiveImageResources;
	private ImageView[] hoodImages = null;
	private int[] hoodImagesResID = null;

	private List<Boolean> listOfSelectedCuisines = new ArrayList<Boolean>();
	private List<Integer> cuisinesActiveImageResources,
			cuisinesInactiveImageResources;
	private ImageView[] cuisineImages = null;
	private int[] cuisineImagesResID = null;

	private CheckBox[][] timeChecks = new CheckBox[2][7];

	@Override
	public void onCreate(Bundle pm) {
		super.onCreate(pm);
		setContentView(R.layout.compact_home_view);

		// Just follow this to understand how the welcome screen works
		initRes();
		setUpHoodRes();
		setUpCuisineRes();
		assignHoodImageIds();
		assignCuisineImageIds();
		createHoodImages();
		createCuisineImages();
		setChecks();
		setOnClickListeners();
	}

	private void setChecks() {
		MealDayTime[] mealtimes = createSomeData(mealtimePreferences);
		for (int y = 0; y < 7; y++) {
			timeChecks[0][y].setChecked(mealtimes[y].isLunch());
			timeChecks[1][y].setChecked(mealtimes[y].isDinner());
		}

	}

	private void setUpCuisineRes() {
		cuisinesActiveImageResources = Arrays.asList(R.drawable.china_active,
				R.drawable.africa_active, R.drawable.italian_active,
				R.drawable.mayan_active, R.drawable.comfort_active,
				R.drawable.eclectic_active, R.drawable.eu_active,
				R.drawable.indian_active, R.drawable.middle_eastern_active,
				R.drawable.north_america_active, R.drawable.pacifica_active,
				R.drawable.vege_active

		);
		cuisinesInactiveImageResources = Arrays.asList(
				R.drawable.china_inactive, R.drawable.africa_inactive,
				R.drawable.italian_inactive, R.drawable.mayan_inactive,
				R.drawable.comfort_inactive, R.drawable.eclectic_inactive,
				R.drawable.eu_inactive, R.drawable.indian_inactive,
				R.drawable.middle_eastern_inactive,
				R.drawable.north_america_inactive,
				R.drawable.pacifica_inactive, R.drawable.vege_inactive);

		cuisineImages = new ImageView[12];
		cuisineImagesResID = new int[cuisineImages.length];

		for (final int dummy : cuisinesActiveImageResources) {
			listOfSelectedCuisines.add(false);
		}

		// load preferences
		listOfSelectedCuisines = new ArrayList<Boolean>();
		for (int i = 0; i < cuisinesActiveImageResources.size(); i++) {
			// Log.d("temp-cus", (mapImagePositionsToEnums(i).getLabel()));
			listOfSelectedCuisines.add(cuisinePreferences.getPreference(
					mapCuisineImagePositionsToEnums(i).getLabel(), true));
		}

	}

	private void createCuisineImages() {
		for (int i = 0; i < cuisineImages.length; i++) {
			cuisineImages[i] = new ImageView(this);
			cuisineImages[i].setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			cuisineImages[i].setAdjustViewBounds(true);
			cuisineImages[i].setImageResource(cuisineImagesResID[i]);
			cuisineImages[i].setId(i + hoodImages.length);
			selectCuisine.addView(cuisineImages[i]);
			cuisineImages[i].setOnClickListener(this);
		}
	}

	private void createHoodImages() {
		for (int i = 0; i < hoodImages.length; i++) {
			hoodImages[i] = new ImageView(this);
			hoodImages[i].setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			hoodImages[i].setAdjustViewBounds(true);
			hoodImages[i].setImageResource(hoodImagesResID[i]);
			hoodImages[i].setId(i);
			selectHood.addView(hoodImages[i]);
			hoodImages[i].setOnClickListener(this);
		}
	}

	private void assignHoodImageIds() {
		for (int i = 0; i < hoodImages.length; i++) {
			if (listOfSelectedNeighborhoods.get(i)) {
				hoodImagesResID[i] = neighborhoodActiveImageResources.get(i);
			} else {
				hoodImagesResID[i] = neighborhoodInactiveImageResources.get(i);
			}
		}
	}

	private void assignCuisineImageIds() {
		for (int i = 0; i < cuisineImages.length; i++) {
			if (listOfSelectedCuisines.get(i)) {
				cuisineImagesResID[i] = cuisinesActiveImageResources.get(i);
			} else {
				cuisineImagesResID[i] = cuisinesInactiveImageResources.get(i);
			}
		}
	}

	private void setUpHoodRes() {
		neighborhoodActiveImageResources = Arrays.asList(
				R.drawable.n_inwood_active, R.drawable.n_harlem_active,
				R.drawable.n_east_harlem_active, R.drawable.n_uws_active,
				R.drawable.n_ues_active, R.drawable.n_chelsea_active,
				R.drawable.n_gramercy_active,
				R.drawable.n_greenwich_soho_active, R.drawable.n_les_active,
				R.drawable.n_east_village_active, R.drawable.n_wall_st_active);

		neighborhoodInactiveImageResources = Arrays.asList(
				R.drawable.n_inwood_inactive, R.drawable.n_harlem_inactive,
				R.drawable.n_east_harlem_inactive, R.drawable.n_uws_inactive,
				R.drawable.n_ues_inactive, R.drawable.n_chelsea_inactive,
				R.drawable.n_gramercy_inactive,
				R.drawable.n_greenwich_soho_inactive,
				R.drawable.n_les_inactive, R.drawable.n_east_village_inactive,
				R.drawable.n_wall_st_inactive);

		hoodImages = new ImageView[neighborhoodActiveImageResources.size()];
		hoodImagesResID = new int[hoodImages.length];

		for (final int i : neighborhoodActiveImageResources) {
			listOfSelectedNeighborhoods.add(false);
		}

		// load preferences
		listOfSelectedNeighborhoods = new ArrayList<Boolean>();
		for (int i = 0; i < neighborhoodActiveImageResources.size(); i++) {
			listOfSelectedNeighborhoods.add(hoodPreferences.getPreference(
					mapHoodImagePositionsToEnums(i).getLabel(), true));
		}

	}

	public static Cuisine mapCuisineImagePositionsToEnums(int position) {
		Cuisine cuisine = null;
		switch (position) {
		case 0: {
			cuisine = Cuisine.ASIAN;
			break;
		}
		case 1: {
			cuisine = Cuisine.AFRICAN;
			break;
		}
		case 2: {
			cuisine = Cuisine.ITALIAN;
			break;
		}
		case 3: {
			cuisine = Cuisine.CENTRAL_AND_SOUTH_AMERICAN;
			break;
		}
		case 4: {
			cuisine = Cuisine.COMFORT_AND_SNACKS;
			break;
		}
		case 5: {
			cuisine = Cuisine.ECLECTIC;
			break;
		}
		case 6: {
			cuisine = Cuisine.EUROPEAN;
			break;
		}
		case 7: {
			cuisine = Cuisine.INDIAN_SUBCONTINENT;
			break;
		}
		case 8: {
			cuisine = Cuisine.MIDDLE_EASTERN;
			break;
		}
		case 9: {
			cuisine = Cuisine.US_NORTH_AMERICAN;
			break;
		}
		case 10: {
			cuisine = Cuisine.PACIFICA;
			break;
		}
		case 11: {
			cuisine = Cuisine.VEGETARIAN;
			break;
		}
		}
		return cuisine;
	}

	// Is there a better way of doing this?
	public static Neighborhood mapHoodImagePositionsToEnums(int position) {
		Neighborhood neighborhood = null;
		switch (position) {
		case 0: {
			neighborhood = Neighborhood.INWOOD;
			break;
		}
		case 1: {
			neighborhood = Neighborhood.HARLEM;
			break;
		}
		case 2: {
			neighborhood = Neighborhood.EAST_HARLEM;
			break;
		}
		case 3: {
			neighborhood = Neighborhood.UWS;
			break;
		}
		case 4: {
			neighborhood = Neighborhood.UES;
			break;
		}
		case 5: {
			neighborhood = Neighborhood.CHELSEA;
			break;
		}
		case 6: {
			neighborhood = Neighborhood.GRAMERCY;
			break;
		}
		case 7: {
			neighborhood = Neighborhood.GREENWICH_SOHO;
			break;
		}
		case 8: {
			neighborhood = Neighborhood.LES;
			break;
		}
		case 9: {
			neighborhood = Neighborhood.EAST_VILLAGE;
			break;
		}
		case 10: {
			neighborhood = Neighborhood.WALL_ST;
			break;
		}
		}
		return neighborhood;
	}

	private void setOnClickListeners() {
		eat.setOnClickListener(this);
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 7; y++) {
				timeChecks[x][y].setOnCheckedChangeListener(this);
			}
		}
	}

	private void initRes() {
		hoodPreferences = new NeighborhoodPreferences(this);
		cuisinePreferences = new CuisinePreferences(this);
		mealtimePreferences = new MealTimePreferences(this);

		selectCuisine = (LinearLayout) findViewById(R.id.home_select_cuisines);
		selectHood = (LinearLayout) findViewById(R.id.home_select_hoods);
		eat = (Button) findViewById(R.id.home_eat);

		timeChecks[LUNCH][SUNDAY] = (CheckBox) findViewById(R.id.home_sunday_lunch);
		timeChecks[DINER][SUNDAY] = (CheckBox) findViewById(R.id.home_sunday_diner);
		timeChecks[LUNCH][MONDAY] = (CheckBox) findViewById(R.id.home_monday_lunch);
		timeChecks[DINER][MONDAY] = (CheckBox) findViewById(R.id.home_monday_diner);
		timeChecks[LUNCH][TUESDAY] = (CheckBox) findViewById(R.id.home_tuesday_lunch);
		timeChecks[DINER][TUESDAY] = (CheckBox) findViewById(R.id.home_tuesday_diner);
		timeChecks[LUNCH][WEDNESDAY] = (CheckBox) findViewById(R.id.home_wednesday_lunch);
		timeChecks[DINER][WEDNESDAY] = (CheckBox) findViewById(R.id.home_wednesday_diner);
		timeChecks[LUNCH][THURSDAY] = (CheckBox) findViewById(R.id.home_thursday_lunch);
		timeChecks[DINER][THURSDAY] = (CheckBox) findViewById(R.id.home_thursday_diner);
		timeChecks[LUNCH][FRIDAY] = (CheckBox) findViewById(R.id.home_friday_lunch);
		timeChecks[DINER][FRIDAY] = (CheckBox) findViewById(R.id.home_friday_diner);
		timeChecks[LUNCH][SATURDAY] = (CheckBox) findViewById(R.id.home_saturday_lunch);
		timeChecks[DINER][SATURDAY] = (CheckBox) findViewById(R.id.home_saturday_diner);
	}

	private void refreshHood() {
		for (int i = 0; i < listOfSelectedNeighborhoods.size(); i++) {
			if (listOfSelectedNeighborhoods.get(i)) {
				hoodImages[i].setImageResource(neighborhoodActiveImageResources
						.get(i));
			} else {
				hoodImages[i]
						.setImageResource(neighborhoodInactiveImageResources
								.get(i));
			}
		}
	}

	private void refreshCuisine() {
		for (int i = 0; i < listOfSelectedCuisines.size(); i++) {
			if (listOfSelectedCuisines.get(i)) {
				cuisineImages[i].setImageResource(cuisinesActiveImageResources
						.get(i));
			} else {
				cuisineImages[i]
						.setImageResource(cuisinesInactiveImageResources.get(i));
			}
		}
	}

	@Override
	public void onClick(View v) {

		if (v.getId() >= 0 && v.getId() < hoodImages.length) {
			final boolean newState1 = !listOfSelectedNeighborhoods.get(v
					.getId());
			listOfSelectedNeighborhoods.set(v.getId(), newState1);

			hoodPreferences.setPreferences(
					mapHoodImagePositionsToEnums(v.getId()).getLabel(),
					newState1);

			refreshHood();

		}

		if (v.getId() >= hoodImages.length
				&& v.getId() < cuisineImages.length + hoodImages.length) {
			final boolean newState2 = !listOfSelectedCuisines.get(v.getId()
					- hoodImages.length);
			listOfSelectedCuisines
					.set(v.getId() - hoodImages.length, newState2);

			cuisinePreferences.setPreferences(
					mapCuisineImagePositionsToEnums(
							v.getId() - hoodImages.length).getLabel(),
					newState2);

			refreshCuisine();

		}
		if (v.getId() == R.id.home_eat) {
			showRestaurants();
		}

	}

	private void showRestaurants() {
		final Intent showRestaurantsIntent = new Intent(this,
				ShowRestaurantListActivity.class);
		startActivity(showRestaurantsIntent);
	}

	private DayAndTime mapPositionToMealTime(int position, boolean isLunch) {
		DayAndTime result = null;
		if (isLunch) {
			switch (position) {
			case 0: {
				result = DayAndTime.SUNDAY_LUNCH;
				break;
			}
			case 1: {
				result = DayAndTime.MONDAY_LUNCH;
				break;
			}
			case 2: {
				result = DayAndTime.TUESDAY_LUNCH;
				break;
			}
			case 3: {
				result = DayAndTime.WEDNESDAY_LUNCH;
				break;
			}
			case 4: {
				result = DayAndTime.THURSDAY_LUNCH;
				break;
			}
			case 5: {
				result = DayAndTime.FRIDAY_LUNCH;
				break;
			}
			case 6: {
				result = DayAndTime.SATURDAY_LUNCH;
				break;
			}

			}
		} else {
			switch (position) {
			case 0: {
				result = DayAndTime.SUNDAY_DINNER;
				break;
			}
			case 1: {
				result = DayAndTime.MONDAY_DINNER;
				break;
			}
			case 2: {
				result = DayAndTime.TUESDAY_DINNER;
				break;
			}
			case 3: {
				result = DayAndTime.WEDNESDAY_DINNER;
				break;
			}
			case 4: {
				result = DayAndTime.THURSDAY_DINNER;
				break;
			}
			case 5: {
				result = DayAndTime.FRIDAY_DINNER;
				break;
			}
			case 6: {
				result = DayAndTime.SATURDAY_DINNER;
				break;
			}

			}
		}
		return result;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 2; x++) {
				if (timeChecks[x][y].getId() == buttonView.getId()) {

					mealtimePreferences.setPreferences(
							mapPositionToMealTime(y, x == 0).name(), isChecked);

				}
			}
		}
	}

	// private int getCheckedY(int i) {
	// for (int y = 0; y < 7; y++) {
	// if (timeChecks[0][y].getId() == )
	// }
	//
	// return -1;
	// }

	private MealDayTime[] createSomeData(PreferencesDao preferences) {
		MealDayTime[] times = new MealDayTime[7];
		DayAndTime[] dayAndTimes = DayAndTime.values();
		for (int i = 0, j = 1; i < dayAndTimes.length; i = i + 2, j++) {
			DayAndTime lunch = dayAndTimes[i];
			DayAndTime dinner = dayAndTimes[i + 1];
			MealDayTime mDt = new MealDayTime(j, preferences.getPreference(
					lunch.name(), true), preferences.getPreference(
					dinner.name(), true));
			times[j - 1] = mDt;
		}

		return times;
	}
}
