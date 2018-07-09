package com.tracotech.models;

public class DrawerItemsModel {

    private String mTitle;
    private int mIconId;

    public DrawerItemsModel(String title, int icon) {
        this.mTitle = title;
        this.mIconId = icon;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmIconId() {
        return mIconId;
    }

    public void setmIconId(int mIconId) {
        this.mIconId = mIconId;
    }
}
