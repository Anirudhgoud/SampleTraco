package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tracotech.adapters.ChangeDestinationAdapter;
import com.tracotech.adapters.HomeScreenAdapter;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.models.LocationModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.utils.AppUtils;

import java.util.List;

import butterknife.BindView;

public class ChangeDestinationActivity extends ParentAppCompatActivity {

    @BindView(R.id.rlNOResults)
    RelativeLayout rlNOResults;

    @BindView(R.id.rvList)
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
        initUi();
    }

    private void initUi() {
        locationList = AppUtils.getLocationList(this);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        mChangeDestinationAdapter = new ChangeDestinationAdapter(this,
                locationList);
        rvItems.setAdapter(mChangeDestinationAdapter);
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
        }
    }

    private void storeSelectedDestination(LocationModel locationModel) {
        Gson gson = new Gson();
        String locationData = gson.toJson(locationModel);
        LocalStorageService.sharedInstance().getLocalFileStore().store(this,
                SharedPreferenceKeys.SELECTED_DESTINATION, locationData);
    }
}
