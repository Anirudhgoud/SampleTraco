package com.tracotech.interfaces;

import com.tracotech.constants.NetworkConstants;
import com.tracotech.models.ResponseModel;

/**
 * Created by vishalm on 27/06/18.
 */
public interface NetworkResponseChecker {

    default void checkResponse(int type, String errorMessage, ResponseModel responseModel,
                               boolean showDialog, boolean logout) {
        switch (type) {
            case NetworkConstants.SUCCESS:
                responseModel.setStatus(true);
                break;
            case NetworkConstants.FAILURE:
            case NetworkConstants.NETWORK_ERROR:
                responseModel.setErrorMessage(errorMessage, showDialog);
                break;
            case NetworkConstants.UNAUTHORIZED:
                responseModel.setToLogout(logout);
                break;
        }
    }
}
