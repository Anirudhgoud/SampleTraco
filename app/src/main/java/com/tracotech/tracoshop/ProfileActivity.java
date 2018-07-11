package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tracotech.constants.AppConstants;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.AddressModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.viewmodels.ProfileViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends ParentAppCompatActivity {

    @BindView(R.id.tvProfileName)
    TextView tvProfileName;

    @BindView(R.id.tvProfileLocation)
    TextView tvProfileLocation;

    @BindView(R.id.tvCustomerId)
    TextView tvCustomerId;

    @BindView(R.id.tvContactNo)
    TextView tvContactNo;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.ivProfile)
    ImageView ivProfile;

    private ResponseModel adressDataResponseModel = new ResponseModel();
    private ProfileViewModel mProfileViewModel;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_profile);
    }

    @Override
    public void doInitialSetup() {
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        initToolBar();
        mProfileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        initAddressDataObservers();

        String fName = LocalStorageService.sharedInstance().getLocalFileStore().
                getString(this, SharedPreferenceKeys.FIRST_NAME);
        String lName = LocalStorageService.sharedInstance().getLocalFileStore().
                getString(this, SharedPreferenceKeys.LAST_NAME);

        tvProfileName.setText(fName.concat(lName));
        tvContactNo.setText(LocalStorageService.sharedInstance().getLocalFileStore().getString(
                this, SharedPreferenceKeys.CONTACT_NO));

        String profileUrl = LocalStorageService.sharedInstance().getLocalFileStore().
                getString(this, SharedPreferenceKeys.PROFILE_URL);

        if (!TextUtils.isEmpty(profileUrl)) {
            Glide.with(this).load(profileUrl).into(ivProfile);
        }

        fetchAddress();
    }

    private void fetchAddress() {
        showProgressDialog();
        mProfileViewModel.fetchAddress(new NetworkResponseChecker() {
                                       }, adressDataResponseModel,
                LocalStorageService.sharedInstance().getLocalFileStore().
                        getInt(this, SharedPreferenceKeys.BUSINESS_ID));
    }

    private void initAddressDataObservers() {
        adressDataResponseModel.getToLogout().observe(this, logoutObserver);
        adressDataResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                dismissProgressDialog();
                if (aBoolean) {
                    dismissProgressDialog();

                    String addressLine1 = "";
                    String addressLine2 = "";

                    String city = "";
                    String country = "";


                    AddressModel addressModel = mProfileViewModel.getAddressResponse();
                    if (addressModel != null) {
                        if (!TextUtils.isEmpty(addressModel.getCity())) {
                            city = addressModel.getCity();
                        }
                        if (!TextUtils.isEmpty(addressModel.getCountry())) {
                            country = addressModel.getCountry();
                        }
                        String location = city + AppConstants.COMMA + country;
                        tvProfileLocation.setText(location);

                        if (addressModel.getAddress_line_1() != null) {
                            addressLine1 = addressModel.getAddress_line_1();
                        }

                        if (addressModel.getAddress_line_2() != null) {
                            addressLine2 = addressModel.getAddress_line_2();
                        }

                        String address = addressLine1 + addressLine2;
                        tvAddress.setText(address);
                    }
                }
            }
        });
        adressDataResponseModel.getErrorMessage().observe(this, errorObserver);
    }

    private void initToolBar() {
        setToolbarLeftIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void onClickWithId(View view) {

        switch (view.getId()) {
            case R.id.bt_top_left:
                onBackPressed();
                break;
        }
    }
}
