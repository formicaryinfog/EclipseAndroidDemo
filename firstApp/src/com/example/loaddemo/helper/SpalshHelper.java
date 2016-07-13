package com.example.loaddemo.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.SyncStateContract.Helpers;

public class SpalshHelper {
	public static SpalshHelper helper;
	
	public static SpalshHelper getInatance() {
		if(helper ==null) {
			helper = new SpalshHelper();
		}
		return helper;
	}
	
	public Boolean isFirstGo(Context context) {
		SharedPreferences share = context.getSharedPreferences("name", context.MODE_PRIVATE);
		if(!share.getString("name", "").equals("lukou")) {
			Editor editor = share.edit();
			editor.putString("name", "lukou");
			editor.commit();
			return true;
		}
		return false;		
	}
	
	
}
