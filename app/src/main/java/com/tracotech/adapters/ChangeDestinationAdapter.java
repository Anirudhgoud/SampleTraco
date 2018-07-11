package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tracotech.models.LocationModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.ChangeDestinationHolder;

import java.util.List;

public class ChangeDestinationAdapter extends RecyclerView.Adapter<ChangeDestinationHolder> {

    private View.OnClickListener mChangeDestinationClickLIstener;
    private List<LocationModel> mlocationsList;

    public ChangeDestinationAdapter(View.OnClickListener changeDestinationClickListener,
                                    List<LocationModel> locationList) {
        this.mChangeDestinationClickLIstener = changeDestinationClickListener;
        this.mlocationsList = locationList;
    }

    @NonNull
    @Override
    public ChangeDestinationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_change_destination_item, parent,
                false);
        return new ChangeDestinationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChangeDestinationHolder holder, int position) {

        LocationModel mlocationData = mlocationsList.get(position);
        holder.bind(mChangeDestinationClickLIstener, mlocationData);
    }

    @Override
    public int getItemCount() {
        return mlocationsList == null ? 0 : mlocationsList.size();
    }
}
