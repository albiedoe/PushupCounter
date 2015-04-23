package albie.development.pushupcounter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import albie.development.pushupcounter.R;

public class PushupActivity extends Activity {
	
	private DatabaseHandler db;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// standard start
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baselinetest);
		
		db = new DatabaseHandler(this);
	}
	
	public void saveBaseline(View v)
	{

		String maxPushups = (String) v.getTag();
		User user = new User(0, 1, Integer.parseInt(maxPushups),
				0);
		db.updateUser_baseline(user);
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	/*
	@SuppressLint("NewApi")
	private void drawBubbles() {
		// Get dimensions

		int width = 0;
		int height = 0;
		int xpadding = 110;
		int yadjustment = 200;
		Point size = new Point();

		WindowManager w = getWindowManager();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);
			width = size.x;
			height = size.y;
		} else {
			Display d = w.getDefaultDisplay();
			width = d.getWidth();
			height = d.getHeight();
		}

		// Initialize paint and bitmaps
		Paint paint = new Paint();
		paint.setColor(Color.parseColor("#f3606f"));
		Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bg);
		width = width - 20;

		// draw everything
		canvas.drawCircle(width - xpadding * 2, height + yadjustment, 50, paint);
		paint.setColor(Color.parseColor("#9256b2"));
		canvas.drawCircle(width - xpadding, height + yadjustment, 50, paint);
		paint.setColor(Color.parseColor("#29aca3"));
		paint.setColor(Color.parseColor("#e5ad2f"));
		canvas.drawCircle(width + xpadding, height + yadjustment, 50, paint);
		// set to layout
		LinearLayout ll = (LinearLayout) findViewById(R.id.rect);
		ll.setBackgroundDrawable(new BitmapDrawable(bg));

	}
*/
	public void goBack(View view) {
		super.onBackPressed();
	}

}