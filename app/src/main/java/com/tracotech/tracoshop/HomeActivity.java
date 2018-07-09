package com.tracotech.tracoshop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tracotech.adapters.DrawerItemAdapter;
import com.tracotech.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends ParentAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rvDrawer)
    RecyclerView mDrawerRv;


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
        initDrawerView();

    }

    private void initDrawerView() {

        mDrawerRv.setLayoutManager(new LinearLayoutManager(this));
        mDrawerRv.setAdapter(new DrawerItemAdapter(this,
                AppUtils.getDrawerListItems(this)));
    }

    @Override
    public void onClickWithId(int resourceId) {

    }
}
