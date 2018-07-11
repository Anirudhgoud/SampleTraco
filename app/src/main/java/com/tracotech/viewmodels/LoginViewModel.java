package com.tracotech.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.tracotech.constants.RequestConstants;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.constants.UrlConstants;
import com.tracotech.interfaces.NetworkAPICallback;

import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.LocationModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.network.NetworkService;
import com.tracotech.services.storage.LocalStorageService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vishalm on 26/06/18.
 */
public class LoginViewModel extends AndroidViewModel {

    private Object loginResponse;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(NetworkResponseChecker loginResponseChecker, String phoneNumber, String password, ResponseModel responseModel) {
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put(RequestConstants.KEY_PHONE_NUMBER, phoneNumber);
        bodyParams.put(RequestConstants.KEY_COUNTRY_CODE, "+91");
        bodyParams.put(RequestConstants.KEY_PASSWORD, password);
        NetworkService.sharedInstance().getNetworkClient().makeJsonPostRequest(getApplication(),
                UrlConstants.LOGIN_URL, false, bodyParams, new NetworkAPICallback() {
                    @Override
                    public void onNetworkResponse(int type, Object response, String errorMessage) {
                        if (response != null) {
                            storeUserData(response);
                        }
                        loginResponse = parseLoginResponse(response);
                        loginResponseChecker.checkResponse(type, errorMessage, responseModel, true, false);
                    }
                });
    }


    public Object getLoginData() {
        return loginResponse;
    }

    private Object parseLoginResponse(Object response) {
        return new Object();
    }

    private void storeUserData(Object response) {

        JSONObject userObj = (JSONObject) response;
        Context context = getApplication().getApplicationContext();
        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.PROFILE_URL, userObj.optString("profile_image_url"));
        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.USER_ID, userObj.optString("id"));

        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.FIRST_NAME, userObj.optString("first_name"));
        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.LAST_NAME, userObj.optString("last_name"));
        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.CONTACT_NO, userObj.optString("contact_number"));
        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.BUSINESS_ID, userObj.optInt("business_id"));

        LocalStorageService.sharedInstance().getLocalFileStore().store(context, SharedPreferenceKeys.LOCATIONS,
                userObj.optJSONArray("locations").toString());
        JSONObject permissions = userObj.optJSONObject("permissions");
        if (permissions != null) {
            LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                    SharedPreferenceKeys.SALES_LOGIN, permissions.optString("sale_login"));

            LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                    SharedPreferenceKeys.ORDER_LOGIN, permissions.optString("order_login"));
        }
    }

    private void storeLocationsData(Context context, JSONArray locations) {

        List<LocationModel> locationModels = new ArrayList<>();

        for (int i = 0; i < locations.length(); i++) {
            try {
                JSONObject locationsObj = locations.getJSONObject(i);
                LocationModel locationModel = new LocationModel();
                locationModel.setId(locationsObj.getInt("id"));
                locationModel.setName(locationsObj.getString("name"));
                locationModel.setAddressLine1(locationsObj.getString("address_line_1"));
                locationModel.setAddressLine2(locationsObj.getString("address_line_2"));
                locationModels.add(locationModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LocalStorageService.sharedInstance().getLocalFileStore().store(context,
                SharedPreferenceKeys.LOCATIONS, new Gson().toJson(locationModels));
    }
}
