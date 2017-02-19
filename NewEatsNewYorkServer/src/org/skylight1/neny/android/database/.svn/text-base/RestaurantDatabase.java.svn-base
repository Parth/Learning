package org.skylight1.neny.android.database;

import static java.lang.String.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.skylight1.neny.android.database.model.Address;
import org.skylight1.neny.android.database.model.Borough;
import org.skylight1.neny.android.database.model.Cuisine;
import org.skylight1.neny.android.database.model.Grade;
import org.skylight1.neny.android.database.model.Neighborhood;
import org.skylight1.neny.android.database.model.Restaurant;
import org.skylight1.neny.database.utils.DatabaseQueryUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDatabase {
	private static final String TAG = RestaurantDatabase.class.getSimpleName();
	private static final int CURRENT_DATABASE_VERSION = 1;

	private static final int COL_CAMIS = 0;
	private static final int COL_DOING_BUSINESS_AS = 1;
	private static final int COL_BOROUGH = 2;
	private static final int COL_BUILDING = 3;
	private static final int COL_STREET = 4;
	private static final int COL_ZIP_CODE = 5;
	private static final int COL_PHONE = 6;
	private static final int COL_CUISINE_CODE = 7;
	private static final int COL_CURRENT_GRADE = 8;
	private static final int COL_GRADE_DATE = 9;

	private static final int COL_MAJOR_CUISINE = 10;
	private static final int COL_NEIGHBORHOOD = 11;

	private SQLiteOpenHelper sqLiteOpenHelper;

	// this is used to handle the parsing of the received gradeDate value
	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public RestaurantDatabase(final Context aContext) {
		sqLiteOpenHelper = new SQLiteOpenHelper(aContext, "restaurants", null,
				CURRENT_DATABASE_VERSION) {
			@Override
			public void onCreate(SQLiteDatabase aDb) {

				aDb.execSQL("create table restaurant (camis TEXT, doingBusinessAs TEXT, borough TEXT, building TEXT, street TEXT, zipCode TEXT, phone TEXT, cuisineCode TEXT, currentGrade TEXT, gradeDate TEXT)");
				aDb.execSQL("create table cuisine_map(cuisineCode TEXT, major_cuisine_name TEXT);");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('00','Eclectic');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('84','Eclectic');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('02','African');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('31','African');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('33','African');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('57','African');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('05','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('20','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('21','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('22','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('28','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('34','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('44','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('45','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('49','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('52','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('82','Asian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('07','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('08','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('12','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('14','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('29','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('41','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('42','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('43','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('51','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('58','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('60','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('70','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('74','Comfort');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('99','Eclectic');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('04','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('11','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('23','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('26','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('30','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('32','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('35','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('37','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('38','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('47','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('54','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('64','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('66','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('67','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('71','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('77','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('80','European');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('01','Indian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('09','Indian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('59','Indian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('48','Italian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('62','Italian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('63','Italian');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('46','Middle Eastern');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('50','Middle Eastern');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('56','Middle Eastern');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('83','Middle Eastern');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('03','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('10','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('15','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('16','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('18','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('24','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('25','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('27','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('36','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('39','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('55','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('68','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('69','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('72','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('73','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('75','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('76','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('78','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('81','North American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('06','Pacifica');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('40','Pacifica');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('65','Pacifica');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('13','South American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('17','South American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('19','South American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('53','South American');");
				aDb.execSQL("insert into cuisine_map (cuisineCode, major_cuisine_name) values ('61','South American');");

				aDb.execSQL("create table zipcode_neighborhood_map(zipCode TEXT, neighborhood TEXT)");

				// Upper West Side
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10023','Upper West Side');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10024','Upper West Side');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10025', 'Upper West Side');");

				// Upper East Side 10021, 10028, 10044, 10128
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10021', 'Upper East Side');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10028', 'Upper East Side');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10044', 'Upper East Side');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10128', 'Upper East Side');");
				
				// Greenwich / Soho
				// 10012, 10013, 10014
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10012','Greenwich/Soho');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10013','Greenwich/Soho');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10014', 'Greenwich/Soho');");
				
				// Harlem -- 10026, 10027, 10030, 10037, 10039
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10026', 'Harlem');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10027', 'Harlem');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10030', 'Harlem');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10037', 'Harlem');");
				aDb.execSQL("insert into zipcode_neighborhood_map(zipCode, neighborhood) values('10038', 'Harlem');");
				
				// da view
				aDb.execSQL("create view vw_restaurant as select r.*, c.major_cuisine_name, z.neighborhood from restaurant r, cuisine_map c, zipcode_neighborhood_map z where r.cuisineCode=c.cuisineCode and r.zipCode=z.zipCode");

			}

			@Override
			public void onUpgrade(SQLiteDatabase aDb, int anOldVersion,
					int aNewVersion) {
				throw new RuntimeException(
						format("No upgrade path exists for upgrading from version %d to version %d",
								anOldVersion, aNewVersion));
			}
		};
	}

	public Restaurant getRestaurantByCamis(String camis) {

		Restaurant restaurant = null;

		final SQLiteDatabase database = sqLiteOpenHelper.getReadableDatabase();

		try {

			Cursor cursor = database.query("vw_restaurant", null, "camis=?",
					new String[] { camis }, null, null, null);

			if (cursor != null) {

				cursor.moveToFirst();
				restaurant = cursorToRestaurant(cursor);

				cursor.close();
			}

		} finally {
			if (database != null) {
				database.close();
			}
		}

		return restaurant;
	}

	private Restaurant cursorToRestaurant(Cursor cursor) {

		try {
			final String camis = cursor.getString(COL_CAMIS);

			final String doingBusinessAs = cursor
					.getString(COL_DOING_BUSINESS_AS);

			final Borough borough = Borough.valueOf(cursor
					.getString(COL_BOROUGH));

			final String building = cursor.getString(COL_BUILDING);
			final String street = cursor.getString(COL_STREET);
			final String zipCode = cursor.getString(COL_ZIP_CODE);

			final Address address = new Address(building, street, zipCode);

			final String phone = cursor.getString(COL_PHONE);
			final String cuisineCode = cursor.getString(COL_CUISINE_CODE);
			final Grade currentGrade = Grade.valueOf(cursor
					.getString(COL_CURRENT_GRADE));

			Date gradeDate;
			final String gradeDateAsString = cursor.getString(COL_GRADE_DATE);

			if (gradeDateAsString == "") {
				gradeDate = null;
			} else {

				try {
					gradeDate = simpleDateFormat.parse(gradeDateAsString);
				} catch (ParseException e) {
					throw new RuntimeException(
							format("Unable to parse date %s for restaurant with camis %s",
									gradeDateAsString, camis), e);
				}
			}

			final String majorCuisine = cursor.getString(COL_MAJOR_CUISINE);
			final String neighborhood = cursor.getString(COL_NEIGHBORHOOD);
			
			final Restaurant restaurant = new Restaurant(camis,
					doingBusinessAs, borough, address, phone, cuisineCode,
					currentGrade, gradeDate, majorCuisine, neighborhood);

			return restaurant;

		} catch (Exception e) {
			return null;
		}

	}

	public ArrayList<Restaurant> getRestaurants() {

		final ArrayList<Restaurant> result = new ArrayList<Restaurant>();
		final SQLiteDatabase database = sqLiteOpenHelper.getReadableDatabase();
		try {
			final Cursor cursor = database.query("vw_restaurant", null, null,
					null, null, null, null);

			while (cursor.moveToNext()) {

				final Restaurant restaurant = cursorToRestaurant(cursor);
				if (restaurant != null) {
					result.add(restaurant);
				}
			}
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return result;
	}
	
	public ArrayList<Restaurant> getRestaurantsByUserPrefs(List<Neighborhood> neighborhoods,List<Cuisine> cuisines){
		final ArrayList<Restaurant> result = new ArrayList<Restaurant>();
		final SQLiteDatabase database = sqLiteOpenHelper.getReadableDatabase();
		
		final String selection =  DatabaseQueryUtils.buildSelectionString("major_cuisine_name",cuisines.size()) + " and " + 
		DatabaseQueryUtils.buildSelectionString("neighborhood",neighborhoods.size() );
		Log.d(TAG, selection);
		List<String> selections = new ArrayList<String>();
		for(Cuisine c : cuisines){
			selections.add(c.getLabel());
		}
		for(Neighborhood n : neighborhoods){
			selections.add(n.getLabel());
		}
		
		Log.d(TAG,selections.toString());
		try {
			final Cursor cursor = database.query("vw_restaurant", null, selection,
					selections.toArray(new String[selections.size()]), null, null, null);

			while (cursor.moveToNext()) {

				final Restaurant restaurant = cursorToRestaurant(cursor);
				if (restaurant != null) {
					result.add(restaurant);
				}
			}
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return result;
	}
	

	public int getStoredRestaurantCount() {
		
		final SQLiteDatabase database = sqLiteOpenHelper.getReadableDatabase();
		
		int count = 0;
		
		String sql = "select count(camis) as restaurantCount from vw_restaurant";
		
		try {
			
			Cursor mCursor = database.rawQuery(sql, null);
		    mCursor.moveToFirst();
		    
		    count = mCursor.getInt(0);
			
		    mCursor.close();
			
		} finally {
			if (database != null) {
				database.close();
			}
		}
		
		return count;
		
	}
	
	
	public void saveRestaurants(final List<Restaurant> aListOfRestaurants) {

		final SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

		try {
			// delete all of the existing rows
			database.delete("restaurant", null, null);

			// add the new ones
			for (final Restaurant restaurant : aListOfRestaurants) {
				final ContentValues contentValues = new ContentValues();
				contentValues.put("camis", restaurant.getCamis());
				contentValues.put("doingBusinessAs",
						restaurant.getDoingBusinessAs());
				contentValues.put("borough", restaurant.getBorough().name());
				contentValues.put("building", restaurant.getAddress()
						.getBuilding());
				contentValues
						.put("street", restaurant.getAddress().getStreet());
				contentValues.put("zipCode", restaurant.getAddress()
						.getZipCode());
				contentValues.put("phone", restaurant.getPhone());
				contentValues.put("cuisineCode", restaurant.getCuisineCode());
				contentValues.put("currentGrade", restaurant.getCurrentGrade()
						.name());

				Date gradeDate = restaurant.getGradeDate();

				String gradeDateString = "";

				if (gradeDate != null) {
					gradeDateString = simpleDateFormat.format(gradeDate);

				}

				contentValues.put("gradeDate", gradeDateString);
				final long result = database.insert("restaurant", null,
						contentValues);
				if (result == -1) {
					throw new RuntimeException(format(
							"Unable to insert restaurant %s", restaurant));
				}
			}
		} finally {
			if (database != null) {
				database.close();
			}
		}
	}
}
