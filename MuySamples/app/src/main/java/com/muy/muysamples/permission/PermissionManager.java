package com.muy.muysamples.permission;

import android.app.Activity;
import android.content.pm.PackageManager;

import com.muy.muysamples.permission.helper.PermissionHelper;
import com.muy.muysamples.permission.listener.PermissionCallback;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {

    public static void requestPermissions() {

    }

    /**
     * 处理请求回调
     *
     * @param requestCode
     * @param perms
     * @param grantResults
     * @param activity
     */
    public static void onRequestPermissionResult(int requestCode, String[] perms, int[] grantResults, Activity activity) {
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        // 将请求的权限分类
        for (int i = 0; i < perms.length; i++) {
            String perm = perms[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        if (!granted.isEmpty()) {
            if (activity instanceof PermissionCallback) {
                ((PermissionCallback) activity).onPermissionGranted(requestCode, granted);
            }
        }

        if (!denied.isEmpty()) {
            if (activity instanceof PermissionCallback) {
                ((PermissionCallback) activity).onPermissionDenied(requestCode, denied);
            }
        }

        if (!granted.isEmpty() && denied.isEmpty()) {

        }
    }

    public static boolean somePermissionPermanentlyDenied(Activity activity, List<String> perms) {
        return PermissionHelper.newInstance(activity).somePermissionPermanentlyDenied(perms);
    }
}
