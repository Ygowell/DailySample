package com.muy.muysamples.permission;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.muy.muysamples.MuyLog;
import com.muy.muysamples.permission.listener.PermissionCallback;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements PermissionCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        MuyLog.d("Success");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.onRequestPermissionResult(requestCode, permissions, grantResults, this);
    }
}
