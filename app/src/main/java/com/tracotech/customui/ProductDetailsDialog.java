package com.tracotech.customui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vishalm on 09/07/18.
 */
public class ProductDetailsDialog extends Dialog{

    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;
    @BindView(R.id.tv_product_name)
    CustomTextView tvProductName;
    @BindView(R.id.tv_product_desc)
    CustomTextView tvProductDesc;
    @BindView(R.id.tv_product_spec)
    CustomTextView tvProductSpec;
    @BindView(R.id.bt_add)
    CustomTextView btAdd;

    private ProductsUiModel productsUiModel;
    private int position;

    public ProductDetailsDialog(@NonNull Context context) {
        super(context, R.style.CustomDialogTheme);
        setContentView(R.layout.layout_dialog_product_details);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void setProductsUiModel(ProductsUiModel productsUiModel){
        this.productsUiModel = productsUiModel;
        tvProductName.setText(productsUiModel.getName());
        tvProductDesc.setText(productsUiModel.getWeight()+productsUiModel.getWeightUnit());
        Glide.with(ivProductImage.getContext()).load(productsUiModel.getImageUrl()).into(ivProductImage);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @OnClick(R.id.iv_close)
    public void close(View v){
        dismiss();
    }

}
