package com.muy.muysamples.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muy.muysamples.R

class WidgetSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cutom_view)
//        setContentView(R.layout.placeholder)
//        iv_app_icon.setOnClickListener { v -> placeholder.setContentId(v.id) }
    }
}