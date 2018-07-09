package com.tracotech.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.tracotech.interfaces.SearchResultsListener;
import com.tracotech.models.CustomersModel;
import com.tracotech.tracoshop.R;
import com.tracotech.viewholders.HomeScreenHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenHolder> implements Filterable {

    private List<CustomersModel> mCustomerList;
    private CustomersFilter mCustomFilter;
    private SearchResultsListener mSearchResultListener;
    private View.OnClickListener listTapListener;

    private List<CustomersModel> mCustomerListCopy;

    public HomeScreenAdapter(List<CustomersModel> customersList) {
        this.mCustomerList = customersList;
        this.mCustomerListCopy = customersList;
    }

    @NonNull
    @Override
    public HomeScreenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        HomeScreenHolder holder = new HomeScreenHolder(view);
        holder.setItemClickListener(listTapListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenHolder holder, int position) {
        CustomersModel customersModel = mCustomerList.get(position);
        holder.bind(customersModel, position);
    }

    @Override
    public int getItemCount() {
        return mCustomerList != null ? mCustomerList.size() : 0;
    }

    @Override
    public Filter getFilter() {
        if (mCustomFilter == null) {
            mCustomFilter = new CustomersFilter();
        }
        return mCustomFilter;
    }

    public void setSearchResultsListener(SearchResultsListener searchResultsListener) {
        this.mSearchResultListener = searchResultsListener;
    }

    public void setTapListener(View.OnClickListener onClickListener) {
        listTapListener = onClickListener;
    }

    public CustomersModel getItemAt(int position) {
        return mCustomerList.get(position);
    }


    private class CustomersFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<CustomersModel> modelList = new ArrayList<>();

                constraint = constraint.toString().toUpperCase();

                for (CustomersModel customersModel : mCustomerList) {
                    if (customersModel.getName().toUpperCase().contains(constraint)) {
                        modelList.add(customersModel);
                    }
                }

                filterResults.count = modelList.size();
                filterResults.values = modelList;

            } else {
                filterResults.count = mCustomerListCopy.size();
                filterResults.values = mCustomerListCopy;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mSearchResultListener.onSearchFinished(results.count);
            mCustomerList = (List<CustomersModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
