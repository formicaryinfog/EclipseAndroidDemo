package com.formicary.mybill;

import java.util.List;

import com.formicary.mybill.Db.AccountModel;
import com.formicary.mybill.Db.UserModel;
import com.formicary.mybill.pojo.Account;
import com.formicary.mybill.pojo.User;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	Button account, keep_accounts;
	TextView propertyNumber, income_monthNumber, expense_monthNumber, create_time, update_time;
	UserModel usermodel;
	User user;
	AccountModel accountmodel;
	List<Account> month_accountlist;
	double income_month, expense_month;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		propertyNumber = (TextView)findViewById(R.id.propertyNumber);
		income_monthNumber = (TextView)findViewById(R.id.income_monthNumber);
		expense_monthNumber = (TextView)findViewById(R.id.expense_monthNumber);
		create_time = (TextView)findViewById(R.id.create_time);
		update_time = (TextView)findViewById(R.id.update_time);
		
		usermodel = new UserModel(this);
		accountmodel = new AccountModel(this);
		
		account = (Button)findViewById(R.id.account);
		keep_accounts = (Button)findViewById(R.id.keep_accounts);
		
		init();
		
		account.setOnClickListener(new mClick());
		keep_accounts.setOnClickListener(new mClick());
	}

	class mClick implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			switch(v.getId())
			{
			case R.id.account:
				intent.setClass(MainActivity.this, AccountActivity.class);
				break;
			case R.id.keep_accounts:
				intent.setClass(MainActivity.this, EditBudgetActivity.class);
				break;
			}
			bundle.putParcelable("user",  user);
			intent.putExtras(bundle);
			startActivity(intent);
		}	
	}
	
	private void init() {
		getProperty();
		getMonth_Income_Expense();
		create_time.setText(user.getCreate_time());
		update_time.setText(user.getUpdate_time());
	}
	
	private void getProperty() {
		user = usermodel.selectUser(1);
		propertyNumber.setText(Double.toString(user.getProperty()));
	}
	
	private void getMonth_Income_Expense() {
		income_month = 0;
		expense_month = 0;
		month_accountlist = accountmodel.selectMonthAccounts();
		for(int i = 0; i < month_accountlist.size(); i++) {
			switch(month_accountlist.get(i).getBudget_type()) {
			case 0:
				expense_month += month_accountlist.get(i).getMoney();
				break;
			case 1:
				income_month += month_accountlist.get(i).getMoney();
				break;
			default:					
			}
		}
		income_monthNumber.setText(Double.toString(income_month));
		expense_monthNumber.setText(Double.toString(expense_month));
		
	}
	
	@Override
    public void onResume()//可以用广播来通知数据库是否更新
    {
        super.onResume();
        init();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
