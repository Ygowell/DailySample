package com.muy.muysamples.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.muy.muysamples.R;
import com.muy.muysamples.Utils;

public class BottomPopup {

    private PopupWindow popupWindow;

    private View popupView;

    private ObjectAnimator animator;

    public BottomPopup(Context context) {
        popupView = LayoutInflater.from(context).inflate(R.layout.bottom_popup_view, null);
//        popupView.setOnTouchListener(touchListener);

        popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
                context.getResources().getDimensionPixelSize(R.dimen.bottom_popup_height));

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        int start = Utils.getScreenHeight();
        animator = ObjectAnimator.ofFloat(popupView, "translationY", start, 0);
        animator.setDuration(500);
    }

    public void show(View parent) {
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        animator.start();
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        int orgX, orgY;
        int offsetX, offsetY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    orgX = (int) event.getX();
                    orgY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    offsetX = (int) event.getRawX() - orgX;
                    offsetY = (int) event.getRawY() - orgY;
                    popupWindow.update(0, offsetY, -1, -1, true);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }

            return true;
        }
    };
}
