package com.example.firstapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class GuideActivity extends Activity {

	protected ViewPager viewPager;
	
	private ArrayList<ImageView> imgs;
	
	protected RadioGroup group;
	
	protected Button start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		viewPager =(ViewPager) findViewById(R.id.viewpager);
		group = (RadioGroup)findViewById(R.id.group);
		start = (Button)findViewById(R.id.start);
		
		initImgs();
		viewPager.setAdapter(new MyPagerAdapter(imgs));
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		group.setOnCheckedChangeListener(new MyOnButtonChagedListener());
		
		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				startActivity(new Intent(getApplicationContext(), Main2Activity.class));
			    finish();
				}
			});
		
	}
	
	private void initImgs() {
		imgs = new ArrayList<ImageView>();
		ImageView imgs1 = new ImageView(this, null);
        imgs1.setBackgroundResource(R.drawable.guidebg_1);
        imgs1.setImageResource(R.drawable.guideimg_1);
        imgs.add(imgs1);
        ImageView imgs2 = new ImageView(this, null);
        imgs2.setBackgroundResource(R.drawable.guidebg_2);
        imgs2.setImageResource(R.drawable.guideimg_2);
        imgs.add(imgs2);
        ImageView imgs3 = new ImageView(this, null);
        imgs3.setBackgroundResource(R.drawable.guidebg_3);
        imgs3.setImageResource(R.drawable.guideimg_3);
        imgs.add(imgs3);

	}
	
	protected void onDestroy() {
	    super.onDestroy();
	}
	
	class MyPagerAdapter extends PagerAdapter
	{
		private ArrayList<ImageView> imgs;

	    public MyPagerAdapter(ArrayList<ImageView> imgs) {
	    	this.imgs = imgs;
	    }
	    
	    /**
	     * ����positionλ�õĽ��� 
	     */
	    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
	    	paramViewGroup.removeView((View)paramObject);
	    }

	    /**
	     * ��õ�ǰ������
	     */
	    public int getCount() {
	    	return this.imgs.size();
	    }

	    /**
	     * ��ʼ��positionλ�õĽ��� 
	     */
	    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
	    	paramViewGroup.addView(imgs.get(paramInt));
	    	return imgs.get(paramInt);
	    }

	    /**
	     * �ж��Ƿ��ɶ������ɽ��� 
	     */
	    public boolean isViewFromObject(View paramView, Object paramObject) {
	    	return paramView == paramObject;
	    }
	}
	
	class MyOnPageChangeListener implements OnPageChangeListener
	{
	    public MyOnPageChangeListener() {
	    }

	    public void onPageScrollStateChanged(int paramInt) {
	    }

	    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
	    }

	    public void onPageSelected(int paramInt)
	    {
	    	((RadioButton)group.getChildAt(paramInt)).setChecked(true);
	    	if (paramInt == 2)
	    	{
	    		start.setVisibility(0);//0����˼�ǿɼ��� 
	    		return;
	      	}	
	      	start.setVisibility(8);//8����˼�ǲ��ɼ��ģ����Ҳ�ռ�ò��ֿռ�
	    }
	}
	
	
	class MyOnButtonChagedListener implements OnCheckedChangeListener
	{
	    public MyOnButtonChagedListener() {
	    }

	    public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt) {
	    	switch (paramInt)
	    	{
	    	case R.id.radio1:
	    		viewPager.setCurrentItem(0);
		        return;
		    case R.id.radio2:
		        viewPager.setCurrentItem(1);
		        return;
		    case R.id.radio3:
		    	viewPager.setCurrentItem(2);
		    	return;
		    default:
		    }
		}
	}
	
}
