package com.tracotech.tracoshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CartActivity extends ParentAppCompatActivity {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_cart);
        init();
    }

    private void init(){
        initToolbar();
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
