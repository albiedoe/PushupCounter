package albie.development.pushupcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import albie.development.pushupcounter.R;

public class OverviewActivity extends Activity {

	Intent intent;
	Intent intent_base;
	SQLiteDatabase db;
	DatabaseHandler dbHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overview);
		
		intent = new Intent(getBaseContext(), WorkoutActivity.class);
		intent_base = new Intent(getBaseContext(), PushupActivity.class);
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		setContentView(R.layout.overview);
		createOverview();
	}

	public void goBack(View view) {
		super.onBackPressed();
	}

	public void createOverview() {
		dbHandler = new DatabaseHandler(this);
		db = dbHandler.getReadableDatabase();
		String[] projection = { "max_pushups", "completed_workouts" };

		// How you want the results sorted in the resulting Cursor
		Cursor c = db.query("user", // The table to query
				projection, // The columns to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // The sort order
				);
		c.moveToFirst();
		int maxPushups = c.getInt(c.getColumnIndexOrThrow("max_pushups"));
		int completedWorkouts = c.getInt(c
				.getColumnIndexOrThrow("completed_workouts"));
		intent.putExtra("workouts_completed", completedWorkouts);


		// Build and fill arrayLists that of all views
		ArrayList<ImageView> imageList = getAllImages();
		ArrayList<ArrayList<TextView>> textList = getAllText(maxPushups);

		int rowCount = 0;

		for (int i = 0; i < imageList.size(); i++) {
			ImageView iv = imageList.get(i);
			// Initialize First Row
			if (rowCount < completedWorkouts) {
				iv.setImageResource(R.drawable.complete);
				rowCount++;
			} else if (rowCount == completedWorkouts) 
			{			
				// Set onClick Listener
				//for baseline
				if(rowCount == (imageList.size()-1))
				{
					OnClickListener onclicklistener = new OnClickListener() {					
					    @Override
					    public void onClick(View v) {				    	
							startActivity(intent_base);
					        }
					    };
					    textList.get(rowCount).get(0).setOnClickListener(onclicklistener);
					    iv.setOnClickListener(onclicklistener);
				}
				//for everything else
				else
				{
					iv.setImageResource(R.drawable.incomplete);	
					//get and pass all appropriate workout numbers to workout activity
					CharSequence workoutNumber1 = textList.get(rowCount).get(1).getText();
					CharSequence workoutNumber2 = textList.get(rowCount).get(2).getText();
					CharSequence workoutNumber3 = textList.get(rowCount).get(3).getText();
					CharSequence workoutNumber4 = textList.get(rowCount).get(4).getText();
					CharSequence workoutNumber5 = textList.get(rowCount).get(5).getText();
					//pass to Intent
					intent.putExtra("workoutNumber1", workoutNumber1);
					intent.putExtra("workoutNumber2", workoutNumber2);
					intent.putExtra("workoutNumber3", workoutNumber3);
					intent.putExtra("workoutNumber4", workoutNumber4);
					intent.putExtra("workoutNumber5", workoutNumber5);
					
				OnClickListener onclicklistener = new OnClickListener() {					
				    @Override
				    public void onClick(View v) {				    	
						startActivity(intent);
				        }
				    };
				    textList.get(rowCount).get(0).setOnClickListener(onclicklistener);
				    iv.setOnClickListener(onclicklistener);
				}
				rowCount++;
		}		 
		else {
				// change to gray circle
				iv.setImageResource(R.drawable.incomplete_gray);
				for(TextView tv: textList.get(rowCount))
				{
					tv.setTextColor(getResources().getColor(R.color.grayed_out));
				}
				rowCount++;
			}
		}
		db.close();

	}

	private ArrayList<ImageView> getAllImages() {
		// get All ImageViews
		ImageView imageViewW1D1 = (ImageView) findViewById(R.id.complete);
		ImageView imageViewW1D2 = (ImageView) findViewById(R.id.complete2);
		ImageView imageViewW1D3 = (ImageView) findViewById(R.id.complete3);
		ImageView imageViewW2D1 = (ImageView) findViewById(R.id.complete4);
		ImageView imageViewW2D2 = (ImageView) findViewById(R.id.complete5);
		ImageView imageViewW2D3 = (ImageView) findViewById(R.id.complete6);
		ImageView imageViewW3D1 = (ImageView) findViewById(R.id.complete7);
		ImageView imageViewW3D2 = (ImageView) findViewById(R.id.complete8);
		ImageView imageViewW3D3 = (ImageView) findViewById(R.id.complete9);
		ImageView pushup = (ImageView) findViewById(R.id.pushup_rowImage);
		// Establish ArrayList
		ArrayList<ImageView> imageList = new ArrayList<ImageView>();
		imageList.add(imageViewW1D1);
		imageList.add(imageViewW1D2);
		imageList.add(imageViewW1D3);
		imageList.add(imageViewW2D1);
		imageList.add(imageViewW2D2);
		imageList.add(imageViewW2D3);
		imageList.add(imageViewW3D1);
		imageList.add(imageViewW3D2);
		imageList.add(imageViewW3D3);
		imageList.add(pushup);

		return imageList;
	}

	private ArrayList<ArrayList<TextView>> getAllText(int maxPushups) {

		ArrayList<ArrayList<TextView>> textList = new ArrayList<ArrayList<TextView>>();

		// get All TextViews
		// Row1
		ArrayList<TextView> rowList_1 = new ArrayList<TextView>();
		TextView textViewW1D1_1 = (TextView) findViewById(R.id.week1day1);
		TextView textViewW1D1_2 = (TextView) findViewById(R.id.a2);
		textViewW1D1_2.setText(Integer.toString((int) (maxPushups*.75)));
		TextView textViewW1D1_3 = (TextView) findViewById(R.id.a3);
		textViewW1D1_3.setText(Integer.toString((int) (maxPushups*.75)));
		TextView textViewW1D1_4 = (TextView) findViewById(R.id.a4);
		textViewW1D1_4.setText(Integer.toString((int) (maxPushups*.5)));
		TextView textViewW1D1_5 = (TextView) findViewById(R.id.a5);
		textViewW1D1_5.setText(Integer.toString((int) (maxPushups*.5)));
		TextView textViewW1D1_6 = (TextView) findViewById(R.id.a6);
		textViewW1D1_6.setText(Integer.toString((int) (maxPushups*.65)));
		rowList_1.add(textViewW1D1_1);
		rowList_1.add(textViewW1D1_2);
		rowList_1.add(textViewW1D1_3);
		rowList_1.add(textViewW1D1_4);
		rowList_1.add(textViewW1D1_5);
		rowList_1.add(textViewW1D1_6);
		textList.add(rowList_1);

		// Row2
		ArrayList<TextView> rowList_2 = new ArrayList<TextView>();
		TextView textViewW1D2_1 = (TextView) findViewById(R.id.week1day2);
		TextView textViewW1D2_2 = (TextView) findViewById(R.id.b2);
		textViewW1D2_2.setText(Integer.toString((int) (maxPushups*.7)));
		TextView textViewW1D2_3 = (TextView) findViewById(R.id.b3);
		textViewW1D2_3.setText(Integer.toString((int) (maxPushups*.9)));
		TextView textViewW1D2_4 = (TextView) findViewById(R.id.b4);
		textViewW1D2_4.setText(Integer.toString((int) (maxPushups*.75)));
		TextView textViewW1D2_5 = (TextView) findViewById(R.id.b5);
		textViewW1D2_5.setText(Integer.toString((int) (maxPushups*.7)));
		TextView textViewW1D2_6 = (TextView) findViewById(R.id.b6);
		textViewW1D2_6.setText(Integer.toString((int) (maxPushups*.85)));
		rowList_2.add(textViewW1D2_1);
		rowList_2.add(textViewW1D2_2);
		rowList_2.add(textViewW1D2_3);
		rowList_2.add(textViewW1D2_4);
		rowList_2.add(textViewW1D2_5);
		rowList_2.add(textViewW1D2_6);
		textList.add(rowList_2);

		// Row3
		ArrayList<TextView> rowList_3 = new ArrayList<TextView>();
		TextView textViewW1D3_1 = (TextView) findViewById(R.id.week1day3);
		TextView textViewW1D3_2 = (TextView) findViewById(R.id.c2);
		textViewW1D3_2.setText(Integer.toString((int) (maxPushups*.9)));
		TextView textViewW1D3_3 = (TextView) findViewById(R.id.c3);
		textViewW1D3_3.setText(Integer.toString((int) (maxPushups*1.1)));
		TextView textViewW1D3_4 = (TextView) findViewById(R.id.c4);
		textViewW1D3_4.setText(Integer.toString((int) (maxPushups*.8)));
		TextView textViewW1D3_5 = (TextView) findViewById(R.id.c5);
		textViewW1D3_5.setText(Integer.toString((int) (maxPushups*.8)));
		TextView textViewW1D3_6 = (TextView) findViewById(R.id.c6);
		textViewW1D3_6.setText(Integer.toString((int) (maxPushups*1.1)));
		rowList_3.add(textViewW1D3_1);
		rowList_3.add(textViewW1D3_2);
		rowList_3.add(textViewW1D3_3);
		rowList_3.add(textViewW1D3_4);
		rowList_3.add(textViewW1D3_5);
		rowList_3.add(textViewW1D3_6);
		textList.add(rowList_3);

		// Row4
		ArrayList<TextView> rowList_4 = new ArrayList<TextView>();
		TextView textViewW2D1_1 = (TextView) findViewById(R.id.week2day1);
		TextView textViewW2D1_2 = (TextView) findViewById(R.id.d2);
		textViewW2D1_2.setText(Integer.toString((int) (maxPushups*1.0)));
		TextView textViewW2D1_3 = (TextView) findViewById(R.id.d3);
		textViewW2D1_3.setText(Integer.toString((int) (maxPushups*1.2)));
		TextView textViewW2D1_4 = (TextView) findViewById(R.id.d4);
		textViewW2D1_4.setText(Integer.toString((int) (maxPushups*.9)));
		TextView textViewW2D1_5 = (TextView) findViewById(R.id.d5);
		textViewW2D1_5.setText(Integer.toString((int) (maxPushups*.9)));
		TextView textViewW2D1_6 = (TextView) findViewById(R.id.d6);
		textViewW2D1_6.setText(Integer.toString((int) (maxPushups*1.2)));
		rowList_4.add(textViewW2D1_1);
		rowList_4.add(textViewW2D1_2);
		rowList_4.add(textViewW2D1_3);
		rowList_4.add(textViewW2D1_4);
		rowList_4.add(textViewW2D1_5);
		rowList_4.add(textViewW2D1_6);
		textList.add(rowList_4);
		
		
		// Row5
				ArrayList<TextView> rowList_5 = new ArrayList<TextView>();
				TextView textViewW2D2_1 = (TextView) findViewById(R.id.week2day2);
				TextView textViewW2D2_2 = (TextView) findViewById(R.id.e2);
				textViewW2D2_2.setText(Integer.toString((int) (maxPushups*1.1)));
				TextView textViewW2D2_3 = (TextView) findViewById(R.id.e3);
				textViewW2D2_3.setText(Integer.toString((int) (maxPushups*1.3)));
				TextView textViewW2D2_4 = (TextView) findViewById(R.id.e4);
				textViewW2D2_4.setText(Integer.toString((int) (maxPushups*1.0)));
				TextView textViewW2D2_5 = (TextView) findViewById(R.id.e5);
				textViewW2D2_5.setText(Integer.toString((int) (maxPushups*1.0)));
				TextView textViewW2D2_6 = (TextView) findViewById(R.id.e6);
				textViewW2D2_6.setText(Integer.toString((int) (maxPushups*1.5)));
				rowList_5.add(textViewW2D2_1);
				rowList_5.add(textViewW2D2_2);
				rowList_5.add(textViewW2D2_3);
				rowList_5.add(textViewW2D2_4);
				rowList_5.add(textViewW2D2_5);
				rowList_5.add(textViewW2D2_6);
				textList.add(rowList_5);
				
				// Row6
				ArrayList<TextView> rowList_6 = new ArrayList<TextView>();
				TextView textViewW2D3_1 = (TextView) findViewById(R.id.week2day3);
				TextView textViewW2D3_2 = (TextView) findViewById(R.id.f2);
				textViewW2D3_2.setText(Integer.toString((int) (maxPushups*1.3)));
				TextView textViewW2D3_3 = (TextView) findViewById(R.id.f3);
				textViewW2D3_3.setText(Integer.toString((int) (maxPushups*1.5)));
				TextView textViewW2D3_4 = (TextView) findViewById(R.id.f4);
				textViewW2D3_4.setText(Integer.toString((int) (maxPushups*1.2)));
				TextView textViewW2D3_5 = (TextView) findViewById(R.id.f5);
				textViewW2D3_5.setText(Integer.toString((int) (maxPushups*1.2)));
				TextView textViewW2D3_6 = (TextView) findViewById(R.id.f6);
				textViewW2D3_6.setText(Integer.toString((int) (maxPushups*1.8)));
				rowList_6.add(textViewW2D3_1);
				rowList_6.add(textViewW2D3_2);
				rowList_6.add(textViewW2D3_3);
				rowList_6.add(textViewW2D3_4);
				rowList_6.add(textViewW2D3_5);
				rowList_6.add(textViewW2D3_6);
				textList.add(rowList_6);
				
				// Row7
				ArrayList<TextView> rowList_7 = new ArrayList<TextView>();
				TextView textViewW3D1_1 = (TextView) findViewById(R.id.week3day1);
				TextView textViewW3D1_2 = (TextView) findViewById(R.id.g2);
				textViewW3D1_2.setText(Integer.toString((int) (maxPushups*1.3)));
				TextView textViewW3D1_3 = (TextView) findViewById(R.id.g3);
				textViewW3D1_3.setText(Integer.toString((int) (maxPushups*1.8)));
				TextView textViewW3D1_4 = (TextView) findViewById(R.id.g4);
				textViewW3D1_4.setText(Integer.toString((int) (maxPushups*1.5)));
				TextView textViewW3D1_5 = (TextView) findViewById(R.id.g5);
				textViewW3D1_5.setText(Integer.toString((int) (maxPushups*1.5)));
				TextView textViewW3D1_6 = (TextView) findViewById(R.id.g6);
				textViewW3D1_6.setText(Integer.toString((int) (maxPushups*2.0)));
				rowList_7.add(textViewW3D1_1);
				rowList_7.add(textViewW3D1_2);
				rowList_7.add(textViewW3D1_3);
				rowList_7.add(textViewW3D1_4);
				rowList_7.add(textViewW3D1_5);
				rowList_7.add(textViewW3D1_6);
				textList.add(rowList_7);
				
				// Row8
				ArrayList<TextView> rowList_8 = new ArrayList<TextView>();
				TextView textViewW3D2_1 = (TextView) findViewById(R.id.week3day2);
				TextView textViewW3D2_2 = (TextView) findViewById(R.id.h2);
				textViewW3D2_2.setText(Integer.toString((int) (maxPushups*1.6)));
				TextView textViewW3D2_3 = (TextView) findViewById(R.id.h3);
				textViewW3D2_3.setText(Integer.toString((int) (maxPushups*2.0)));
				TextView textViewW3D2_4 = (TextView) findViewById(R.id.h4);
				textViewW3D2_4.setText(Integer.toString((int) (maxPushups*1.6)));
				TextView textViewW3D2_5 = (TextView) findViewById(R.id.h5);
				textViewW3D2_5.setText(Integer.toString((int) (maxPushups*1.6)));
				TextView textViewW3D2_6 = (TextView) findViewById(R.id.h6);
				textViewW3D2_6.setText(Integer.toString((int) (maxPushups*2.2)));
				rowList_8.add(textViewW3D2_1);
				rowList_8.add(textViewW3D2_2);
				rowList_8.add(textViewW3D2_3);
				rowList_8.add(textViewW3D2_4);
				rowList_8.add(textViewW3D2_5);
				rowList_8.add(textViewW3D2_6);
				textList.add(rowList_8);
				
				// Row9
				ArrayList<TextView> rowList_9 = new ArrayList<TextView>();
				TextView textViewW3D3_1 = (TextView) findViewById(R.id.week3day3);
				TextView textViewW3D3_2 = (TextView) findViewById(R.id.i2);
				textViewW3D3_2.setText(Integer.toString((int) (maxPushups*1.8)));
				TextView textViewW3D3_3 = (TextView) findViewById(R.id.i3);
				textViewW3D3_3.setText(Integer.toString((int) (maxPushups*2.2)));
				TextView textViewW3D3_4 = (TextView) findViewById(R.id.i4);
				textViewW3D3_4.setText(Integer.toString((int) (maxPushups*1.7)));
				TextView textViewW3D3_5 = (TextView) findViewById(R.id.i5);
				textViewW3D3_5.setText(Integer.toString((int) (maxPushups*1.7)));
				TextView textViewW3D3_6 = (TextView) findViewById(R.id.i6);
				textViewW3D3_6.setText(Integer.toString((int) (maxPushups*2.5)));
				rowList_9.add(textViewW3D3_1);
				rowList_9.add(textViewW3D3_2);
				rowList_9.add(textViewW3D3_3);
				rowList_9.add(textViewW3D3_4);
				rowList_9.add(textViewW3D3_5);
				rowList_9.add(textViewW3D3_6);
				textList.add(rowList_9);
				
				//row 10
				ArrayList<TextView> rowList_10 = new ArrayList<TextView>();
				TextView retestBase = (TextView) findViewById(R.id.retestBase);
				rowList_10.add(retestBase);
				textList.add(rowList_10);
				
		return textList;
	}

}
