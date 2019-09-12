package com.muy.muysamples.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.muy.muysamples.MuyLog;

public class ChildView extends View {

    boolean onlyPrintOnce = true;

    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        MuyLog.d("ChildView: dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MuyLog.d("ChildView: onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onlyPrintOnce = true;
                MuyLog.d("ChildView: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (onlyPrintOnce) {
                    MuyLog.d("ChildView: ACTION_MOVE");
                    onlyPrintOnce = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                MuyLog.d("ChildView: ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
