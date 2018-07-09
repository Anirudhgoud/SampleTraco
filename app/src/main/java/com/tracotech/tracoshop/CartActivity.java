package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tracotech.adapters.CartListAdapter;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.viewmodels.CartViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends ParentAppCompatActivity {

    @BindView(R.id.rv_cart_products)
    RecyclerView cartRecyclerView;

    private CartListAdapter adapter;
    private CartViewModel viewModel;
    private ResponseModel cartProductsResponseModel = new ResponseModel();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_cart);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        initToolbar();
        initCartList();
    }

    private void initCartList() {
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CartListAdapter();
        cartRecyclerView.setAdapter(adapter);
        initObservers();
        viewModel.fetchCartProducts(new NetworkResponseChecker() {}, cartProductsResponseModel);
    }

    private void initObservers(){
        cartProductsResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                adapter.updateList(viewModel.getCartItems());
            }
        });
        cartProductsResponseModel.getErrorMessage().observe(this, errorObserver);
        cartProductsResponseModel.getToLogout().observe(this, logoutObserver);
    }

    private void initToolbar() {
        setToolbarLeftIcon(R.drawable.ic_back_arrow);
        setTitle(getString(R.string.cart));
    }

    @Override
    public void doInitialSetup() {

    }

    @Override
    public void onClickWithId(int resourceId) {
        switch (resourceId){
            case R.id.bt_top_left:
                finish();
                break;
        }
    }
}
