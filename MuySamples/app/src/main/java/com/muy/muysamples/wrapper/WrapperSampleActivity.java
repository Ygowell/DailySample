package com.muy.muysamples.wrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.muy.muysamples.R;

import java.util.HashMap;
import java.util.Map;

public class WrapperSampleActivity extends AppCompatActivity {

    private static String url = "https://v.juhe.cn/historyWeather/citys";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper_sample);

        HttpHelper.init(new VolleyProcessor(getApplicationContext()));

        Map<String, Object> params = new HashMap<>();
        params.put("provice_id", 1);
        params.put("key", "dddds");
        HttpHelper.getInstance().post(url, params, new HttpCallback<ResponseClass>() {
            @Override
            public void onSuccess(ResponseClass responseClass) {
                Log.d("Muy", "=====> " + new Gson().toJson(responseClass));
            }
        });
    }
}
