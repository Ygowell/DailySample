package com.muy.muysamples.permission.listener;

import java.util.List;

public interface PermissionCallback {

    /**
     * 授权通过回调
     * @param requestCode 权限请求标识码
     * @param perms 请求的权限组
     */
    void onPermissionGranted(int requestCode, List<String> perms);

    /**
     * 授权被拒绝
     * @param requestCode 权限请求标识码
     * @param perms 请求的权限组
     */
    void onPermissionDenied(int requestCode, List<String> perms);
}
