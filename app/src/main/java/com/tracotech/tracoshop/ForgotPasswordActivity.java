package com.tracotech.tracoshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.tracotech.utils.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordActivity extends ParentAppCompatActivity {

    @BindView(R.id.etPhoneNumber)
    EditText mEtPhoneNumber;

    @BindView(R.id.btContinue)
    Button btContinue;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_number_confirmation);
    }

    @Override
    public void doInitialSetup() {

    }

    @Override
    public void onClickWithId(int resourceId) {

    }

    @OnClick(R.id.btContinue)
    public void performSubmitPhoneNumber() {
        if (AppUtils.isValidEmailOrContactNumber(mEtPhoneNumber.getText().toString())) {
            submitPhoneNumber();
        }
    }

    private void submitPhoneNumber() {
        showProgressDialog();

    }


}
