package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.MyFragmentPagerAdapter;
import com.example.firstapp.R;

import android.R.integer;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class IndexFragment extends Fragment
{
	int green;
	int white;
	private ViewPager viewPager;
	private ArrayList<Fragment> fragmentsList;
	private RadioGroup index_title_group;
	
    public final static int num = 7 ; 
	
    Fragment choiceness;
    Fragment costume;
    Fragment beauty;
    Fragment homelife;
    Fragment infantmom;
    Fragment ranklist;
    Fragment interest;
    
    
	TextView index_title_show1;
	TextView index_title_show2;
	TextView index_title_show3;
	TextView index_title_show4;
	TextView index_title_show5;
	TextView index_title_show6;
	TextView index_title_show7;
	List<TextView> ivList;
	
	int prePosition;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.index, null);
		white = getResources().getColor(R.color.white);
		green = getResources().getColor(R.color.green);
		
		initTextView(view);
		initRadioGroup(view);
		initViewPager(view);
		
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void initTextView(View paramView) {
		prePosition = 0;
		index_title_group = (RadioGroup)paramView.findViewById(R.id.index_title_group);
		viewPager = (ViewPager)paramView.findViewById(R.id.index_viewpager);
		index_title_show1 = (TextView)paramView.findViewById(R.id.index_title_show1);
		index_title_show2 = (TextView)paramView.findViewById(R.id.index_title_show2);
		index_title_show3 = (TextView)paramView.findViewById(R.id.index_title_show3);
		index_title_show4 = (TextView)paramView.findViewById(R.id.index_title_show4);
		index_title_show5 = (TextView)paramView.findViewById(R.id.index_title_show5);
		index_title_show6 = (TextView)paramView.findViewById(R.id.index_title_show6);
		index_title_show7 = (TextView)paramView.findViewById(R.id.index_title_show7);
		ivList = new ArrayList<TextView>();
		ivList.add(index_title_show1);
		ivList.add(index_title_show2);
		ivList.add(index_title_show3);
		ivList.add(index_title_show4);
		ivList.add(index_title_show5);
		ivList.add(index_title_show6);
		ivList.add(index_title_show7);
	}
	
	private void initRadioGroup(View paramView) {
		index_title_group = (RadioGroup)paramView.findViewById(R.id.index_title_group);
		
		index_title_group.setOnCheckedChangeListener(new MyOnButtonChagedListener());
	}
	
	private void initViewPager(View parentView) {
		viewPager = (ViewPager) parentView.findViewById(R.id.index_viewpager);
        fragmentsList = new ArrayList<Fragment>();

        choiceness = new IndexchoicenessFragment();
        costume = new IndexcostumeFragment();
        beauty = new IndexbeautyFragment();
        homelife = new IndexhomelifeFragment();
        infantmom = new IndexinfantmomFragment();
        ranklist = new IndexranklistFragment();
        interest = new IndexinterestFragment();

        fragmentsList.add(choiceness);
        fragmentsList.add(costume);
        fragmentsList.add(beauty);
        fragmentsList.add(homelife);
        fragmentsList.add(infantmom);
        fragmentsList.add(ranklist);
        fragmentsList.add(interest);
    
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(0);
        
    }
	
	protected void SetTipColor(int index) {
		((TextView)IndexFragment.this.ivList.get(IndexFragment.this.prePosition)).setBackgroundColor(0xff00aa00);
        ((TextView)IndexFragment.this.ivList.get(index)).setBackgroundColor(0xffffffff);
        IndexFragment.this.prePosition = index;
    }
	
	class MyOnButtonChagedListener implements OnCheckedChangeListener
	{
	    public MyOnButtonChagedListener() {
	    }
	    
	    public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt) {
			int index = 0;			
			switch (paramInt)
			{
			case R.id.index_title_choiceness:
				index = 0;
				break;
			case R.id.index_title_costume:
				index = 1;
				break;
			case R.id.index_title_beauty:
				index = 2;
				break;
			case R.id.index_title_homelife:
				index = 3;
				break;
			case R.id.index_title_infantmom:
				index = 4;
				break;
			case R.id.index_title_rankinglist:
				index = 5;
				break;
			case R.id.index_title_interest:
				index = 6;
				break;
			}
			//SetTipColor(index);
			viewPager.setCurrentItem(index);
		}
	}
	
	class MyOnPageChangeListener implements OnPageChangeListener
	{

		@Override
		public void onPageScrollStateChanged(int paramInt) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int paramInt, float paramFloat, int paramInt2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int paramInt) {
			// TODO Auto-generated method stub
			((RadioButton)IndexFragment.this.index_title_group.getChildAt(paramInt)).setChecked(true);
	        ((TextView)IndexFragment.this.ivList.get(IndexFragment.this.prePosition)).setBackgroundColor(white);
	        ((TextView)IndexFragment.this.ivList.get(paramInt)).setBackgroundColor(green);
	        IndexFragment.this.prePosition = paramInt;
		}
	    
	}
	
}
