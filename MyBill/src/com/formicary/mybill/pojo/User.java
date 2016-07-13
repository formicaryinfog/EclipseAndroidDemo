package com.formicary.mybill.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	int id;
	String username;
	double property;
	String update_time;
	String create_time;
	
	public User() {
		
	}
	public User(Parcel in){  
        //顺序要和writeToParcel写的顺序一样  
        id = in.readInt();
        username = in.readString();
        property = in.readDouble();
        update_time = in.readString();
        create_time = in.readString();
    }  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getProperty() {
		return property;
	}
	public void setProperty(double property) {
		this.property = property;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel parcel, int flags) { 
		parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeDouble(property);
        parcel.writeString(update_time);
        parcel.writeString(create_time);
    } 
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {  
        public User createFromParcel(Parcel in) {  
            return new User(in);  
        }  
          
        public User[] newArray(int size) {  
            return new User[size];  
        }  
    };
}
