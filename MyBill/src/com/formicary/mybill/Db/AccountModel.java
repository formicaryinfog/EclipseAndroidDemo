package com.formicary.mybill.Db;

import java.util.ArrayList;
import java.util.List;

import com.formicary.mybill.pojo.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountModel {
	
	MyDbHelper helper = null; 
	private final static String TABLENAME = "account";
	
	public AccountModel(Context cxt) {  
        helper = new MyDbHelper(cxt);  
    }
	
	// ²åÈë²Ù×÷  
    public long insertData(Account account) {  
        //String sql = "insert into student (username)values(?,?)";  
        SQLiteDatabase db = helper.getWritableDatabase();
        //db.execSQL(sql, new Object[] { user.getUsername() });
        
        ContentValues values = new ContentValues();
        values.put("uid", account.getUid()); 
        values.put("budget_type", account.getBudget_type()); 
        values.put("money", account.getMoney()); 
        values.put("type", account.getType()); 
        values.put("remark", account.getRemark()); 
        
        long id = db.insert(TABLENAME, null, values);
        db.close();
        
        return id;
    }
	
    public List<Account> selectMonthAccounts() {
    	List<Account> accountlist = new ArrayList<Account>();
    	    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	
    	Cursor cursor = db.query(TABLENAME, new String[]{"uid", "budget_type", "money", "type", "remark", "date(create_time)"},
    			"create_time >= date('now','start of year')", null, null, null, "create_time desc", null);  
    	  
    	while (cursor.moveToNext()) {  
    	  
    		Account t = new Account(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2),
    				cursor.getString(3), cursor.getString(4), cursor.getString(5));
    		accountlist.add(t);
    	
    	}
    	
    	cursor.close();
    	db.close();
    	
    	return accountlist;
    }
	
    public List<Account> selectYearAccounts() {
    	List<Account> accountlist = new ArrayList<Account>();
    	    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	
    	Cursor cursor = db.query(TABLENAME, new String[]{"uid", "budget_type", "money", "type", "remark", "date(create_time)"},
    			"create_time >= date('now','start of month')", null, null, null, "create_time desc", null);  
    	  
    	while (cursor.moveToNext()) {  
    	  
    		Account t = new Account(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2),
    				cursor.getString(3), cursor.getString(4), cursor.getString(5));
    		accountlist.add(t);
    	
    	}
    	
    	cursor.close();
    	db.close();
    	
    	return accountlist;
    }
    
    public List<Account> selectMonthExpenseAccounts() {
    	List<Account> accountlist = new ArrayList<Account>();
    	    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	
    	Cursor cursor = db.query(TABLENAME, new String[]{"uid", "budget_type", "money", "type", "remark", "create_time"},
    			"create_time >= date('now','start of month') and budget_type = 0", null, null, null, "create_time desc", null);  
    	  
    	while (cursor.moveToNext()) {  
    	  
    		Account t = new Account(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2),
    				cursor.getString(3), cursor.getString(4), cursor.getString(5));
    		accountlist.add(t);
    		
    	}  
    	
    	cursor.close();
    	db.close();
    	
    	return accountlist;
    }
	
    public List<Account> selectMonthIncomeAccounts() {
    	List<Account> accountlist = new ArrayList<Account>();
    	    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	
    	Cursor cursor = db.query(TABLENAME, new String[]{"uid", "budget_type", "money", "type", "remark", "create_time"},
    			"create_time >= date('now','start of month') and budget_type = 1", null, null, null, "create_time desc", null);  
    	  
    	while (cursor.moveToNext()) {  
    	  
    		Account t = new Account(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2),
    				cursor.getString(3), cursor.getString(4), cursor.getString(5));
    		accountlist.add(t);
    		
    	}  
    	
    	cursor.close();
    	db.close();
    	
    	return accountlist;
    }
	
}
