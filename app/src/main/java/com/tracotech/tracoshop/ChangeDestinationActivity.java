package com.tracotech.tracoshop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tracotech.adapters.ChangeDestinationAdapter;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.interfaces.SearchResultsListener;
import com.tracotech.models.LocationModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeDestinationActivity extends ParentAppCompatActivity implements TextWatcher,
        SearchResultsListener {

    @BindView(R.id.rlNOResults)
    RelativeLayout rlNOResults;

    @BindView(R.id.rvDestination)
    RecyclerView rvItems;

    @BindView(R.id.etSearch)
    EditText mEtSearch;


    private List<LocationModel> locationList;
    private ChangeDestinationAdapter mChangeDestinationAdapter;
    private ResponseModel mChangeDestinationResponseModel = new ResponseModel();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_select_destination);
    }

    @Override
    public void doInitialSetup() {
        ButterKnife.bind(this);
        initToolBar();
        initUi();
    }

    private void initToolBar() {
        setToolbarLeftIcon(R.drawable.ic_back_arrow);
        setTitle(getString(R.string.select_destination));
    }

    private void initUi() {
        locationList = AppUtils.getLocationList(this);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        mChangeDestinationAdapter = new ChangeDestinationAdapter(this,
                locationList);
        rvItems.setAdapter(mChangeDestinationAdapter);
        mEtSearch.addTextChangedListener(this);
        mChangeDestinationAdapter.setSearchResultsListener(this);
        setDefaultSelectedDestination();
    }

    @Override
    public void onClickWithId(View view) {

        switch (view.getId()) {
            case R.id.llDestination:
                if (view.getTag() instanceof LocationModel) {
                    LocationModel locationModel = (LocationModel) view.getTag();
                    for (LocationModel locationModel1 : locationList) {
                        locationModel1.setAddressSelection(false);
                    }
                    locationModel.setAddressSelection(true);
                    storeSelectedDestination(locationModel);
                    mChangeDestinationAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.bt_top_left:
                onBackPressed();
                break;
        }
    }

    private void storeSelectedDestination(LocationModel locationModel) {
        Gson gson = new Gson();
        String locationData = gson.toJson(locationModel);
        LocalStorageService.sharedInstance().getLocalFileStore().store(this,
                SharedPreferenceKeys.SELECTED_DESTINATION, locationData);
    }


    private void setDefaultSelectedDestination() {
        Gson gson = new Gson();

        String selectedData = LocalStorageService.sharedInstance().getLocalFileStore().getString(this,
                SharedPreferenceKeys.SELECTED_DESTINATION);
        LocationModel locationModel = gson.fromJson(selectedData, LocationModel.class);

        for (LocationModel locationModel1 : locationList) {
            if (locationModel1.getId() == locationModel.getId()) {
                locationModel1.setAddressSelection(true);
            }
        }
        mChangeDestinationAdapter.notifyDataSetChanged();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mChangeDestinationAdapter != null) {
            mChangeDestinationAdapter.getFilter().filter(s);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onSearchFinished(int count) {
        if (count == 0) {
//            tvBusinessType.setVisibility(View.GONE);
            rvItems.setVisibility(View.GONE);
            rlNOResults.setVisibility(View.VISIBLE);

        } else {
            rlNOResults.setVisibility(View.GONE);
//            tvBusinessType.setVisibility(View.VISIBLE);
            rvItems.setVisibility(View.VISIBLE);

        }
    }
}
