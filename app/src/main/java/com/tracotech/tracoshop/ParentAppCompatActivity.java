package com.tracotech.tracoshop;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tracotech.helpers.uihelpers.AlertDialogHelper;
import com.tracotech.interfaces.NetworkChangeListener;
import com.tracotech.interfaces.OnPermissionResult;
import com.tracotech.models.ErrorModel;
import com.tracotech.services.network.NetworkChecker;
import com.tracotech.services.storage.LocalStorageService;

import java.util.Vector;

/**
 * Created by vishalm on 07/02/18.
 */

public abstract class ParentAppCompatActivity extends AppCompatActivity implements View.OnClickListener {

    public abstract void onActivityCreated(Bundle savedInstanceState);

    public abstract void doInitialSetup();

    public abstract void onClickWithId(int resourceId);

    protected Observer logoutObserver = new Observer() {
        @Override
        public void onChanged(@Nullable Object o) {
            logoutUser();
        }
    };

    protected Observer<ErrorModel> errorObserver = new Observer<ErrorModel>() {
        @Override
        public void onChanged(@Nullable ErrorModel errorModel) {
            if (errorModel.isShowDialog())
                dismissProgressDialog();
            showNetworkRelatedDialogs(errorModel.getErrorMessage());
//                alertDialogHelper.showOkAlertDialog(ParentAppCompatActivity.this,
//                        getString(R.string.error), errorModel.getErrorMessage());
        }
    };

    private NetworkChangeListener networkChangeListener;
    private AlertDialogHelper alertDialogHelper;
    private Dialog progressBarDialog;

    private OnPermissionResult permissionResult;

    private BroadcastReceiver connectivityChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (networkChangeListener != null
                    && new NetworkChecker().isNetworkAvailable(ParentAppCompatActivity.this)) {
                closeAllNetworkDialogs();
                networkChangeListener.onNetworkConnected();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityCreated(savedInstanceState);
    }

    protected void setResources(int resourceIdentifier) {
        setContentView(resourceIdentifier);
        doInitialSetup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
        unregisterReceiverForNetworkChange();
    }

    protected void setToolbarLeftIcon(int resId){
        ImageView leftToolbarButton = findViewById(R.id.bt_top_left);
        leftToolbarButton.setVisibility(View.VISIBLE);
        leftToolbarButton.setOnClickListener(this);
        leftToolbarButton.setImageResource(resId);
    }

    protected void showSearchBar(){
        LinearLayout searchLL = findViewById(R.id.search_ll);
        searchLL.setVisibility(View.VISIBLE);
        EditText searchBar = findViewById(R.id.et_search);
        ImageView searchIcon = findViewById(R.id.iv_search);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                    searchIcon.setVisibility(View.GONE);
                else searchIcon.setVisibility(View.VISIBLE);
            }
        });
        searchBar.clearFocus();
    }

    protected void setTitle(String title){
        TextView titleTv = findViewById(R.id.tv_title);
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText(title);
    }

    protected void setToolbarRightIcon(int resId){
        ImageView rightToolbarButton = findViewById(R.id.bt_top_right);
        rightToolbarButton.setVisibility(View.VISIBLE);
        rightToolbarButton.setOnClickListener(this);
        rightToolbarButton.setImageResource(resId);
    }

//    protected void setToolBarColor(int colorId){
//        RelativeLayout toolbarContainer = findViewById(R.id.toolbar_container);
//        toolbarContainer.setBackgroundColor(colorId);
//    }

    public void logoutUser(){
        LocalStorageService.sharedInstance().getLocalFileStore().clearAllPreferences(this);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View view) {
        onClickWithId(view.getId());
    }

    private void registerReceiverForNetworkChange() {
        registerReceiver(connectivityChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void unregisterReceiverForNetworkChange() {
        if (connectivityChangeReceiver != null) {
            try {
                unregisterReceiver(connectivityChangeReceiver);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    protected void startNetworkMonitoring(NetworkChangeListener networkChangeListener) {
        this.networkChangeListener = networkChangeListener;
        registerReceiverForNetworkChange();
    }

    public void showNetworkRelatedDialogs(String message) {
        alertDialogHelper = new AlertDialogHelper();
        alertDialogHelper.showOkAlertDialog(this, getResources().getString(R.string.error), message);
    }

    public void showConfirmationDialog(String title, String message,
                                       DialogInterface.OnClickListener positive,
                                       DialogInterface.OnClickListener negative) {
        alertDialogHelper = new AlertDialogHelper();
        alertDialogHelper.showYesNoAlertDialog(this, title, message, positive, negative);
    }

    public void showProgressDialog() {
        if (progressBarDialog == null) {
            progressBarDialog = new Dialog(ParentAppCompatActivity.this, R.style.ProgressBarTheme);
            progressBarDialog.setContentView(LayoutInflater.from(this).inflate(
                    R.layout.progress_dialog_layout, null, false), new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            progressBarDialog.setCancelable(false);
            ProgressBar progressBar = progressBarDialog.findViewById(R.id.progress_bar);
            progressBar.getIndeterminateDrawable().setColorFilter(
                    getResources().getColor(R.color.colorPrimary),
                    android.graphics.PorterDuff.Mode.SRC_IN);
            progressBarDialog.getWindow().setGravity(Gravity.CENTER);
            progressBarDialog.show();
        }
    }


    public void dismissProgressDialog() {
        if (progressBarDialog != null) {
            progressBarDialog.dismiss();
            progressBarDialog = null;
        }
    }

    private void closeAllNetworkDialogs() {
        if (alertDialogHelper != null) {
            Vector<AlertDialog> dialogs = alertDialogHelper.getDialogs();
            for (final AlertDialog dialog : dialogs) {
                if (dialog != null) {
                    runOnUiThread(dialog::dismiss);
                }
            }
            alertDialogHelper = null;
        }
    }

    protected boolean isPermissionGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this,
                    permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    protected void requestPermission(String[] permissions, OnPermissionResult permissionResult) {
        this.permissionResult = permissionResult;
        if (!isPermissionGranted(permissions)) {
            ActivityCompat.requestPermissions(this,
                    permissions, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionResult != null) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionResult.onPermissionGranted();
            } else {
                permissionResult.onPermissionDenied();
            }
        }
    }
}
