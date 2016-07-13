package com.example.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

public class MyGridView extends GridView 
{
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	protected void onMeasure(int paramInt1, int paramInt2) {
		super.onMeasure(paramInt1,
				View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
	}
}
