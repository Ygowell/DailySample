package com.muy.muysamples.permission;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.List;

public class PermissionActivity extends BaseActivity {

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        if (PermissionManager.somePermissionPermanentlyDenied(this, perms)) {
            new AlertDialog.Builder(this)
                    .setTitle("权限设置")
                    .setMessage("你需要去设置开启")
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
