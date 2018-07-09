package com.tracotech.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.models.DrawerItemsModel;
import com.tracotech.models.LocationModel;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.tracoshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    private final static String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final static String PHONE_PATTERN = "[0-9]{4,}";
    private final static int DEVICE_WIDTH = 0;
    private final static int DEVICE_HEIGHT = 1;

    public static boolean isValidEmailOrContactNumber(String text) {
        boolean isEmail = text.matches(EMAIL_PATTERN);
        boolean isPhone = text.matches(PHONE_PATTERN);
        return isEmail || isPhone;
    }


    public static List<DrawerItemsModel> getDrawerListItems(Context context) {
        List<DrawerItemsModel> itemsModelList = new ArrayList<>();
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.profile),
                R.drawable.ic_drawer_profile));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.suppliers),
                R.drawable.ic_drawer_suppliers));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.orders),
                R.drawable.ic_drawer_orders));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.locations),
                R.drawable.ic_drawer_location));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.request_returns),
                R.drawable.ic_drawer_req_returns));
        itemsModelList.add(new DrawerItemsModel(context.getString(R.string.payments),
                R.drawable.ic_drawer_payments));
        return itemsModelList;
    }

    public static String getInitials(String name) {

        StringBuilder initials = new StringBuilder();
        for (String s : name.split(" ")) {
            if (initials.length() != 2) {
                initials.append(s.charAt(0));
            }
        }
        return initials.toString();
    }


    public static List<LocationModel> getLocationList(Context context) {

        List<LocationModel> locationsList = new ArrayList<>();

        String locationData = LocalStorageService.sharedInstance().getLocalFileStore().getString(context,
                SharedPreferenceKeys.LOCATIONS);
        if (locationData != null) {
            try {
                JSONArray jsonArray = new JSONArray(locationData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    LocationModel locationModel = new LocationModel();
                    locationModel.setId(jsonObject.optInt("id"));
                    locationModel.setName(jsonObject.optString("name"));
                    locationModel.setAddressLine1(jsonObject.optString("address_line_1"));
                    locationModel.setAddressLine1(jsonObject.optString("address_line_2"));
                    locationsList.add(locationModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return locationsList;
    }

    public static int getDeviceWidthOHeight(Context context, int spec) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        switch (spec) {
            case DEVICE_WIDTH:
                return displayMetrics.widthPixels;
            case DEVICE_HEIGHT:
                return displayMetrics.heightPixels;
        }
        return DEVICE_WIDTH;
    }
}
