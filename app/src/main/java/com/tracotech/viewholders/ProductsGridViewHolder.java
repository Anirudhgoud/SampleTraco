package com.tracotech.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;

/**
 * Created by vishalm on 29/06/18.
 */
public class ProductsGridViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView quantity;
    private ImageView productImage;

    public ProductsGridViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_product_name);
        quantity = itemView.findViewById(R.id.tv_product_quantity);
        productImage = itemView.findViewById(R.id.iv_product_image);
    }

    public void bind(ProductsUiModel productsUiModel) {
        name.setText(productsUiModel.getName());
        quantity.setText(productsUiModel.getWeight()+" "+productsUiModel.getWeightUnit());
        Glide.with(productImage.getContext()).load(productsUiModel.getImageUrl()).into(productImage);
    }
}
