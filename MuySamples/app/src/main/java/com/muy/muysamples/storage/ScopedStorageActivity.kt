package com.muy.muysamples.storage

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_scoped_storage.*
import java.io.File

class ScopedStorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoped_storage)

        button.setOnClickListener {
            mkdir()
        }
    }

    private fun mkdir() {
        val file = File(Environment.getExternalStorageDirectory().path, "MIUI")

        if (file.exists()) {
            Log.d("Muy", "sdcard exists! ${file.path}")
            val file1 = File(file, "Muy_Test")
            if (!file1.exists()) {
                Log.d("Muy", "test file doesn't exist! ${file1.path}")
                file1.mkdir()
            } else {
                Log.d("Muy", "test file exists!")
            }
        } else {
            Log.d("Muy", "sdcard doesn't exist")
        }
    }
}