package com.formicary.mybill.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper 
{
	private final static String DB_NAME ="Bill.db";//数据库名  
    private final static int VERSION = 1;//版本号  
	
	public MyDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	//为了每次构造时不用传入dbName和版本号，自己得新定义一个构造方法  
    public MyDbHelper(Context cxt){  
        this(cxt, DB_NAME, null, VERSION);//调用上面的构造方法  
    } 
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String usercreate = "create table user(id integer primary key autoincrement," +
				"username varchar(20), property real default 0.00, " +
				"update_time datetime default (datetime('now', 'localtime')), " +
				"create_time datetime default (datetime('now', 'localtime')));"
				;
		db.execSQL(usercreate);
		ContentValues userValues = new ContentValues();
		userValues.put("username", "nidaye");
		userValues.put("property", -50);
		db.insert("user", null, userValues);
        
        String accountcreate = "create table account(id integer primary key autoincrement," +
				"uid integer not null,budget_type integer not null," +
				"money real default 0.00,type varchar(20)," +
				"remark varchar(100)," +
				"create_time datetime  default (datetime('now','localtime')));"
				;
        db.execSQL(accountcreate);
        
        String accounttrigger = "CREATE TRIGGER update_userupdatetime AFTER INSERT " + 
        		"ON account " + 
        		"BEGIN " +
        		"update user set update_time=datetime('now','localtime') where id=new.[uid]; " +
        		"END; "
        		;
        db.execSQL(accounttrigger);
        
		ContentValues accountvalues = new ContentValues();
		accountvalues.put("uid", 1); 
		accountvalues.put("budget_type", 0); 
		accountvalues.put("money", 100); 
		accountvalues.put("type", "校园卡"); 
		accountvalues.put("remark", "");
        db.insert("account", null, accountvalues);
        accountvalues = new ContentValues();
        accountvalues.put("uid", 1); 
        accountvalues.put("budget_type", 1); 
        accountvalues.put("money", 50); 
        accountvalues.put("type", "工资"); 
        accountvalues.put("remark", "第一个月");
        db.insert("account", null, accountvalues);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}  

}
