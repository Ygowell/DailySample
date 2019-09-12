package com.muy.muysamples.permission.helper;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.muy.muysamples.MuyLog;

public class LowApiPermissionHelper extends PermissionHelper {

    LowApiPermissionHelper(Activity activity) {
        super(activity);
    }

    @Override
    public void requestPermissions(int requestCode, String... perms) {
        MuyLog.d("低于6.0版本无需运行时请求权限");
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String deniedPermission) {
        return false;
    }
}
