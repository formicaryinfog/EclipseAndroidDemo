package com.formicary.mybill.pojo;

public class AccountChild {
	String date;
	String type;
	double money;
	int budget_type;
	String remark;
	
	public AccountChild(String d, String t, double m){
		date = d;
		type = t;
		money = m;
	}
	
	public AccountChild(Account account){
		date = account.getDay();
		type = account.getType();
		money = account.getMoney();
		budget_type = account.getBudget_type();
		remark = account.getRemark();
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getMoneyString() {
		if(budget_type == 0)
			return "-" + Double.toString(money);
		else
			return "+" + Double.toString(money);
		
	}
}
