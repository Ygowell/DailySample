package com.muy.muysamples.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.muy.muysamples.MuyLog;
import com.muy.muysamples.R;

public class EventHandleActivity extends AppCompatActivity {

    boolean onlyPrintOnce = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handle);

        findViewById(R.id.view_child).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MuyLog.d("ChildView: onTouch");
                return false;
            }
        });

        findViewById(R.id.view_father).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MuyLog.d("FatherLayout: onTouch");
                return false;
            }
        });
    }

    // 事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MuyLog.d("Activity: dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MuyLog.d("Activity: onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onlyPrintOnce = true;
                MuyLog.d("Activity: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (onlyPrintOnce) {
                    MuyLog.d("Activity: ACTION_MOVE");
                    onlyPrintOnce = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                MuyLog.d("Activity: ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
