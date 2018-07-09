package com.tracotech.services.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by kunalsingh on 12/04/17.
 */

public class ObjectMapper {

    ObjectMapper(){}

    public Object getResponse(Response response){
        String responseBody = parseResponseFromBody(response);
        if(responseBody != null){
            try {
                return new JSONObject(responseBody);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                return new JSONArray(responseBody);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String parseResponseFromBody(Response response){
        String responseBody = null;
        try {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

}
