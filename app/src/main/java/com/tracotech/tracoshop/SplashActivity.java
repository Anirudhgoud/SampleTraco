package com.tracotech.tracoshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashActivity extends ParentAppCompatActivity {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_splash_screen);
    }

    @Override
    public void doInitialSetup() {
        new Handler().postDelayed(this::sheduleSplashScreen, 500);
    }

    @Override
    public void onClickWithId(View view) {

    }

    private void sheduleSplashScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}
