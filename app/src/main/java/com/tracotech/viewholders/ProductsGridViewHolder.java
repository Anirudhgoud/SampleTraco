package com.tracotech.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tracotech.customui.CustomTextView;
import com.tracotech.interfaces.AddToCartListener;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vishalm on 29/06/18.
 */
public class ProductsGridViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_product_name)
    CustomTextView name;
    @BindView(R.id.tv_product_quantity)
    CustomTextView quantity;
    @BindView(R.id.iv_product_image)
    ImageView productImage;
    @BindView(R.id.bt_add)
    CustomTextView addButton;
    @BindView(R.id.tv_product_desc)
    CustomTextView brandName;

    private AddToCartListener addClickListener;

    public ProductsGridViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ProductsUiModel productsUiModel, int position) {
        name.setText(productsUiModel.getName());
        quantity.setText(productsUiModel.getWeight()+" "+productsUiModel.getWeightUnit());
        Glide.with(productImage.getContext()).load(productsUiModel.getImageUrl()).into(productImage);
        brandName.setText(productsUiModel.getBrandName());
        if(productsUiModel.isInCart()){
            addButton.setBackgroundColor(addButton.getContext().getResources().getColor(R.color.cart_green));
            addButton.setText(addButton.getContext().getString(R.string.edit));
        } else {
            addButton.setBackgroundColor(addButton.getContext().getResources().getColor(R.color.colorPrimary));
            addButton.setText(addButton.getContext().getString(R.string.add));
        }
        addButton.setTag(position);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClickListener.onAdd(productsUiModel, position);
            }
        });
    }

    public void setAddListener(AddToCartListener addToCartListener) {
        addClickListener = addToCartListener;
    }
}
