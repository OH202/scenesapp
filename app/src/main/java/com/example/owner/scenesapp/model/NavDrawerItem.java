package com.example.owner.scenesapp.model;

import android.app.Activity;

public class NavDrawerItem extends Activity {

    private String title;
    private int icon;
    private String count = "0";
    // boolean to set visiblity of the counter
    private boolean isCounterVisible = false;

    public NavDrawerItem(){}

    public NavDrawerItem(String title){
        this.title = title;

    }

    public NavDrawerItem(String title, boolean isCounterVisible, String count){
        this.title = title;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
    }


    public final String getNavTitle(){
        return this.title;
    }

    public String getCount(){
        return this.count;
    }

    public boolean getCounterVisibility(){
        return this.isCounterVisible;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setCount(String count){
        this.count = count;
    }

    public void setCounterVisibility(boolean isCounterVisible){
        this.isCounterVisible = isCounterVisible;
    }
}
