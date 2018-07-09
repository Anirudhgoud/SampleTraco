package com.tracotech.viewmodels;

import android.app.Application;
import android.support.annotation.NonNull;

import com.tracotech.models.uimodels.ProductsUiModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vishalm on 05/07/18.
 */
public class CartViewModel extends CartBaseViewModel {

    public CartViewModel(@NonNull Application application) {
        super(application);
    }

    public ArrayList<ProductsUiModel> getCartItems() {
        ArrayList<ProductsUiModel> productsUiModels = new ArrayList<>();
        if(cartProducts.size() > 0){
            for(Map.Entry<Integer, ProductsUiModel> entry : cartProducts.entrySet()){
                productsUiModels.add(entry.getValue());
            }
        }
        return productsUiModels;
    }
}
