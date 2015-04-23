package albie.development.pushupcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import albie.development.pushupcounter.R;


public class JournalActivity extends Activity {
	
	ArrayList<Workout> workouts;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);
        fillView();

	}
	
	private void fillView()
	{
		//needed for textvies
		DatabaseHandler db = new DatabaseHandler(this);
		workouts = db.getAllWorkouts();
		int rows = db.getRowCount("workouts");
		LinearLayout layout = (LinearLayout) findViewById(R.id.entries);
		//ScrollView main = (ScrollView) findViewById(R.id.ScrollView);
		//stylings
		Typeface font = Typeface.createFromAsset(getAssets(),
				"Helvetica Neue Ultra Light/HelveticaNeue-UltraLight.otf");
		int paddingPixel = 15;
		float density = this.getResources().getDisplayMetrics().density;
		int paddingDp = (int)(paddingPixel * density);
		ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.pink));
		ForegroundColorSpan fcs_purple = new ForegroundColorSpan(getResources().getColor(R.color.purple));
		
		for(int i = 0; i<rows; i++)
		{
			// fill in any details dynamically here
			Workout w = workouts.get(i);
			
			//First Line
			TextView tv_dateTitle = new TextView(this);
			tv_dateTitle.setText(Integer.toString(i+1) + ". " + w.getDateCompleted());
			tv_dateTitle.setPadding(0, paddingDp, 0, 0);
			tv_dateTitle.setTextColor(getResources().getColor(R.color.teal));
			tv_dateTitle.setTextSize(22);
			//style
			tv_dateTitle.setTypeface(font, Typeface.BOLD);
			//Second Line
			TextView tv_workoutsCompleted = new TextView(this);
			Spannable sentence_weekDay = new SpannableString("Workout attempted: "+ getWeekDay(w.getCurrentCompletedWorkouts()));
			sentence_weekDay.setSpan(fcs_purple, 19, 33, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			tv_workoutsCompleted.setText(sentence_weekDay);
			tv_workoutsCompleted.setTypeface(font, Typeface.BOLD);
			tv_workoutsCompleted.setTextSize(17);
			//Third Line
			TextView tv_finishedAllSets = new TextView(this);
			if(w.getFinished()==0)
			{
				Spannable sentence = new SpannableString("User did not complete all sets. "+Integer.toString(w.getTotalPushups())+
						" pushups will be used for "+"data purposes");
				sentence.setSpan(fcs, 32, 34, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				tv_finishedAllSets.setText(sentence);
				
			}
			else
			{
				Spannable sentence = new SpannableString("User completed all sets for a total of "+
							Integer.toString(w.getTotalPushups())+ " pushups");
				sentence.setSpan(fcs, 39, 41, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				tv_finishedAllSets.setText(sentence);
			}
			tv_finishedAllSets.setTypeface(font, Typeface.BOLD);
			tv_finishedAllSets.setPadding(0, 0, 0, paddingDp);
			tv_finishedAllSets.setTextSize(17);
			
			
			// insert into main view
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			ll.setOrientation(LinearLayout.VERTICAL);
			
			ll.setId(workouts.get(i).getId());
			ll.addView(tv_dateTitle);
			ll.addView(tv_workoutsCompleted);
			ll.addView(tv_finishedAllSets);
			//View v = (View) ll.getParent();
			
			
			layout.addView(ll);
			//separator
			View ruler = new View(this);
			ruler.setBackgroundColor(getResources().getColor(R.color.off_white));
			layout.addView(ruler,
 			new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
		}
	
	}
	
	private String getWeekDay(int currentCompleted)
	{
		String weekDay = "";
		switch (currentCompleted)
		{
			case 0:
				weekDay = "Week 1 - Day 1";
				break;
			case 1:
				weekDay = "Week 1 - Day 2";
				break;
			case 2:
				weekDay = "Week 1 - Day 3";
				break;
			case 3:
				weekDay = "Week 2 - Day 1";
				break;
			case 4:
				weekDay = "Week 2 - Day 2";
				break;
			case 5:
				weekDay = "Week 2 - Day 3";
				break;
			case 6:
				weekDay = "Week 3 - Day 1";
				break;
			case 7:
				weekDay = "Week 3 - Day 3";
				break;
			case 8:
				weekDay = "Week 3 - Day 3";
				break;
		}		
		return weekDay;
	}
	
	public void goBack(View view)
	{
		super.onBackPressed();
	}
	
	
}
