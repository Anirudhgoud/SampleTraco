package com.tracotech.interfaces;

import org.json.JSONArray;

/**
 * Created by kunalsingh on 12/04/17.
 */

public interface NetworkAPICallback {
    void onNetworkResponse(int type, Object response, String errorMessage);
}
