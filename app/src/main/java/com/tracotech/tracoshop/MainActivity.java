package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.viewmodels.LoginViewModel;

public class MainActivity extends ParentAppCompatActivity{

    private LoginViewModel loginViewModel;
    private ResponseModel loginResponseModel;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.setResources(R.layout.activity_main);
    }

    @Override
    public void doInitialSetup() {
        init();
        loginViewModel.login(new NetworkResponseChecker(){}, loginResponseModel);
    }

    @Override
    public void onClickWithId(int resourceId) {

    }

    private void init(){
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        initObservers();
    }

    private void initObservers() {
        loginResponseModel.getToLogout().observe(this, logoutObserver);
        loginResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean)
                    loginViewModel.getLoginData();
            }
        });

        loginResponseModel.getErrorMessage().observe(this, errorObserver);
    }

}
