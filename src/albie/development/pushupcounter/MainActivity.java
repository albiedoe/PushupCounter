package albie.development.pushupcounter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.*;

import albie.development.pushupcounter.R;

public class MainActivity extends Activity {

	// instance variables

	private DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		db = new DatabaseHandler(this);
		if (db.isCreated()) 
		{
		}
		// Recreate the db
		else {
			// These 3 lines recreate the DB
			SQLiteDatabase sq = db.getWritableDatabase();
			db.deleteTables(sq);
			db.onUpgrade(sq, 1, 2);
			db.close();
		}
		
	}

	public void openPushupActivity(View view) {
		/*
		 * 
		 * String[] projection = { //"id", "baseline_flag" //, "max_pushups",
		 * //"completed_workouts" };
		 * 
		 * // How you want the results sorted in the resulting Cursor
		 * 
		 * Cursor c = sqTwo.query("user", // The table to query projection, //
		 * The columns to return null, // The columns for the WHERE clause null,
		 * // The values for the WHERE clause null, // don't group the rows
		 * null, // don't filter by row groups null // The sort order );
		 * 
		 * c.moveToFirst(); int baselineId =
		 * c.getInt(c.getColumnIndexOrThrow("baseline_flag"));
		 */
		int baselineId = db.getBaselineFlag();
		if (baselineId == 1) {
			Intent intent = new Intent(this, OverviewActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, PushupActivity.class);
			startActivity(intent);
		}

	}

	public void openStatisticsActivity(View view) {
		Intent intent = new Intent(this, StatisticsActivity.class);
		startActivity(intent);
	}

	public void openSettingsActivity(View view) {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	
	public void openJournalActivity(View view) {
		Intent intent = new Intent(this, JournalActivity.class);
		startActivity(intent);
	}

	public void goBack(View view) {
		super.onBackPressed();
	}
}
