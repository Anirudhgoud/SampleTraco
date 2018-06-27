package com.tracotech.models;

/**
 * Created by vishalm on 27/06/18.
 */
public class ErrorModel {
    private String errorMessage;
    private boolean showDialog;

    public ErrorModel(String errorMessage, boolean showDialog) {
        this.errorMessage = errorMessage;
        this.showDialog = showDialog;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
