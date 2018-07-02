package com.tracotech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tracotech.models.DrawerItemsModel;
import com.tracotech.tracoshop.R;

public class DrawerItemsHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private ImageView ivDrawerIcon;

    public DrawerItemsHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvItemTitle);
        ivDrawerIcon = itemView.findViewById(R.id.ivDrawer);
    }

    public void bind(DrawerItemsModel drawerItem) {
        tvTitle.setText(drawerItem.getmTitle());
        ivDrawerIcon.setImageResource(drawerItem.getmIconId());
    }

}
