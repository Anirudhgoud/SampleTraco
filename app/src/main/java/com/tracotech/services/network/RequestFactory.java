package com.tracotech.services.network;

import android.content.Context;

import com.google.gson.Gson;
import com.tracotech.constants.NetworkConstants;
import com.tracotech.constants.RequestConstants;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.interfaces.NetworkRequest;
import com.tracotech.services.storage.LocalFileStore;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.utils.LogUtils;


import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by kunalsingh on 12/04/17.
 */

public class RequestFactory {

    RequestFactory(){}

    public Request createRequest(Context context, String url, boolean isAuthRequired, String requestType,
                                 String contentType, Map<String, Object> bodyParams, Map<String, String> localHeaderParams){
        NetworkRequest networkRequest;
        switch (requestType) {
            case NetworkConstants.PUT_REQUEST:
                networkRequest = new PutRequest();
                break;
            case NetworkConstants.POST_REQUEST:
                networkRequest = new PostRequest();
                break;
            case NetworkConstants.DELETE_REQUEST:
                networkRequest = new DeleteRequest();
                break;
            default:
                networkRequest = new GetRequest();
                break;
        }
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put(RequestConstants.KEY_USER_AGENT, RequestConstants.USER_AGENT);
        RequestBody requestBody = null;
        if(isAuthRequired){
            LocalFileStore localFileStore = LocalStorageService.sharedInstance().getLocalFileStore();
            String authToken = localFileStore.getString(context, SharedPreferenceKeys.AUTH_TOKEN);
            headerParams.put(RequestConstants.AUTHORIZATION, authToken);
        }
        if(localHeaderParams != null){
            for (Map.Entry<String, String> entry : localHeaderParams.entrySet()) {
                headerParams.put(entry.getKey(), entry.getValue());
            }
        }
        if(contentType != null && contentType.equals("form")){
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : bodyParams.entrySet()) {
                String value;
                try{
                    value = (String) entry.getValue();
                }catch (ClassCastException exception){
                    exception.printStackTrace();
                    value = entry.getValue().toString();
                }
                LogUtils.error(this.getClass().getSimpleName(), entry.getKey()+" "+value);
                bodyBuilder.add(entry.getKey(), value);
            }
            requestBody = bodyBuilder.build();
            headerParams.put("Content-Type", "application/x-form-urlencoded");
        }else if (contentType != null && contentType.equals(RequestConstants.CONTENT_TYPE_JSON)){
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            headerParams.put("Content-Type", "application/json");
            if(bodyParams != null) {
                LogUtils.debug(this.getClass().getSimpleName(), new Gson().toJson(bodyParams));
                requestBody = RequestBody.create(JSON, new Gson().toJson(bodyParams));
            }
        }
        return networkRequest.buildRequest(url, requestBody, headerParams);
    }
}
