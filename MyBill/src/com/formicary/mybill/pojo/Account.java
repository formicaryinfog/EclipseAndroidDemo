package com.formicary.mybill.pojo;

public class Account {
	int uid;
	int budget_type;
	double money;
	String type;
	String remark;
	String create_time;
	public final static int YEARBEGIN = 0;
	public final static int YEAREND = 4;
	public final static int MONTHBEIGIN = 5;
	public final static int MONTHEDN = 7;
	public final static int DAYBEIGIN = 8;
	public final static int DAYEDN = 10;
	
	
	public Account(int uid, int budget_type, double money ,String type, String remark, String create_time) {
		this.uid = uid;
		this.budget_type = budget_type;
		this.money = money;
		this.type = type;
		this.remark = remark;
		this.create_time = create_time;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBudget_type() {
		return budget_type;
	}
	public void setBudget_type(int budget_type) {
		this.budget_type = budget_type;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public String getYear() {
		return (String)create_time.subSequence(YEARBEGIN, YEAREND);
	}
	public String getMonth() {
		return (String)create_time.subSequence(MONTHBEIGIN, MONTHEDN);
	}
	public String getDay() {
		return (String)create_time.subSequence(DAYBEIGIN, DAYEDN);
	}
}
