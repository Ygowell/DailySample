package com.muy.muysamples.permission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muy.muysamples.R

class PermissionSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_sample)
    }

    private fun requestRuntimePermission(permissions : Array<String>) {
        var permissionList = ArrayList<String>()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
