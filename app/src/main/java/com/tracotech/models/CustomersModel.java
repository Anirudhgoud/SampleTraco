package com.tracotech.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomersModel implements Parcelable {

    private int id;
    private String name;
    private boolean active;

    public CustomersModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected CustomersModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        active = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (active ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CustomersModel> CREATOR = new Parcelable.Creator<CustomersModel>() {
        @Override
        public CustomersModel createFromParcel(Parcel in) {
            return new CustomersModel(in);
        }

        @Override
        public CustomersModel[] newArray(int size) {
            return new CustomersModel[size];
        }
    };

    @Override
    public String toString(){
        return name;
    }

}