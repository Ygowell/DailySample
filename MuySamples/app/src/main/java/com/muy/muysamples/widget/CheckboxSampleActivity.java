package com.muy.muysamples.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muy.muysamples.R;

public class CheckboxSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_sample);

        findViewById(R.id.checkBox).setEnabled(false);
    }
}
