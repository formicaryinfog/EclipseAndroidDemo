package com.example.firstapp;

import com.example.fragment.DiscoverFragment;
import com.example.fragment.FriendsFragment;
import com.example.fragment.IndexFragment;
import com.example.fragment.ProfileFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Main2Activity extends FragmentActivity
{

	private RadioGroup main_bottom_group;
	
	Fragment indexFragment;
	Fragment discoverFragment;
	Fragment friendsFragment;
	Fragment profileFragment;
	
	private FragmentManager fragmentManager;
    //private FragmentTransaction transaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main2);
		
		fragmentManager = getSupportFragmentManager();
		
		// 初始化控件和声明事件
		main_bottom_group = (RadioGroup)findViewById(R.id.main_bottom_group);
		((RadioButton)main_bottom_group.findViewById(R.id.bottom_btn_index)).setChecked(true);
		
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		// 设置默认的Fragment
		setDefaultFragment();
		
		main_bottom_group.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

		
	}
	
	private void setDefaultFragment() {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		indexFragment = new IndexFragment();
		transaction.add(R.id.main_fragment, indexFragment);
		transaction.commit();
	}

	class MyOnCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			
			switch (checkedId) {
			case R.id.bottom_btn_index:
				if (indexFragment == null) {
					indexFragment = new IndexFragment();
				}
				transaction.replace(R.id.main_fragment, indexFragment);
				break;
			case R.id.bottom_btn_discover:
				if (discoverFragment == null) {
					discoverFragment = new DiscoverFragment();
				}
				transaction.replace(R.id.main_fragment, discoverFragment);
				break;
			case R.id.bottom_btn_friend:
				if (friendsFragment == null) {
					friendsFragment = new FriendsFragment();
				}
				transaction.replace(R.id.main_fragment, friendsFragment);
				break;
			case R.id.bottom_btn_profile:
				if (profileFragment == null) {
					profileFragment = new ProfileFragment();
				}
				transaction.replace(R.id.main_fragment, profileFragment);
				break;
	    	}
			transaction.commit();
		}		
	}	
	
}
