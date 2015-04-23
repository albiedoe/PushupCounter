package albie.development.pushupcounter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView{
	
	
	public CustomTextView(Context context, AttributeSet attrs) {
	super(context, attrs);
	//Typeface.createFromAsset doesn't work in the layout editor. Skipping...
	if (isInEditMode()) {
	return;
	}
	TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomTypeFace);
	String fontName = styledAttrs.getString(R.styleable.CustomTypeFace_typeface);
	styledAttrs.recycle();
	if (fontName != null) {
	Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
	setTypeface(typeface, Typeface.BOLD);
	}
	}
}
