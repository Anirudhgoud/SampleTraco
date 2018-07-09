package com.tracotech.tracoshop;

import android.os.Bundle;

public class ProfileActivity extends ParentAppCompatActivity {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_profile);
    }

    @Override
    public void doInitialSetup() {
        initUi();
    }

    private void initUi() {
        initToolBar();
    }

    private void initToolBar() {
        setToolbarLeftIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void onClickWithId(int resourceId) {

    }
}
