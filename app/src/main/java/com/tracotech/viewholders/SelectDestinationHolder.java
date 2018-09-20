package com.tracotech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.tracotech.models.LocationModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectDestinationHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rbItem)
    RadioButton radioButton;

    @BindView(R.id.llContainer)
    LinearLayout llContainer;

    public SelectDestinationHolder(View itemView, View.OnClickListener mDestinationSelectionListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        llContainer.setOnClickListener(mDestinationSelectionListener);
    }

    public void bind(LocationModel locationModel) {

        String addressLine1 = locationModel.getAddressLine1();
        String addressLine2 = locationModel.getAddressLine2();

        String address = addressLine1 + addressLine2;

        radioButton.setText(address);
        llContainer.setTag(locationModel);
        if (locationModel.isAddressSelected()) {
            radioButton.setChecked(true);
        } else {
            radioButton.setChecked(false);
        }
    }

}
