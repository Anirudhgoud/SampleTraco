package com.tracotech.models;

public class LocationModel {

    private int id;
    private String name;
    private String addressLine1;
    private String addressLine2;

    private boolean isAddressSelected;

    public boolean isAddressSelected() {
        return isAddressSelected;
    }

    public void setAddressSelection(boolean isSelected) {
        this.isAddressSelected = isSelected;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
}
