package com.tracotech.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tracotech.adapters.SelectDestinationAdapter;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.models.LocationModel;
import com.tracotech.services.storage.LocalFileStore;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.tracoshop.R;
import com.tracotech.utils.AppUtils;
import com.tracotech.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectDestinationDialogFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.rvLocations)
    RecyclerView mRvLocations;

    private SelectDestinationAdapter mDestinationAdapter;
    private List<LocationModel> locationList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initUi();
    }

    private void initUi() {
        locationList = AppUtils.getLocationList(getActivity());
        mRvLocations.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDestinationAdapter = new SelectDestinationAdapter(this, locationList);
        mRvLocations.setAdapter(mDestinationAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_destination, container, false);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.llContainer:
                if (v.getTag() instanceof LocationModel) {
                    LocationModel locationModel = (LocationModel) v.getTag();
                    for (LocationModel locationModel1 : locationList) {
                        locationModel1.setAddressSelection(false);
                    }
                    locationModel.setAddressSelection(true);
                    storeSelectedDestination(locationModel);
                    mDestinationAdapter.notifyDataSetChanged();
                    dismiss();
                }
                break;
        }

    }

    private void storeSelectedDestination(LocationModel locationModel) {
        Gson gson = new Gson();
        String locationData = gson.toJson(locationModel);
        LocalStorageService.sharedInstance().getLocalFileStore().store(getActivity(),
                SharedPreferenceKeys.SELECTED_DESTINATION, locationData);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            int width = (int) (AppUtils.getDeviceWidthOHeight(getActivity(), 0) * 0.95);
            int height = (int) (AppUtils.getDeviceWidthOHeight(getActivity(), 1) * 0.5);
            if (getDialog() != null && getDialog().getWindow() != null) {
                getDialog().getWindow().setLayout(width, height);
            }
        }
    }
}
