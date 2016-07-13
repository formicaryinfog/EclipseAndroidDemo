package com.formicary.mybill.Db;

import com.formicary.mybill.pojo.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserModel {
	
	MyDbHelper helper = null; 
	private final static String TABLENAME = "user";
	
	public UserModel(Context cxt) {  
        helper = new MyDbHelper(cxt);  
    }  
	
	// 插入操作  
    public long insertData(User user) {  
        //String sql = "insert into student (username)values(?,?)";  
        SQLiteDatabase db = helper.getWritableDatabase();
        //db.execSQL(sql, new Object[] { user.getUsername() });
        
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername()); 
        
        long id = db.insert(TABLENAME, null, values);
        db.close();
        
        return id;
    }
    
    //通过id查询一个用户的操作
    public User selectUser(int id) { 
        SQLiteDatabase db = helper.getWritableDatabase(); 
        
        Cursor cursor = db.query(TABLENAME, new String[]{"id", "username", "property","update_time", "create_time"}, "id=?", new String[]{Integer.toString(id)}, null, null, null, null);  
        
        cursor.moveToNext();
        
        User user = new User();
        
        user.setId(cursor.getInt(cursor.getColumnIndex("id"))); //获取第一列的值,第一列的索引从0开始  
        user.setUsername(cursor.getString(1));
        user.setProperty(cursor.getDouble(2));
        user.setUpdate_time(cursor.getString(3));
        user.setCreate_time(cursor.getString(4));
        
        cursor.close();            
        db.close();
        
        return user;
    }
    
    //更新property操作
    public void updateProperty(User user) { 
    	SQLiteDatabase db = helper.getWritableDatabase();  
    	
    	ContentValues values = new ContentValues();      	  
    	values.put("property", user.getProperty());//key为字段名，value为值      	 
    	
    	db.update(TABLENAME, values, "id=?", new String[]{Integer.toString(user.getId())});   
    	
    	db.close();
    }
    
}
