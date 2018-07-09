package com.tracotech.viewmodels;

import android.app.Application;
import android.support.annotation.NonNull;

import com.tracotech.models.uimodels.ProductsUiModel;

import java.util.List;

/**
 * Created by vishalm on 05/07/18.
 */
public class CartViewModel extends CartBaseViewModel {

    public CartViewModel(@NonNull Application application) {
        super(application);
    }

    public List<ProductsUiModel> getCartItems() {
        return cartProducts;
    }
}
