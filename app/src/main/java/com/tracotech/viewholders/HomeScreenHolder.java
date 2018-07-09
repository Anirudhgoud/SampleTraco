package com.tracotech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tracotech.models.CustomersModel;
import com.tracotech.tracoshop.R;
import com.tracotech.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvInitials)
    TextView tvInitials;

    public HomeScreenHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CustomersModel customersModel) {
        if (customersModel != null) {
            tvName.setText(customersModel.getName());
            if (!TextUtils.isEmpty(customersModel.getName())) {
                tvInitials.setText(AppUtils.getInitials(customersModel.getName()));
            }
        }
    }
}
