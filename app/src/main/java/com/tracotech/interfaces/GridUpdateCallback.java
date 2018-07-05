package com.tracotech.interfaces;

import com.tracotech.models.uimodels.ProductsUiModel;

/**
 * Created by vishalm on 05/07/18.
 */
public interface GridUpdateCallback {
    void onChange(ProductsUiModel productsUiModel, int position);
}
