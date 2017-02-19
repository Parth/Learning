package com.example.contacttestersql;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

@SuppressLint("ParserError")
public class AndroidSQLiteTutorialActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_sqlite_tutorial);

		DatabaseHandler db = new DatabaseHandler(this);

		/**
		 * CRUD Operations
		 * */
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		db.addContact(new Contact("Ravi", "9100000000"));
		db.addContact(new Contact("Srinivas", "9199999999"));
		db.addContact(new Contact("Tommy", "9522222222"));
		db.addContact(new Contact("Karthik", "9533333333"));

		// Reading all contacts
		List<Contact> contacts = db.getAllContacts();

		for (Contact cn : contacts) {
			String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
					+ " ,Phone: " + cn.getPhoneNumber();
			// Writing Contacts to log
			System.out.println("Name: "+log);
		}
	}
}
