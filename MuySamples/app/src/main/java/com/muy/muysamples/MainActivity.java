package com.muy.muysamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView functionsRv = findViewById(R.id.rv_function);
        functionsRv.setLayoutManager(new LinearLayoutManager(this));
        functionsRv.setAdapter(new FunctionAdapter(this));
        functionsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}