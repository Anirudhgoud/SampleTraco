package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.tracotech.interfaces.SearchResultsListener;
import com.tracotech.models.CustomersModel;
import com.tracotech.models.LocationModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.ChangeDestinationHolder;

import java.util.ArrayList;
import java.util.List;

public class ChangeDestinationAdapter extends RecyclerView.Adapter<ChangeDestinationHolder> implements Filterable {

    private List<LocationModel> mlocationsList;
    private View.OnClickListener mChangeDestinationClickListener;
    private List<LocationModel> mLocationDataCopy;
    private SearchResultsListener mSearchResultListener;

    private CustomFilter mCustomFilter = null;


    public ChangeDestinationAdapter(View.OnClickListener changeDestinationClickListener,
                                    List<LocationModel> locationList) {
        this.mChangeDestinationClickListener = changeDestinationClickListener;
        this.mlocationsList = locationList;
        this.mLocationDataCopy = locationList;
    }

    @NonNull
    @Override
    public ChangeDestinationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_change_destination_item, parent,
                false);
        return new ChangeDestinationHolder(view, mChangeDestinationClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChangeDestinationHolder holder, int position) {

        LocationModel mlocationData = mlocationsList.get(position);
        holder.bind(mlocationData);
    }

    @Override
    public int getItemCount() {
        return mlocationsList == null ? 0 : mlocationsList.size();
    }

    @Override
    public Filter getFilter() {
        if (mCustomFilter == null) {
            mCustomFilter = new CustomFilter();
        }
        return mCustomFilter;
    }


    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<LocationModel> modelList = new ArrayList<>();

                constraint = constraint.toString().toUpperCase();

                for (LocationModel locationModel : mlocationsList) {

                    String addressLine1 = "";
                    String addressLine2 = "";

                    if (!TextUtils.isEmpty(locationModel.getAddressLine1())) {
                        addressLine1 = locationModel.getAddressLine1();
                    }

                    if (!TextUtils.isEmpty(locationModel.getAddressLine2())) {
                        addressLine2 = locationModel.getAddressLine2();
                    }

                    String address = addressLine1 + addressLine2;

                    if (address.toUpperCase().contains(constraint)) {
                        modelList.add(locationModel);
                    }
                }

                filterResults.count = modelList.size();
                filterResults.values = modelList;

            } else {
                filterResults.count = mLocationDataCopy.size();
                filterResults.values = mLocationDataCopy;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mSearchResultListener.onSearchFinished(results.count);
            mlocationsList = (List<LocationModel>) results.values;
            notifyDataSetChanged();
        }
    }

    public void setSearchResultsListener(SearchResultsListener searchResultsListener) {
        this.mSearchResultListener = searchResultsListener;
    }

}
