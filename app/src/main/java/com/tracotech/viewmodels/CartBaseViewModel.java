package com.tracotech.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tracotech.constants.NetworkConstants;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.services.storage.LocalStorageService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishalm on 05/07/18.
 */
public class CartBaseViewModel extends AndroidViewModel {

    protected List<ProductsUiModel> cartProducts = new ArrayList<>();

    public CartBaseViewModel(@NonNull Application application) {
        super(application);
    }

    protected ArrayList<ProductsUiModel> getCartProducts(){
        String cartProductsJson = LocalStorageService.sharedInstance().getLocalFileStore().
                getString(getApplication(), SharedPreferenceKeys.CART);
        Gson gson = new Gson();
        ArrayList<ProductsUiModel> cartProducts = new ArrayList<>();
        Type typeToken = new TypeToken<ArrayList<ProductsUiModel>>() {
        }.getType();
        if(cartProductsJson != null || !cartProductsJson.equalsIgnoreCase("")) {
            cartProducts = gson.fromJson(cartProductsJson, typeToken);
        }
        if(cartProducts == null)
            cartProducts = new ArrayList<>();
        return cartProducts;
    }

    public void fetchCartProducts(NetworkResponseChecker networkResponseChecker, ResponseModel responseModel){
        cartProducts = getCartProducts();
        networkResponseChecker.checkResponse(NetworkConstants.SUCCESS, "Error",
                responseModel, true, false);
    }

}
