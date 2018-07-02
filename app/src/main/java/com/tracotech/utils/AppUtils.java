package com.tracotech.utils;

import android.content.Context;

import com.tracotech.models.DrawerItemsModel;
import com.tracotech.tracoshop.R;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    public final static String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public final static String PHONE_PATTERN = "[0-9]{4,}";

    public static boolean isValidEmailOrContactNumber(String text) {
        boolean isEmail = text.matches(EMAIL_PATTERN);
        boolean isPhone = text.matches(PHONE_PATTERN);
        return isEmail || isPhone;
    }


    public static List<DrawerItemsModel> getDrawerListItems(Context context) {
        List<DrawerItemsModel> itemsModelList = new ArrayList<>();
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.profile),
                R.drawable.ic_hamburger));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.suppliers),
                R.drawable.ic_hamburger));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.orders),
                R.drawable.ic_hamburger));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.locations),
                R.drawable.ic_hamburger));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.request_returns),
                R.drawable.ic_hamburger));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.payments),
                R.drawable.ic_hamburger));
        return itemsModelList;
    }

}
