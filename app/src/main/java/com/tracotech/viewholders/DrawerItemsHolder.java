package com.tracotech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tracotech.models.DrawerItemsModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerItemsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvItemTitle)
    TextView tvTitle;

    @BindView(R.id.ivDrawer)
    ImageView ivDrawerIcon;

    @BindView(R.id.rlContainer)
    RelativeLayout rlContainer;

    public DrawerItemsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
//        tvTitle = itemView.findViewById(R.id.tvItemTitle);
//        ivDrawerIcon = itemView.findViewById(R.id.ivDrawer);
    }

    public void bind(DrawerItemsModel drawerItem, View.OnClickListener drawerItemClickListener) {
        tvTitle.setText(drawerItem.getmTitle());
        ivDrawerIcon.setImageResource(drawerItem.getmIconId());
        rlContainer.setTag(drawerItem);
        rlContainer.setOnClickListener(drawerItemClickListener);
    }

}
