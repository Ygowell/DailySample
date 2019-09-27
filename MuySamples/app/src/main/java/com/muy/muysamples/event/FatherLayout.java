package com.muy.muysamples.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.muy.muysamples.MuyLog;

public class FatherLayout extends FrameLayout {

    boolean onlyPrintOnce = true;

    public FatherLayout(Context context) {
        super(context);
    }

    public FatherLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FatherLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MuyLog.d("FatherLayout ---> dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MuyLog.d("FatherLayout ---> onInterceptTouchEvent");
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MuyLog.d("FatherLayout --->  onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onlyPrintOnce = true;
                MuyLog.d("FatherLayout --->  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (onlyPrintOnce) {
                    MuyLog.d("FatherLayout --->  ACTION_MOVE");
                    onlyPrintOnce = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                MuyLog.d("FatherLayout --->  ACTION_UP");
                break;
            default:
                break;
        }
        return true;
    }
}
