package com.formicary.mybill;

import java.util.ArrayList;
import java.util.List;

import com.formicary.mybill.Db.AccountModel;
import com.formicary.mybill.pojo.Account;
import com.formicary.mybill.pojo.AccountChild;
import com.formicary.mybill.pojo.AccountGroup;
import com.formicary.mybill.pojo.AccountList;
import com.formicary.mybill.pojo.User;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class AccountActivity extends ActionBarActivity {
	
	Button get_back;
	TextView account_title;
	ExpandableListView account_list;
	ExpandInfoAdapter adapter;
	User user;
	AccountModel accountmodel;
	List<Account> accountres;
	AccountList accountlist;
	List<AccountGroup> group;
	List<List<AccountChild>> child;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		
		get_back = (Button)findViewById(R.id.get_back);
		account_title = (TextView) findViewById(R.id.account_title);
		account_list = (ExpandableListView) findViewById(R.id.account_list);
		
		get_back.setOnClickListener(new mClick());
		
		accountmodel = new AccountModel(this);
		
		group = new ArrayList<AccountGroup>();
		child = new ArrayList<List<AccountChild>>();
		
		addData();
		
	}
	
	class mClick implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}	
	}
	
	public void addData(){
		
		Bundle bundle = this.getIntent().getExtras();
		user = bundle.getParcelable("user");
		
		accountres = accountmodel.selectYearAccounts();
		accountlist = new AccountList(accountres);
		group = accountlist.getGroup();
		child = accountlist.getChild();
		
		group.add(new AccountGroup("2016-05", 500, 100));  
		group.add(new AccountGroup("2016-04", 200, 700)); 
		
		List<AccountChild> item = new ArrayList<AccountChild>();
		item.add(new AccountChild("09", "校园卡", -100));
		item.add(new AccountChild("10", "网费", 50));
		item.add(new AccountChild("11", "话费", -80));
		
		for (int  index = 0 ; index <group.size(); ++index)  
		{  
		    child.add(item);  
		}
		
		
		adapter = new ExpandInfoAdapter(this);
		account_list.setAdapter(adapter);
	}
	
	public  class  ExpandInfoAdapter extends  BaseExpandableListAdapter  
	{
		Activity activity;
		LayoutInflater mInflater;
		public ExpandInfoAdapter(Activity a) {
			activity = a;
			mInflater = LayoutInflater.from(activity);
		}
		
		// child's stub
		
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
			//return getChildViewStub(child.get(groupPosition).get(childPosition).toString());
			return getViewChild(groupPosition, childPosition, convertView, parent);
		}

		/*public TextView getChildViewStub(String s) 
		{    
		     // Layout parameters for the ExpandableListView
		     AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
		     ViewGroup.LayoutParams.FILL_PARENT, 64);
		     TextView text = new TextView(activity);
		     text.setLayoutParams(lp);
		     text.setTextSize(20);
		     text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		     text.setPadding(36, 0, 0, 0);
		     text.setText(s);
		     return text;
		}*/
		
		public View getViewChild(int groupPosition, int childPosition, View convertView, ViewGroup parent) {
			ViewHolderChild holder;
			 if (convertView == null) {
				 convertView = mInflater.inflate(R.layout.account_children, null);
				 holder = new ViewHolderChild();
				 holder.date = (TextView)convertView.findViewById(R.id.date);
				 holder.type = (TextView)convertView.findViewById(R.id.type);
				 holder.money = (TextView)convertView.findViewById(R.id.money);
				 convertView.setTag(holder);
			 } else {
				 holder = (ViewHolderChild)convertView.getTag();
			 }
			 AccountChild item = child.get(groupPosition).get(childPosition);
			 holder.date.setText(item.getDate());
			 holder.type.setText(item.getType());
			 holder.money.setText(item.getMoneyString());
			 return convertView;
		}
		
		@Override
		public int getChildrenCount(int groupPosition) {
			return child.get(groupPosition).size();
		}

		// group's stub
		
		@Override
		public Object getGroup(int groupPosition) {
			return group.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return group.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			//return getGroupViewStub(getGroup(groupPosition).toString());
			return getViewGroup(groupPosition, convertView, parent);
		}

		/*public TextView getGroupViewStub(String s) {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
			ViewGroup.LayoutParams.FILL_PARENT, 64);
			TextView text = new TextView(activity);
			text.setLayoutParams(lp);
			text.setTextSize(20);
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			text.setPadding(36, 0, 0, 0);
			text.setText(s);
			return text;
		}*/
		
		public View getViewGroup(int groupPosition, View convertView, ViewGroup parent) {
			ViewHolderGroup holder;
			 if (convertView == null) {
				 convertView = mInflater.inflate(R.layout.account_group, null);
				 holder = new ViewHolderGroup();
				 holder.date = (TextView)convertView.findViewById(R.id.date);
				 holder.expense = (TextView)convertView.findViewById(R.id.expense);
				 holder.income = (TextView)convertView.findViewById(R.id.income);
				 convertView.setTag(holder);
			 } else {
				 holder = (ViewHolderGroup)convertView.getTag();
			 }
			 AccountGroup item = group.get(groupPosition);
			 holder.date.setText(item.getDate());
			 holder.expense.setText("支出:" + item.getExpenseString());
			 holder.income.setText("收入:" + item.getIncomeString());
			 return convertView;
		}
		
		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
	
	static class ViewHolderChild {
	    TextView date;
	    TextView type;
	    TextView money;
	}
	
	static class ViewHolderGroup {
	    TextView date;
	    TextView expense;
	    TextView income;
	}
}
