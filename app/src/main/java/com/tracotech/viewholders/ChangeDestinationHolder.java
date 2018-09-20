package com.tracotech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.tracotech.models.LocationModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeDestinationHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rbItem)
    RadioButton rbItem;

    @BindView(R.id.llDestination)
    LinearLayout llDestination;


    public ChangeDestinationHolder(View itemView, View.OnClickListener mChangeDestinationListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        llDestination.setOnClickListener(mChangeDestinationListener);
    }

    public void bind(LocationModel locationModel) {
        String addressLine1 = locationModel.getAddressLine1();
        String addressLine2 = locationModel.getAddressLine2();
        llDestination.setTag(locationModel);
        String address = addressLine1 + addressLine2;
        rbItem.setText(address);

        if (locationModel.isAddressSelected()) {
            rbItem.setChecked(true);
        } else {
            rbItem.setChecked(false);
        }

    }
}
