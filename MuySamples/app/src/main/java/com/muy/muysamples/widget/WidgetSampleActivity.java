package com.muy.muysamples.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.muy.muysamples.R;

public class WidgetSampleActivity extends AppCompatActivity {

    private BottomPopup popup;

    private ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_sample);

        root = findViewById(R.id.root);

        popup = new BottomPopup(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show(root);
            }
        });
    }
}
