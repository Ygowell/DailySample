package com.muy.muysamples.permission.helper;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.List;

public abstract class PermissionHelper {

    private Activity activity;

    PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    public static PermissionHelper newInstance(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return new LowApiPermissionHelper(activity);
        }
        return new ActivityPermissionHelper(activity);
    }

    Activity getHost() {
        return activity;
    }

    public abstract void requestPermissions(int requestCode, String... perms);

    public abstract boolean shouldShowRequestPermissionRationale(@NonNull String deniedPermission);

    public boolean somePermissionPermanentlyDenied(@NonNull List<String> deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRequestPermissionRationale(deniedPermission)) {
                // 点击拒绝且勾选“不再询问”
                return true;
            }
        }
        return false;
    }
}
