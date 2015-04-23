package albie.development.pushupcounter;

public class User {

	private int id;
	private int baseline;
	private int maxPushups;
	private int completedWorkouts;
	private int firstDay;
	private int firstDayFlag;
	
	public User(int _id, int _baseline, int _maxPushups, int _completedWorkouts)
	{
		this.id = _id;
		this.baseline = _baseline;			
		this.maxPushups = _maxPushups;
		this.completedWorkouts = _completedWorkouts;		
	}
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int _id)
	{
		this.id=_id;
	}
	
	public int getBaseline()
	{
		return baseline;
	}
	
	public void setBaseline(int _baseline)
	{
		this.baseline = _baseline;
	}

	public int getMaxPushups()
	{
		return maxPushups;
	}
	
	public void setMaxPushups(int _maxPushups)
	{
		this.maxPushups = _maxPushups;
	}
	
	public int getCompletedWorkouts()
	{
		return completedWorkouts;
	}
	
	public void setCompletedWorkouts(int _completedWorkouts)
	{
		this.completedWorkouts = _completedWorkouts;
	}
	
	public int getFirstDay()
	{
		return firstDay;
	}
	
	public void setFirstDay(int _firstDay)
	{
		this.firstDay = _firstDay;
	}
	
	public int getFirstDayFlag()
	{
		return firstDayFlag;
	}
	
	public void setFirstDayFlag(int _flag)
	{
		this.firstDayFlag = _flag;
	}
}
