package com.formicary.mybill.pojo;

import java.util.ArrayList;
import java.util.List;

public class AccountGroup {
	String date;
	String year;
	String month;
	double expense;
	double income;
	public final static String EXPENSE = "支出:";
	public final static String INCOME = "收入:";
	public final static int YEARBEGIN = 0;
	public final static int YEAREND = 4;
	public final static int MONTHBEIGIN = 5;
	public final static int MONTHEDN = 7;
	
	public AccountGroup(String d, double e, double i) {
		date = d.substring(YEARBEGIN, MONTHEDN);
		initYearMonth();
		expense = e;
		income = i;
	}
	
	public AccountGroup() {
		expense = 0;
		income = 0;
	}
	
	public AccountGroup(Account account) {
		date = account.getCreate_time().substring(YEARBEGIN, MONTHEDN);
		initYearMonth();
		expense = 0;
		income = 0;
		changeMoney(account);
	}
	
	private void changeMoney(Account account) {
		switch(account.getBudget_type()) {
		case 0:
			expense += account.getMoney();
			break;
		case 1:
			income += account.getMoney();
			break;
		}
	}
	
	private void initYearMonth() {
		year = (String) date.subSequence(YEARBEGIN, YEAREND);
		month = (String) date.subSequence(MONTHBEIGIN, MONTHEDN);
	}
	
	public boolean changeData(Account account) {
		String y = account.getYear();
		String m = account.getMonth();
		
		if(year.equals(y) && month.equals(m)) {
			changeMoney(account);
		}	
		return false;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getExpense() {
		return expense;
	}
	public String getExpenseString() {
		return Double.toString(expense);
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	
	public double getIncome() {
		return income;
	}
	public String getIncomeString() {
		return Double.toString(income);
	}
	public void setIncome(double income) {
		this.income = income;
	}
	
	
}
