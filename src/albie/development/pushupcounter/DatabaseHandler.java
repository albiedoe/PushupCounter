package albie.development.pushupcounter;


import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// path
	public static final String DB_PATH = "data/albie.development.pushupcounter/databases/";
	// Database Name
	private static final String DATABASE_NAME = "pushupcounter.sqlite";

	// table names
	private static final String TABLE_USER = "user";
	private static final String TABLE_WORKOUT = "workouts";

	// user Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_BASELINE = "baseline_flag";
	private static final String KEY_MAX_PUSHUPS = "max_pushups";
	private static final String KEY_COMPLETED_WORKOUTS = "completed_workouts";
	private static final String KEY_FIRSTDAY = "first_day";
	private static final String KEY_FIRSTDAY_FLAG = "first_day_flag";
	// workout table column names
	private static final String KEY_DAYS_SINCE_START = "days_since_start";
	private static final String KEY_CURRENT_COMPLETED_WORKOUTS = "current_completed";
	private static final String KEY_FINISH_ALL_FLAG = "finish_flag";
	private static final String KEY_TOTAL_PUSHUPS = "total_pushups";
	private static final String KEY_DATE = "date";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		// create user table
		db.execSQL("CREATE TABLE user (id INTEGER, "
				+ "baseline_flag INTEGER, max_pushups INTEGER, "
				+ "completed_workouts INTEGER, first_day INTEGER,"
				+ " first_day_flag INTEGER" + ");)");

		// create workout table
		db.execSQL("CREATE TABLE workouts (id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_DATE
				+ " TEXT, "
				+ KEY_DAYS_SINCE_START
				+ " INTEGER, "
				+ KEY_CURRENT_COMPLETED_WORKOUTS
				+ " INTEGER, "
				+ KEY_FINISH_ALL_FLAG
				+ " INTEGER, "
				+ KEY_TOTAL_PUSHUPS
				+ " INTEGER);)");
	}

	public void deleteTables(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
		onCreate(db);
		resetData();

	}

	public int getFirstDay() {
		SQLiteDatabase db = getReadableDatabase();
		String[] projection = { KEY_FIRSTDAY };

		Cursor c = db.query("user", // The table to query
				projection, // The columns to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // The sort order
				);

		c.moveToFirst();
		int firstDay = c.getInt(c.getColumnIndexOrThrow("first_day"));
		return firstDay;
	}

	public void setFirstDayCols(int firstDay) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("UPDATE user SET first_day=" + firstDay);
		db.execSQL("UPDATE user SET first_day_flag=" + 1);
		db.close();
	}
	
	public void updateCurrentWorkout(int pos) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("UPDATE user SET " + KEY_COMPLETED_WORKOUTS +" =" + pos);
		db.close();
	}

	public int getFirstDayFlag() {
		SQLiteDatabase db = getReadableDatabase();
		String[] projection = { KEY_FIRSTDAY_FLAG };

		Cursor c = db.query("user", // The table to query
				projection, // The columns to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // The sort order
				);

		c.moveToFirst();
		int firstDayFlag = c.getInt(c.getColumnIndexOrThrow("first_day_flag"));
		return firstDayFlag;
	}
	
	

	// Updating single contact
	public void updateUser(User user) {

		SQLiteDatabase db = this.getWritableDatabase();
		// updating row
		db.execSQL("UPDATE user SET completed_workouts="
				+ user.getCompletedWorkouts());

		db.close();
	}

	public void updateUser_baseline(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		/*
		 * ContentValues values = new ContentValues(); values.put(KEY_BASELINE,
		 * user.getBaseline()); values.put(KEY_MAX_PUSHUPS,
		 * user.getMaxPushups()); values.put(KEY_COMPLETED_WORKOUTS,
		 * user.getCompletedWorkouts());
		 */

		// updating rows
		db.execSQL("UPDATE user SET max_pushups=" + user.getMaxPushups());
		db.execSQL("UPDATE user SET baseline_flag=1");
		db.execSQL("UPDATE user SET "+KEY_COMPLETED_WORKOUTS+"=0");

		db.close();
	}

	public void addWorkout(Workout workout) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DATE, workout.getDateCompleted());
		values.put(KEY_DAYS_SINCE_START, workout.getDaysSinceStart());
		values.put(KEY_CURRENT_COMPLETED_WORKOUTS,
				workout.getCurrentCompletedWorkouts());
		values.put(KEY_FINISH_ALL_FLAG, workout.getFinished());
		values.put(KEY_TOTAL_PUSHUPS, workout.getTotalPushups());
		// updating rows
		db.insert(TABLE_WORKOUT, null, values);
		db.close();
	}
	
	
	// Getting single workout
		public Workout getWorkout(int id) {
				SQLiteDatabase db = this.getReadableDatabase();
				Workout workout;
				
				Cursor cursor = db.rawQuery("Select * FROM "+ TABLE_WORKOUT +" where id ="+id, null);
				if (cursor != null)
					cursor.moveToFirst();
				if(cursor.getCount()>0)
				{
					 workout = new Workout(
							Integer.parseInt(cursor.getString(0)), 	//id from cursor
							cursor.getString(1), 					//date from cursor
							Integer.parseInt(cursor.getString(2)),	//days since start from cursor
							Integer.parseInt(cursor.getString(3)), 	//Current Completed Workouts from cursor
							Integer.parseInt(cursor.getString(4)), 	//finish from cursor
							Integer.parseInt(cursor.getString(5)) 	//totalPushups from cursor
							);						   //firstDay from dbHandler
				//	 Log.d("getWorkoit", cursor.getString(1));
				//	 Log.d("getWorkout()", "id="+cursor.getString(0)+"CCW="+cursor.getString(3)+"finish="+ cursor.getString(4)+"total="+cursor.getString(5)+
				//			 "firstday="+Integer.toString(getFirstDay()));
					 return workout;
				}
				else
				{
					return null;
				}
			}

	public ArrayList<Workout> getAllWorkouts() {
		SQLiteDatabase db = getReadableDatabase();
		ArrayList<Workout> workouts = new ArrayList<Workout>();

		Cursor c = db.rawQuery("select *  from " + TABLE_WORKOUT, null);
		if (c != null) {
			for (int i = 1; i <= c.getCount(); i++) {			
				if(getWorkout(i) != null)
				{				
					workouts.add(getWorkout(i));
				}
			
				

			}
		}
		return workouts;
	}

	public boolean isCreated() {
		boolean check1 = false;
		boolean check2 = false;
		SQLiteDatabase db = this.getReadableDatabase();

		// checks if table user is created yet
		Cursor c = db.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ TABLE_USER + "'", null);
		if (c != null) {
			// if getCount is greater than 0 that means table user exists
			if (c.getCount() > 0) {
				check1 = true;
			}
			c.close();
		}

		Cursor c1 = db.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ TABLE_WORKOUT + "'", null);
		if (c1 != null) {
			if (c1.getCount() > 0) {
				check2 = true;
			}
			c1.close();
		}

		int rowCount = -1;
		// checks if table user exists, if the user row is there
		if (check1) {
			rowCount = getRowCount(TABLE_USER);
		}
		db.close();

		if (check1 && check2 && Integer.toString(rowCount).equals("1")) {
			return true;
		} else
			return false;

	}

	public int getBaselineFlag() {
		SQLiteDatabase db = this.getReadableDatabase();

		String[] projection = { "baseline_flag" };

		Cursor c = db.query("user", // The table to query
				projection, // The columns to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // The sort order
				);

		c.moveToFirst();
		int baselineId = c.getInt(c.getColumnIndexOrThrow("baseline_flag"));
		return baselineId;
	}

	public int getRowCount(String table_name) {
		String countQuery = "SELECT * FROM " + table_name;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();
		db.close();
		return cnt;
	}

	public void resetData() {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, 0);
		values.put(KEY_BASELINE, 0);
		values.put(KEY_MAX_PUSHUPS, 0);
		values.put(KEY_COMPLETED_WORKOUTS, 0);
		values.put(KEY_FIRSTDAY, 0);
		values.put(KEY_FIRSTDAY_FLAG, 0);

		// Insert the new row, 'returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(TABLE_USER, null, values);

		db.close();

	}

	

	// Getting single contact
	public User getUser(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID,
				KEY_BASELINE, KEY_MAX_PUSHUPS, KEY_COMPLETED_WORKOUTS }, KEY_ID
				+ "=?", new String[] { String.valueOf(id) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor
						.getString(2)), Integer.parseInt(cursor.getString(3)));
		// return contact
		return user;

	}
	/*
	 * // Deleting single contact public void deleteUser(User user) {
	 * SQLiteDatabase db = this.getWritableDatabase(); db.delete(TABLE_USER,
	 * KEY_ID + " = ?", new String[] { String.valueOf(user.getId()) });
	 * db.close(); }
	 * 
	 * // Getting All Contacts public List<User> getAllContacts() { return new
	 * ArrayList<User>(); }
	 * 
	 * // Getting contacts Count public int getUserCount() { return 4; }
	 * 
	 * /*
	 * 
	 * // Adding new contact public void addUser(User user) { SQLiteDatabase db
	 * = this.getWritableDatabase();
	 * 
	 * ContentValues values = new ContentValues(); values.put(KEY_BASELINE,
	 * user.getBaseline()); // Contact Name values.put(KEY_MAX_PUSHUPS,
	 * user.getMaxPushups()); // Contact Phone Number
	 * values.put(KEY_COMPLETED_WORKOUTS, user.getCompletedWorkouts()); //
	 * Contact Phone Number
	 * 
	 * // Inserting Row //db.insert(TABLE_USER, null, values); db.close(); //
	 * Closing database connection } public void addData(SQLiteDatabase db, int
	 * completedWorkouts) { Log.d("DB TEST", "begin addData"); ContentValues
	 * values = new ContentValues(); //values.put(KEY_ID, 1);
	 * values.put(KEY_BASELINE, 2); values.put(KEY_MAX_PUSHUPS, 10);
	 * values.put(KEY_COMPLETED_WORKOUTS, completedWorkouts);
	 * 
	 * // Insert the new row, 'returning the primary key value of the new row
	 * long newRowId; newRowId = db.insert( TABLE_USER, null, values);
	 * db.close();
	 * 
	 * }
	 * 
	 * public void addData(SQLiteDatabase db, int id, int baseline, int max, int
	 * completed) { Log.d("DB TEST", "begin addData"); ContentValues values =
	 * new ContentValues(); values.put(KEY_ID, id); values.put(KEY_BASELINE,
	 * baseline); values.put(KEY_MAX_PUSHUPS, max);
	 * values.put(KEY_COMPLETED_WORKOUTS, completed);
	 * 
	 * // Insert the new row, 'returning the primary key value of the new row
	 * long newRowId; newRowId = db.insert( TABLE_USER, null, values);
	 * db.close(); }
	 */
}
