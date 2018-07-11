package com.tracotech.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.tracotech.constants.UrlConstants;
import com.tracotech.interfaces.NetworkAPICallback;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.AddressModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.network.NetworkService;

import org.json.JSONObject;

public class ProfileViewModel extends AndroidViewModel {

    private Object addressResponse;


    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }


    public void fetchAddress(NetworkResponseChecker networkResponseChecker,
                             ResponseModel productsResponseModel, int businessId) {
        String url = UrlConstants.ADDRESS_FOR_ID + "/" + businessId;
        NetworkService.sharedInstance().getNetworkClient().makeGetRequest(getApplication(),
                url, true, new NetworkAPICallback() {
                    @Override
                    public void onNetworkResponse(int type, Object response, String errorMessage) {
                        setAddressResponse(response);
                        networkResponseChecker.checkResponse(type, errorMessage,
                                productsResponseModel, true, false);
                    }
                });
    }


    private void setAddressResponse(Object response) {
        addressResponse = response;
    }


    public AddressModel getAddressResponse() {
        JSONObject jsonObject = ((JSONObject) addressResponse);
        AddressModel addressModel = new AddressModel();
        addressModel.setCity(jsonObject.optString("city"));
        addressModel.setCountry(jsonObject.optString("country"));
        addressModel.setAddress_line_1(jsonObject.optString("address_line_1"));
        addressModel.setAddress_line_2(jsonObject.optString("address_line_2"));
        return addressModel;
    }
}
