package com.formicary.mybill;

import com.formicary.mybill.Db.AccountModel;
import com.formicary.mybill.Db.UserModel;
import com.formicary.mybill.pojo.Account;
import com.formicary.mybill.pojo.User;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditBudgetActivity extends ActionBarActivity {
	
	Button get_back, commit, expense, income;
	TextView rmb, blank;
	EditText edit_money, edit_remark;
	Spinner type_spinner;
	int budget_type;//0代表消费expense，1代表收入income
	String[] expenseItems, incomeItems;
	ArrayAdapter<String> expenseadapter, incomeadapter;
	int button_white, button_white_unclick;
	UserModel usermodel;
	User user;
	AccountModel accountmodel;
	Account account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_budget);
		
		findView();	
		buttonSetClick();
		init();
	}
	
	private void init() {
		budget_type = 0;
		button_white = getResources().getColor(R.color.button_white);
		button_white_unclick = getResources().getColor(R.color.button_white_unclick);
		
		expense.setClickable(false);
		expense.setBackgroundColor(button_white_unclick);
		edit_money.setHintTextColor(Color.RED);
		
		expenseItems = getResources().getStringArray(R.array.type_expense);
		expenseadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, expenseItems);	
		type_spinner.setAdapter(expenseadapter);
		
		
		incomeItems = getResources().getStringArray(R.array.type_income);
		incomeadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, incomeItems);
		
		usermodel = new UserModel(this);
		accountmodel = new AccountModel(this);
		
		Bundle bundle = this.getIntent().getExtras();
		user = bundle.getParcelable("user");
		
	}
	
	private void findView() {
		
		get_back = (Button)findViewById(R.id.get_back);
		commit = (Button)findViewById(R.id.commit);
		expense = (Button)findViewById(R.id.expense);
		income = (Button)findViewById(R.id.income);
		rmb = (TextView)findViewById(R.id.rmb);
		blank = (TextView)findViewById(R.id.blank);
		edit_money = (EditText)findViewById(R.id.edit_money);
		edit_remark = (EditText)findViewById(R.id.edit_remark);
		type_spinner = (Spinner)findViewById(R.id.type_spinner);
		
	}
	
	private void buttonSetClick() {
		get_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		commit.setOnClickListener(new View.OnClickListener() {
			String money, type, remark;
			
			@Override
			public void onClick(View arg0) {
				String money = edit_money.getText().toString();
				if(money.equals(""))
					blank.setText("money is null");
				else {
					double m = Double.parseDouble(money);
					type = "";
					type = type_spinner.getSelectedItem().toString();
					remark = edit_remark.getText().toString();
					blank.setText(money + " " + type + " " + remark);
					account = new Account(user.getId(), budget_type, m, type, remark, "");
					accountmodel.insertData(account);
					changeProperty(m);
					finish();
				}
				
			}
		});
		
		expense.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				expenseChange();
			}
		});
		
		income.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				incomeChange();
			}
		});
	}
	
	private void changeProperty(double m) {
		double property = user.getProperty();
		switch(budget_type) {
		case 0:
			property -= m;
			break;
		case 1:
			property += m;
			break;
		default:
		}
		user.setProperty(property);
		usermodel.updateProperty(user);
	}
	
	//点击income按钮切换
	private void incomeChange() {
		budget_type = 1;
		blank.setText("click income");
		rmb.setTextColor(Color.GREEN);
		edit_money.setTextColor(Color.GREEN);
		edit_money.setHintTextColor(Color.GREEN);
		expense.setClickable(true);
		expense.setBackgroundColor(button_white);
		income.setClickable(false);
		income.setBackgroundColor(button_white_unclick);
		type_spinner.setAdapter(incomeadapter);
	}
	
	//点击expense按钮切换
	private void expenseChange() {
		budget_type = 0;
		blank.setText("click expense");
		rmb.setTextColor(Color.RED);
		edit_money.setTextColor(Color.RED);
		edit_money.setHintTextColor(Color.RED);
		expense.setClickable(false);
		expense.setBackgroundColor(button_white_unclick);
		income.setClickable(true);
		income.setBackgroundColor(button_white);
		type_spinner.setAdapter(expenseadapter);
	}
	
	class mClick implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}	
	}
}
