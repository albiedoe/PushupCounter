package albie.development.pushupcounter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;
import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat") public class Workout {

	private int id=0;
	private String dateCompleted;
	private int daysSinceStart;
	private int currentCompletedWorkouts;
	private int finished;
	private int totalPushups;
	private static AtomicInteger nextId = new AtomicInteger();

	
	//to Pull from db
	public Workout(int _id, String _date, int _daysSinceStart, int _currentCompletedWorkouts, int _finished, int _totalPushups)
	{
		this.id = _id;
		this.dateCompleted= _date;
		this.daysSinceStart = _daysSinceStart;
		this.currentCompletedWorkouts = _currentCompletedWorkouts;
		this.finished = _finished;
		this.totalPushups = _totalPushups;
	}
	//to Create a new One
	public Workout(int _completedWorkouts, int _finished, int _total, int firstDay)
	{
		//id
		id = nextId.incrementAndGet();
		//set date
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
		String date = df.format(c.getTime());
		this.dateCompleted = date;

		//set daysSinceStart
		SimpleDateFormat df2 = new SimpleDateFormat("D");
		int dayofYear = Integer.parseInt(df2.format(c.getTime()).toString());
		if((dayofYear-firstDay)>=0)
		{
			daysSinceStart = dayofYear-firstDay;
		}
		else
		{
			daysSinceStart = (dayofYear+365)-firstDay;
		}
		
		//should be parameter
		this.currentCompletedWorkouts = _completedWorkouts;
		//parameter workout finished?
		this.finished = _finished;
		//parameter
		this.totalPushups = _total;
	}
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int _id)
	{
		this.id=_id;
	}
	
	public String getDateCompleted()
	{
		return dateCompleted;
	}
	
	public void setDateCompleted(String _date)
	{
		this.dateCompleted = _date;
	}

	public int getDaysSinceStart()
	{
		return daysSinceStart;
	}
	
	public void setDaysSinceStart(int _days)
	{
		this.daysSinceStart = _days;
	}
	
	public int getCurrentCompletedWorkouts()
	{
		return currentCompletedWorkouts;
	}
	
	public void setCurrentcompletedWorkouts(int _completedWorkouts)
	{
		this.currentCompletedWorkouts = _completedWorkouts;
	}
	
	public int getFinished()
	{
		return finished;
	}
	public void setFinished(int _newFinish)
	{
		finished = _newFinish;
	}
	
	public int getTotalPushups()
	{
		return totalPushups;
	}
	public void setTotalPushups(int _newTotalPushups)
	{
		totalPushups = _newTotalPushups;
	}
	public String toString()
	{
		return "this is a workout consisting of: id = " + this.id +
				"date completed this workout = "+this.dateCompleted+
				"Days since the start of this program = "+this.daysSinceStart+
				"Current Completed Workouts = "+ this.currentCompletedWorkouts+
				"finished all my sets = "+this.finished+
				"and total Pushups completed = "+this.totalPushups;
	}
}
