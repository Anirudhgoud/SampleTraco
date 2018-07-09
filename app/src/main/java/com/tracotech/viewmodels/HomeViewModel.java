package com.tracotech.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.tracotech.constants.UrlConstants;
import com.tracotech.interfaces.NetworkAPICallback;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.CustomersModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.network.NetworkService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private Object consumersData;
    private List<CustomersModel> mCustomerList;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public void customersOSuppliersData(NetworkResponseChecker loginResponseChecker, ResponseModel responseModel, String url) {

        NetworkService.sharedInstance().getNetworkClient().makeGetRequest(getApplication(),
                url, true, new NetworkAPICallback() {
                    @Override
                    public void onNetworkResponse(int type, Object response, String errorMessage) {
                        consumersData = parseLoginResponse(response);
                        setCustomersList(response);
                        loginResponseChecker.checkResponse(type, errorMessage, responseModel, true, false);
                    }
                });
    }

    private Object parseLoginResponse(Object response) {

        return new Object();
    }

    public Object getConsumersData() {
        return consumersData;
    }

    public List<CustomersModel> getCustomersList() {
        return mCustomerList;
    }

    private void setCustomersList(Object consumersData) {

        if (consumersData != null) {
            JSONObject jsonObject = (JSONObject) consumersData;
            JSONArray jsonArray = jsonObject.optJSONArray("data");

            if (mCustomerList == null) {

                mCustomerList = new ArrayList<>();
            } else {
                mCustomerList.clear();
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.optJSONObject(i);
                CustomersModel customersModel = new CustomersModel();
                customersModel.setName(object.optString("name"));
                customersModel.setId(object.optInt("id"));
                customersModel.setActive(object.optBoolean("active"));
                mCustomerList.add(customersModel);
            }
        }
    }
}
