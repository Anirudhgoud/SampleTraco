package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tracotech.interfaces.AddToCartListener;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.ProductsGridViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishalm on 29/06/18.
 */
public class ProductsGridAdapter extends RecyclerView.Adapter<ProductsGridViewHolder> {

    private List<ProductsUiModel> productsUiModels = new ArrayList<>();
    private AddToCartListener addToCartListener;

    public void replaceAllProducts(List<ProductsUiModel> productsUiModels){
        this.productsUiModels = productsUiModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductsGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ProductsGridViewHolder holder = new ProductsGridViewHolder(LayoutInflater.from(
               parent.getContext()).inflate(R.layout.layout_product_grid_item, parent, false));
       holder.setAddListener(addToCartListener);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsGridViewHolder holder, int position) {
        holder.bind(productsUiModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return productsUiModels.size();
    }

    public void setAddToCartListener(AddToCartListener addToCartListener) {
        this.addToCartListener = addToCartListener;
    }

    public void replaceProduct(int position, ProductsUiModel productsUiModel) {
        productsUiModels.set(position, productsUiModel);
        notifyDataSetChanged();
    }
}
