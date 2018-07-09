package com.tracotech.interfaces;

import com.tracotech.customui.ProductDetailsDialog;
import com.tracotech.models.uimodels.ProductsUiModel;

/**
 * Created by vishalm on 09/07/18.
 */
public interface ProductDetailsListener {
    void showDetails(ProductsUiModel productsUiModel, int position);
}
