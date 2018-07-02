package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tracotech.viewholders.ProductsGridViewHolder;

/**
 * Created by vishalm on 29/06/18.
 */
public class ProductsGridAdapter extends RecyclerView.Adapter<ProductsGridViewHolder> {

    @NonNull
    @Override
    public ProductsGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsGridViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
