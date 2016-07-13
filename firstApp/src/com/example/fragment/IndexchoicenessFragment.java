package com.example.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.IndexListAdapter;
import com.example.adapter.MyFragmentPagerAdapter;
import com.example.firstapp.R;
import com.example.myview.MyGridView;

public class IndexchoicenessFragment extends Fragment
{
	List<String> url;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView listView;
	private RequestQueue requestQueue;
	private ViewPager viewPager;
	private IndexListAdapter indexListAdapter;
	private List<Map<String, String>> listData;
	private Context context = null;
	private View gridView = null;
	private MyGridView myGridView;
	
	
	public void onAttach(Activity paramActivity) {
		context = paramActivity;
		super.onAttach(paramActivity);
	}
	
	public void onCreate(Bundle paramBundle) {
		this.url = new ArrayList<String>();
		this.listData = new ArrayList();
		this.requestQueue = Volley.newRequestQueue(context);
		super.onCreate(paramBundle);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.index_choiceness, container, false);
		initView(view);
		//viewPager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager()));
		return view;
	}

	private void initView(View paramView) {
		swipeRefreshLayout = (SwipeRefreshLayout)paramView.findViewById(R.id.choiceness_swipeRefreshLayout);
		listView = (ListView)paramView.findViewById(R.id.choiceness_listView);
		
		View localView = LayoutInflater.from(context).inflate(R.layout.choiceness_viewpager, listView, false);
		viewPager = (ViewPager)paramView.findViewById(R.id.choiceness_viewpager);
		listView.addHeaderView(localView);
		initGridView();
		listView.addHeaderView(gridView);
		indexListAdapter = new IndexListAdapter(context);
		listView.setAdapter(indexListAdapter);
		
	}
	
	private void initGridView() {
		gridView = LayoutInflater.from(context).inflate(R.layout.choiceness_gridview, listView, false);
		myGridView = (MyGridView)gridView.findViewById(R.id.choiceness_gridview);
	
	
	
	}
	
	private void getAdDataFromNet() {
//		StringRequest stringRequest = new S;
		
	}
	
}
