package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tracotech.constants.AppConstants;
import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.services.storage.LocalStorageService;
import com.tracotech.utils.LogUtils;
import com.tracotech.viewmodels.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends ParentAppCompatActivity {

    @BindView(R.id.etNameONumber)
    EditText mEtNameONumber;

    @BindView(R.id.etPassword)
    EditText mEtPassword;

    @BindView(R.id.btLogin)
    Button btLogin;

    @BindView(R.id.tvForgotPsswd)
    TextView mTvForgotPsswd;

    @BindView(R.id.tvRequestAccount)
    TextView mTvRqAccount;

    private LoginViewModel loginViewModel;
    private ResponseModel loginResponseModel = new ResponseModel();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_log_in);
        if (isLoggedIn()) {
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void doInitialSetup() {
        ButterKnife.bind(this);
        initUi();

    }

    @Override
    public void onClickWithId(View view) {

    }


    private void initUi() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        initObservers();
    }


    @OnClick(R.id.btLogin)
    public void validateLogin() {
        if (isValidUsernamePassword()) {
            showProgressDialog();
            loginViewModel.login(new NetworkResponseChecker() {
            }, mEtNameONumber.getText().toString(), mEtPassword.getText().toString(), loginResponseModel);
        }

    }


    private boolean isValidUsernamePassword() {
        return mEtNameONumber.getText().length() >= AppConstants.PHONE_MIN_LENGTH && mEtPassword.getText().length() > 0;
    }


    private void initObservers() {
        loginResponseModel.getToLogout().observe(this, logoutObserver);
        loginResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean)
                    dismissProgressDialog();
//                loginViewModel.getLoginData();
                startHomeActivity();
            }
        });

        loginResponseModel.getErrorMessage().observe(this, errorObserver);
    }


    private boolean isLoggedIn() {
        if (!LocalStorageService.sharedInstance().getLocalFileStore().getString(
                LoginActivity.this, SharedPreferenceKeys.AUTH_TOKEN).isEmpty()) {
            LogUtils.error("AuthToken", LocalStorageService.sharedInstance().getLocalFileStore().getString(
                    LoginActivity.this, SharedPreferenceKeys.AUTH_TOKEN));
            return true;
        }
        return false;
    }
}
