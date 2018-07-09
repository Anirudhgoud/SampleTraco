package com.tracotech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vishalm on 05/07/18.
 */
public class CartListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_product_desc)
    TextView tvProductDesc;
    @BindView(R.id.tv_product_quantity)
    TextView tvProductQuantity;
    @BindView(R.id.tv_product_units)
    TextView tvProductUnits;
    @BindView(R.id.tv_product_amount)
    TextView tvProductAmount;

    public CartListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ProductsUiModel productsUiModel){
        tvProductName.setText(productsUiModel.getName());
        tvProductDesc.setText(productsUiModel.getBrandName());
        tvProductQuantity.setText(productsUiModel.getWeight()+" "+productsUiModel.getWeightUnit());
        tvProductAmount.setText(productsUiModel.getInCartCount()+"");
        tvProductUnits.setText(productsUiModel.getInCartCount()+"");
        Glide.with(ivProductImage.getContext()).load(productsUiModel.getImageUrl()).into(ivProductImage);
    }
}
