package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tracotech.adapters.DrawerItemAdapter;
import com.tracotech.adapters.HomeScreenAdapter;
import com.tracotech.constants.AppConstants;
import com.tracotech.constants.IntentConstants;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.constants.UrlConstants;
import com.tracotech.fragments.SelectDestinationDialogFragment;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.interfaces.SearchResultsListener;
import com.tracotech.models.CustomersModel;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.utils.AppUtils;
import com.tracotech.viewmodels.HomeViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends ParentAppCompatActivity implements View.OnClickListener, TextWatcher,
        SearchResultsListener {

    @BindView(R.id.rvDrawer)
    RecyclerView mDrawerRv;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvBusinessType)
    TextView tvBusinessType;

    @BindView(R.id.rlNOResults)
    RelativeLayout rlNOResults;

    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    @BindView(R.id.rvList)
    RecyclerView rvItems;

    @BindView(R.id.etSearch)
    EditText mEtSearch;

    private HomeViewModel mHomeViewModel;
    private HomeScreenAdapter mHomeScreenAdapter;
    private ResponseModel homeResponseModel = new ResponseModel();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_homescreen);
    }

    @Override
    public void doInitialSetup() {
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        String name = LocalStorageService.sharedInstance().getLocalFileStore().getString(this,
                SharedPreferenceKeys.FIRST_NAME) + AppConstants.SPACE +
                LocalStorageService.sharedInstance().getLocalFileStore().getString(this,
                        SharedPreferenceKeys.LAST_NAME);
        tvName.setText(name);
        mEtSearch.addTextChangedListener(this);
        initObservers();
        initDrawerView();
        showDestinationDialog();
        fetchConsumersOrSuppliersData();
    }

    private void initObservers() {
        homeResponseModel.getToLogout().observe(this, logoutObserver);

        homeResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean)
                    dismissProgressDialog();
                mHomeScreenAdapter = new HomeScreenAdapter(mHomeViewModel.getCustomersList());
                mHomeScreenAdapter.setSearchResultsListener(HomeActivity.this);
                mHomeScreenAdapter.setTapListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startProductListing((Integer) v.getTag());
                    }
                });
                rvItems.setAdapter(mHomeScreenAdapter);
            }
        });

        homeResponseModel.getErrorMessage().observe(this, errorObserver);
    }

    private void startProductListing(int selectedPosition) {
        Intent intent = new Intent(this, ProductListingActivity.class);
        intent.putExtra(IntentConstants.SELECTED_CUSTOMER, selectedPosition);
        intent.putParcelableArrayListExtra(IntentConstants.CUSTOMERS_LIST, new ArrayList<>(mHomeViewModel.getCustomersList()));
        startActivity(intent);
    }

    private void fetchConsumersOrSuppliersData() {
        showProgressDialog();
        String url = "";

        AppUtils.getLocationList(this);

        if (LocalStorageService.sharedInstance().getLocalFileStore().getBoolean(this,
                SharedPreferenceKeys.ORDER_LOGIN)) {
            url = UrlConstants.PRODUCER_BUSINESS;
            tvBusinessType.setText(getString(R.string.suppliers));
        } else if (LocalStorageService.sharedInstance().getLocalFileStore().getBoolean(this,
                SharedPreferenceKeys.SALES_LOGIN)) {
            url = UrlConstants.CONSUMERS_BUSINESS;
            tvBusinessType.setText(getString(R.string.customers));
        }
        mHomeViewModel.customersOSuppliersData(new NetworkResponseChecker() {
        }, homeResponseModel, url);
    }


    private void showDestinationDialog() {

        if (LocalStorageService.sharedInstance().getLocalFileStore().getBoolean(this,
                SharedPreferenceKeys.ORDER_LOGIN) && TextUtils.isEmpty(LocalStorageService.sharedInstance().
                getLocalFileStore().getString(this, SharedPreferenceKeys.SELECTED_DESTINATION))) {

            SelectDestinationDialogFragment editNameDialogFragment = new SelectDestinationDialogFragment();
            editNameDialogFragment.show(getSupportFragmentManager(), "select_destination_dialog");
            editNameDialogFragment.setCancelable(false);
        }
    }

    private void initDrawerView() {
        mDrawerRv.setLayoutManager(new LinearLayoutManager(this));
        mDrawerRv.setAdapter(new DrawerItemAdapter(this,
                AppUtils.getDrawerListItems(this)));

        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }


    @OnClick(R.id.ivClose)
    public void closeDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawers();
        }
    }

    @OnClick(R.id.ivHamburger)
    public void openDrawer() {
        if (mDrawer != null) {
            mDrawer.openDrawer(Gravity.START);
        }
    }

    @Override
    public void onClickWithId(int resourceId) {
//     Nothing to do
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//     Nothing to do
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mHomeScreenAdapter != null) {
            mHomeScreenAdapter.getFilter().filter(s);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
//     Nothing to do
    }

    @Override
    public void onSearchFinished(int count) {
        if (count == 0) {
            tvBusinessType.setVisibility(View.GONE);
            rvItems.setVisibility(View.GONE);
            rlNOResults.setVisibility(View.VISIBLE);

        } else {
            rlNOResults.setVisibility(View.GONE);
            tvBusinessType.setVisibility(View.VISIBLE);
            rvItems.setVisibility(View.VISIBLE);

        }
    }
}
