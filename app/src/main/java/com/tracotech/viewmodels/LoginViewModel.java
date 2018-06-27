package com.tracotech.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.tracotech.interfaces.NetworkAPICallback;

import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.network.NetworkService;

import java.util.HashMap;

/**
 * Created by vishalm on 26/06/18.
 */
public class LoginViewModel extends AndroidViewModel {

    private Object loginResponse;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(NetworkResponseChecker loginResponseChecker, ResponseModel responseModel) {
        NetworkService.sharedInstance().getNetworkClient().makeJsonPostRequest(getApplication(),
                "", false, new HashMap<>(), new NetworkAPICallback() {
            @Override
            public void onNetworkResponse(int type, Object response, String errorMessage) {
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
}
