package com.tracotech.models;

import android.arch.lifecycle.MutableLiveData;

/**
 * Created by vishalm on 26/06/18.
 */
public class ResponseModel {
    private MutableLiveData<Boolean> status = new MutableLiveData<>();
    private MutableLiveData<ErrorModel> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> toLogout = new MutableLiveData<>();

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.postValue(status);
    }

    public MutableLiveData<ErrorModel> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<Boolean> getToLogout() {
        return toLogout;
    }

    public void setErrorMessage(String errorMessage, boolean showDialog) {
        this.errorMessage.postValue(new ErrorModel(errorMessage, showDialog));
    }

    public void setToLogout(boolean logout) {
        this.toLogout.postValue(logout);
    }
}
