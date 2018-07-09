package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tracotech.models.DrawerItemsModel;
import com.tracotech.models.LocationModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.SelectDestinationHolder;

import java.util.List;

public class SelectDestinationAdapter extends RecyclerView.Adapter<SelectDestinationHolder> {

    private List<LocationModel> mLocationModel;
    private View.OnClickListener mDestinationSelectionListener;

    public SelectDestinationAdapter(View.OnClickListener onClickListener, List<LocationModel> locationModelList) {
        this.mLocationModel = locationModelList;
        this.mDestinationSelectionListener = onClickListener;
    }


    @NonNull
    @Override
    public SelectDestinationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dialog_item, parent, false);
        return new SelectDestinationHolder(view,mDestinationSelectionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectDestinationHolder holder, int position) {
        LocationModel locationModel = mLocationModel.get(position);
        holder.bind(locationModel);

    }

    @Override
    public int getItemCount() {
        return mLocationModel != null ? mLocationModel.size() : 0;
    }
}
