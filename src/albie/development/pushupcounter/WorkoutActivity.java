package albie.development.pushupcounter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutActivity extends Activity {

	// Instance Variables
	private int currentStage = 0;
	private int smallRadius = 0;
	private int mediumRadius = 0;
	private int largeRadius = 0;
	private int dayCount;
	private TextView tv;
	private TextView tv2;;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	private View myView;
	
//	private static boolean finished;
	private int finishedAllSets;
	private int totalPushups;
	private int firstDay;
	// local variables for manipulation
	TextView startMessage;
	TextView topMessage;
	TextView timerTextView;
	TextView secTextView;

	// painting things
	Display display;
	Paint paint;
	Canvas canvas;
	Bitmap bg;
	LinearLayout ll;


	CountDownTimer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout);
		//finished = false;
		finishedAllSets = 0;
		setRadius();
		initializeActivity();
		
	}

	public void goBack(View view) {
		super.onBackPressed();
	}

	@SuppressWarnings("deprecation")
	public boolean drawBubbles(View view) {
		myView= view;
		if (timer != null)
			timer.cancel();
		//finished = false;

		// Initialize Display
		display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		// Initialize Paint Brush
		paint = new Paint();
		paint.setColor(Color.parseColor("#f3606f"));
		bg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bg);

		// initialize coordinates
		int[] location = new int[2];
		int[] locationA = new int[2];
		Rect restRect = new Rect();
		int left =0;
		int right =0;


		switch (currentStage) {
		case 0:
			topMessage.setText("Perform Push Ups");
			startMessage.setText("Complete");
			secTextView.setText("Sec");
			startMessage.setTextColor(Color.WHITE);
			timerTextView.setTextColor(Color.WHITE);
			secTextView.setTextColor(Color.WHITE);
			drawBigCircle();
			// draw appropriate bubble
			
			tv.getLocationInWindow(location);
			Rect rTv = new Rect();
			tv.getLocalVisibleRect(rTv);
			paint.setColor(Color.parseColor("#9256b2"));
			canvas.drawCircle(location[0]+rTv.centerX(), location[1]+rTv.centerY(), mediumRadius, paint);
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);	
				}
			}.start();
			break;
		case 1:
			topMessage.setText("Rest");
			startMessage.setText("Continue");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
					}
			}.start();
			// draw appropriate bubble
			
			//getLeft Side
			tv2.getLocationInWindow(location);
			tv2.getLocalVisibleRect(restRect);
			left = restRect.left;
			
			//get Right side
			tv.getLocationInWindow(locationA);
			tv.getLocalVisibleRect(restRect);
			right = restRect.right;
			
			int centerX =(location[0]+ locationA[0]+ right) /2;
			
			//draw rest Circle
			paint.setColor(Color.parseColor("#f3606f"));
			canvas.drawCircle(centerX, location[1]+restRect.centerY(), smallRadius, paint);
			break;
		case 2:
			topMessage.setText("Perform Push Ups");
			startMessage.setText("Complete");
			secTextView.setText("Sec");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			tv2.getLocationInWindow(location);
			Rect rTv2 = new Rect();
			tv2.getLocalVisibleRect(rTv2);
			paint.setColor(Color.parseColor("#9256b2"));
			canvas.drawCircle(location[0]+rTv2.centerX(), location[1]+rTv2.centerY(), mediumRadius, paint);
			break;
		case 3:
			topMessage.setText("Rest");
			startMessage.setText("Continue");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			
			//getLeft Side
			tv3.getLocationInWindow(location);
			tv3.getLocalVisibleRect(restRect);
			left = restRect.left;
			
			//get Right side
			tv2.getLocationInWindow(locationA);
			tv2.getLocalVisibleRect(restRect);
			right = restRect.right;
			
			centerX =(location[0]+ locationA[0]+ right) /2;
			
			//draw rest Circle
			paint.setColor(Color.parseColor("#f3606f"));
			canvas.drawCircle(centerX, location[1]+restRect.centerY(), smallRadius, paint);
			break;
		case 4:
			topMessage.setText("Perform Push Ups");
			startMessage.setText("Complete");
			secTextView.setText("Sec");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			tv3.getLocationInWindow(location);
			Rect rTv3 = new Rect();
			tv3.getLocalVisibleRect(rTv3);
			paint.setColor(Color.parseColor("#9256b2"));
			canvas.drawCircle(location[0]+rTv3.centerX(), location[1]+rTv3.centerY(), mediumRadius, paint);
			break;
		case 5:
			topMessage.setText("Rest");
			startMessage.setText("Continue");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			
			//getLeft Side
			tv4.getLocationInWindow(location);
			tv4.getLocalVisibleRect(restRect);
			left = restRect.left;
			
			//get Right side
			tv3.getLocationInWindow(locationA);
			tv3.getLocalVisibleRect(restRect);
			right = restRect.right;
			
			centerX =(location[0]+ locationA[0]+ right) /2;
			
			//draw rest Circle
			paint.setColor(Color.parseColor("#f3606f"));
			canvas.drawCircle(centerX, location[1]+restRect.centerY(), smallRadius, paint);
			break;
		case 6:
			topMessage.setText("Perform Push Ups");
			startMessage.setText("Complete");
			secTextView.setText("Sec");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			tv4.getLocationInWindow(location);
			Rect rTv4 = new Rect();
			tv3.getLocalVisibleRect(rTv4);
			paint.setColor(Color.parseColor("#9256b2"));
			canvas.drawCircle(location[0]+rTv4.centerX(), location[1]+rTv4.centerY(), mediumRadius, paint);
			break;
		case 7:
			topMessage.setText("Rest");
			startMessage.setText("Continue");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			
			//getLeft Side
			tv5.getLocationInWindow(location);
			tv5.getLocalVisibleRect(restRect);
			left = restRect.left;
			
			//get Right side
			tv4.getLocationInWindow(locationA);
			tv4.getLocalVisibleRect(restRect);
			right = restRect.right;
			
			centerX =(location[0]+ locationA[0]+ right) /2;
			
			//draw rest Circle
			paint.setColor(Color.parseColor("#f3606f"));
			canvas.drawCircle(centerX, location[1]+restRect.centerY(), smallRadius, paint);
			break;
		case 8:
			topMessage.setText("Perform Push Ups");
			startMessage.setText("Complete");
			secTextView.setText("Sec");
			drawBigCircle();
			// start timer
			timer = new CountDownTimer(61000, 1000) {

				public void onTick(long millisUntilFinished) {
					timerTextView.setText(Long
							.toString(millisUntilFinished / 1000));
				}

				public void onFinish() {
					// automatically move to next state
					WorkoutActivity.this.drawBubbles(WorkoutActivity.this.myView);
				}
			}.start();
			// draw appropriate bubble
			tv5.getLocationInWindow(location);
			Rect rTv5 = new Rect();
			tv3.getLocalVisibleRect(rTv5);
			paint.setColor(Color.parseColor("#9256b2"));
			canvas.drawCircle(location[0]+rTv5.centerX(), location[1]+rTv5.centerY(), mediumRadius, paint);
			break;
		case 9:
			topMessage.setText("");
			startMessage.setText("Finish Workout");
			startMessage.setTextColor(Color.parseColor("#29aca3"));
			timerTextView.setText("");
			secTextView.setText("");
			break;
		case 10:
			//first dialog
			new AlertDialog.Builder(this)
			.setTitle("Workout Complete")
			.setMessage("Did you complete all pushups for each set?")
			.setIcon(android.R.drawable.ic_menu_save)
			//on yes
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	finishedAllSets = 1;
			    	//incremenets CW in User
			    	incrementCompletedWorkouts();
			    	//checks firstDay cols in User
			    	checkFirstDay();
			    	//log to Journal
			    	saveToJournal(new Workout(dayCount, finishedAllSets, totalPushups, firstDay));
			    	goBack(getWindow().getDecorView().findViewById(R.id.workoutPage));
			    }})
			    //on no
			 .setNegativeButton("No", new DialogInterface.OnClickListener() {

				    public void onClick(DialogInterface dialog, int whichButton) {			    	
				    	WorkoutActivity.this.showSecondDialog();
				    }}).show();	
			break;
		}

		// set to layout
		ll.setBackgroundDrawable(new BitmapDrawable(bg));

		currentStage++;
		return true;
	}
	
	public void showSecondDialog()
	{
		//second dialog
		new AlertDialog.Builder(this)
		.setTitle("Workout Complete")
		.setMessage("Would you like to mark this workout as complete anyway?")
		.setIcon(android.R.drawable.ic_menu_save)
		//on yes
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int whichButton) {	
		    	incrementCompletedWorkouts();
		    	//checks firstDay cols in User
		    	checkFirstDay();
		    	//log to journal
		    	saveToJournal(new Workout(dayCount, finishedAllSets, totalPushups, firstDay));
		    	goBack(getWindow().getDecorView().findViewById(R.id.workoutPage));
		    }})
		    //on no
		 .setNegativeButton("No", new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	//checks firstDay cols in User
			    	checkFirstDay();
			    	//log to journal()
			    	saveToJournal(new Workout(dayCount, finishedAllSets, totalPushups, firstDay));
			    	
			    	goBack(getWindow().getDecorView().findViewById(R.id.workoutPage));
			    }}).show();
	}
	
	//Saves changes to User table row
	private void incrementCompletedWorkouts() {
		//First save changes to User
		DatabaseHandler db = new DatabaseHandler(this);

		User prevUser = db.getUser(0);
		int oldCompletedWorkouts = prevUser.getCompletedWorkouts();
		
		prevUser.setCompletedWorkouts(oldCompletedWorkouts+1);
		db.updateUser(prevUser);
		db.close();
	}
	
	public void checkFirstDay()
	{
		DatabaseHandler db = new DatabaseHandler(this);
		//Deal with first Day stats logic here
				if(db.getFirstDayFlag()==0)
				{		
					SimpleDateFormat df = new SimpleDateFormat("D");
					String date = df.format(Calendar.getInstance().getTime());			 
					db.setFirstDayCols(Integer.parseInt(date));
				}
				firstDay = db.getFirstDay();
				db.close();
				
	}
	private void saveToJournal(Workout workout)
	{

		DatabaseHandler db = new DatabaseHandler(this);	
		db.addWorkout(workout);
		db.close();
	}
	
	

	private void initializeActivity() {
		tv = (TextView) findViewById(R.id.workoutNumber1);
		tv2 = (TextView) findViewById(R.id.workoutNumber2);
		tv3 = (TextView) findViewById(R.id.workoutNumber3);
		tv4 = (TextView) findViewById(R.id.workoutNumber4);
		tv5 = (TextView) findViewById(R.id.workoutNumber5);
		startMessage = (TextView) findViewById(R.id.start);
		startMessage.setTextColor(Color.parseColor("#29aca3"));
		topMessage = (TextView) findViewById(R.id.Message);
		timerTextView = (TextView) findViewById(R.id.timer);
		timerTextView.setTextColor(Color.BLACK);
		secTextView = (TextView) findViewById(R.id.sec);
		secTextView.setTextColor(Color.BLACK);
		ll = (LinearLayout) findViewById(R.id.workoutPage);

		// getting data from last activity
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String workoutNumber1 = extras.getString("workoutNumber1");
			tv.setText(workoutNumber1);
			String workoutNumber2 = extras.getString("workoutNumber2");
			tv2.setText(workoutNumber2);
			String workoutNumber3 = extras.getString("workoutNumber3");
			tv3.setText(workoutNumber3);
			String workoutNumber4 = extras.getString("workoutNumber4");
			tv4.setText(workoutNumber4);
			String workoutNumber5 = extras.getString("workoutNumber5");
			tv5.setText(workoutNumber5);
			dayCount = extras.getInt("workouts_completed");
			
			//calculating total pushups from workoutnumbers
			int wNumber1 = Integer.parseInt(workoutNumber1);
			int wNumber2 = Integer.parseInt(workoutNumber2);
			int wNumber3 = Integer.parseInt(workoutNumber3);
			int wNumber4 = Integer.parseInt(workoutNumber4);
			int wNumber5 = Integer.parseInt(workoutNumber5);

			totalPushups = wNumber1 + wNumber2 + wNumber3 + wNumber4 + wNumber5;
			// set Title bar According to dayCount
			TextView titleTv = (TextView) findViewById(R.id.default_week_day);
			;
			switch (dayCount) {
			case 0:
				titleTv.setText("Week 1 - Day 1");
				break;
			case 1:
				titleTv.setText("Week 1 - Day 2");
				break;
			case 2:
				titleTv.setText("Week 1 - Day 3");
				break;
			case 3:
				titleTv.setText("Week 2 - Day 1");
				break;
			case 4:
				titleTv.setText("Week 2 - Day 2");
				break;
			case 5:
				titleTv.setText("Week 2 - Day 3");
				break;
			case 6:
				titleTv.setText("Week 3 - Day 1");
				break;
			case 7:
				titleTv.setText("Week 3 - Day 2");
				break;
			case 8:
				titleTv.setText("Week 3 - Day 3");
				break;
			}

		}
	}

	private void drawBigCircle() {
		// draw big circle
		int[] location = new int[2];
		timerTextView.getLocationInWindow(location);
		Rect rSec = new Rect();
		secTextView.getLocalVisibleRect(rSec);
		Rect rTimer = new Rect();
		timerTextView.getLocalVisibleRect(rTimer);
		
		int centerX = rSec.centerX()+rTimer.centerX();
		int centerY = rSec.centerY()+rTimer.centerY();

		paint.setColor(Color.parseColor("#29aca3"));
		canvas.drawCircle(location[0]+centerX, location[1]+centerY, largeRadius, paint);
		
		paint.setColor(Color.BLACK);
		canvas.drawCircle(location[0]+centerX, location[1]+centerY, largeRadius-1, paint);

	}
	
	private void setRadius()
	{
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		if(height<=360)
		{
			smallRadius = 4;
			mediumRadius = 12;
			largeRadius = 80;
		}
		else if(height <= 520)
		{
			smallRadius = 5;
			mediumRadius = 15;
			largeRadius = 110;
		}
		else 		
		{
			smallRadius = 7;
			mediumRadius = 22;
			largeRadius = 150;
		}
	}
}
