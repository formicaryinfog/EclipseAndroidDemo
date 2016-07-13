package com.example.firstapp;

import com.example.loaddemo.helper.SpalshHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	SpalshHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView iv = (ImageView)findViewById(R.id.iv_bg);
		
		AlphaAnimation alpha = new AlphaAnimation(0.3f, 1f);
		//alpha.setDuration(3000);// 设置动画显示时间  
		iv.setAnimation(alpha);
		alpha.start();
		
		helper = helper.getInatance();
		
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(helper.isFirstGo(getApplicationContext())) {
					Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		
	}
}
