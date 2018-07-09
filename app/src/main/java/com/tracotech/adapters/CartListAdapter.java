package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.CartListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishalm on 05/07/18.
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListViewHolder> {

    private List<ProductsUiModel> cartItems = new ArrayList<>();

    public CartListAdapter() {
    }

    public void updateList(List<ProductsUiModel> cartItems){
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        holder.bind(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
