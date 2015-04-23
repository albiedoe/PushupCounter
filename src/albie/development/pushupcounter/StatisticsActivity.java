package albie.development.pushupcounter;

import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import albie.development.pushupcounter.R;

public class StatisticsActivity extends Activity {
	private GraphView graph;
	private int totalPushups;
	private int minTotal;
	private int maxTotal;
	//private String dates[];
	private ArrayList<Workout> workouts;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.statistics);
		
		workouts = getAllWorkouts();
		drawGraph();
	}

	public void goBack(View view) {

		super.onBackPressed();
	}

	private void drawGraph()
	{

		 GraphViewData[] data = getData();
		// init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries(
				"",
				new GraphViewSeriesStyle(getResources().getColor(R.color.teal),3),
				data
			);
		 
		 graph = new LineGraphView(
		    this // context
		    , "" // heading
		);
		graph.addSeries(exampleSeries); // data
		//graph.getGraphViewStyle().setGridColor(getResources().getColor(R.color.grayed_out));
		graph.getGraphViewStyle().setHorizontalLabelsColor(getResources().getColor(R.color.purple));
		graph.getGraphViewStyle().setVerticalLabelsColor(getResources().getColor(R.color.purple));
		graph.getGraphViewStyle().setTextSize(12);
		
		//ymin = (int) (totalPushups*.6);
		//ymax = (int) (totalPushups*1.1);
		graph.setManualYAxisBounds(maxTotal*1.2, minTotal*.8);
		//graph.getGraphViewStyle().setNumHorizontalLabels(4);
		graph.getGraphViewStyle().setNumVerticalLabels(5);
		//graph.getGraphViewStyle().setVerticalLabelsWidth(300);

		
		
		 
		LinearLayout layout = (LinearLayout) findViewById(R.id.statistics_layout);
		layout.addView(graph);
		
	}
	private GraphViewData[] getData()
	{
		GraphViewData[] data = new GraphViewData[workouts.size()];
		//dates = new String[workouts.size()];
		int i=0;
		//ymin=0;
		//ymax=20;
		for(Workout w : workouts)
		{
			totalPushups=w.getTotalPushups();
			if(minTotal>totalPushups)
			{
				minTotal=totalPushups;
			}
			if(maxTotal<totalPushups)
			{
				maxTotal=totalPushups;
			}
			GraphViewData point = new GraphViewData(w.getDaysSinceStart(), w.getTotalPushups());
			data[i]=(point);
		//	dates[i]=w.getDateCompleted();
			i++;
		}
		//dummy data
		//data[0] = new GraphViewData(0,24);
		//data[1]= new GraphViewData(3,26);
		//data[2]= new GraphViewData(5,31);		

		return data;
	}
	
	private ArrayList<Workout> getAllWorkouts()
	{
		DatabaseHandler db = new DatabaseHandler(this);
		return db.getAllWorkouts();
	}
	
	/*private void printAllWorkouts()
	{
		if(workouts != null){
			for(Workout w : workouts)
			{
				Log.d("workout", w.toString());
			}
		}
		
	}*/
}
