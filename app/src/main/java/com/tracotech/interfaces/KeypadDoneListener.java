package com.tracotech.interfaces;

import com.tracotech.models.uimodels.ProductsUiModel;

/**
 * Created by vishalm on 04/07/18.
 */
public interface KeypadDoneListener {
    void onKeyPadDone(String input, ProductsUiModel productsUiModel, int position);
}
