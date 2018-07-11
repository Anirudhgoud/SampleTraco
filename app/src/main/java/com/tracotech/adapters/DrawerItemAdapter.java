package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tracotech.models.DrawerItemsModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.DrawerItemsHolder;

import java.util.List;

public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemsHolder> {

    private List<DrawerItemsModel> mListItems;
    private View.OnClickListener drawerItemClickListener;

    public DrawerItemAdapter(View.OnClickListener drawerItemClickListener,
                             List<DrawerItemsModel> draweListItems) {
        this.drawerItemClickListener = drawerItemClickListener;
        this.mListItems = draweListItems;
    }

    @Override
    public DrawerItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drawer_list_item,
                parent, false);
        return new DrawerItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerItemsHolder holder, int position) {

        DrawerItemsModel drawerItemsModel = mListItems.get(position);
        holder.bind(drawerItemsModel,drawerItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
