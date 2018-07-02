package com.tracotech.tracoshop;

import android.os.Bundle;

public class ProductListingActivity extends ParentAppCompatActivity {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_product_listing);
        init();
    }

    private void init(){
        initialiseToolbar();
    }

    private void initialiseToolbar() {
        setToolbarLeftIcon(R.drawable.ic_back_arrow);
        //setToolbarRightIcon();
    }

    @Override
    public void doInitialSetup() {

    }

    @Override
    public void onClickWithId(int resourceId) {

    }
}
