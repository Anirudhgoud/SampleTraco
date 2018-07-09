package com.tracotech.models.uimodels;

/**
 * Created by vishalm on 04/07/18.
 */
public class CartItemUiModel {
    private int productId;
    private int inCartCount;

    public CartItemUiModel(int id, int inCartCount) {
        this.productId = id;
        this.inCartCount = inCartCount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getInCartCount() {
        return inCartCount;
    }

    public void setInCartCount(int inCartCount) {
        this.inCartCount = inCartCount;
    }
}
