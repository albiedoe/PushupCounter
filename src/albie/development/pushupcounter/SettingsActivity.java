package albie.development.pushupcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import albie.development.pushupcounter.R;

public class SettingsActivity extends Activity implements
		OnItemSelectedListener {

	Spinner weekDaySpinner;
	boolean firstClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		firstClick = true;
		weekDaySpinner = (Spinner) findViewById(R.id.weekDay_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.weekDayArray,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		weekDaySpinner.setAdapter(adapter);
		weekDaySpinner.setOnItemSelectedListener(this);

	}

	public void goBack(View view) {
		super.onBackPressed();
	}

	public void onItemSelected(AdapterView<?> parent, View view, final int pos,
			long id) {		
		
		final Intent intent = new Intent(this, MainActivity.class);
		final DatabaseHandler db = new DatabaseHandler(this);
		if(!firstClick)
		{
		new AlertDialog.Builder(this)
		.setTitle("Skip to workout")
		.setMessage("Do you really want to skip to workout "+getWeekDay(pos)+"?")
		.setIcon(android.R.drawable.alert_light_frame)
		.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int whichButton) {
						//parent.getItemAtPosition(pos);
						
						TextView tv = (TextView) findViewById(R.id.weekDayText);					
						tv.setText(Integer.toString(pos));
						//update db
						db.updateCurrentWorkout(pos);

						startActivity(intent);
					}
				}).setNegativeButton(android.R.string.no, null).show();
		}
		else
			firstClick = false;

	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

	public void reset(View view) {
		final DatabaseHandler db = new DatabaseHandler(this);
		final SQLiteDatabase sq = db.getWritableDatabase();
		final Intent intent = new Intent(this, MainActivity.class);

		new AlertDialog.Builder(this)
				.setTitle("Reset Data")
				.setMessage("Do you really want to reset your data?")
				.setIcon(android.R.drawable.alert_light_frame)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

								// These 3 lines recreate the DB
								db.deleteTables(sq);
								db.onCreate(sq);
								db.resetData();
								// db.close();

								startActivity(intent);
							}
						}).setNegativeButton(android.R.string.no, null).show();

	}

	public void rate(View view)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=albie.development.pushupcounter"));
		startActivity(intent);
	}
	
	private String getWeekDay(int pos) {
		String weekDay = "";
		switch (pos) {
		case 0:
			weekDay = "Week 1 - Day 1";
			return weekDay;
		case 1:
			weekDay = "Week 1 - Day 2";
			return weekDay;
		case 2:
			weekDay = "Week 1 - Day 3";
			return weekDay;
		case 3:
			weekDay = "Week 2 - Day 1";
			return weekDay;
		case 4:
			weekDay = "Week 2 - Day 2";
			return weekDay;
		case 5:
			weekDay = "Week 2 - Day 3";
			return weekDay;
		case 6:
			weekDay = "Week 3 - Day 1";
			return weekDay;
		case 7:
			weekDay = "Week 3 - Day 2";
			return weekDay;
		case 8:
			weekDay = "Week 3 - Day 3";
			return weekDay;

		}
		return "position not found";
	}
}
