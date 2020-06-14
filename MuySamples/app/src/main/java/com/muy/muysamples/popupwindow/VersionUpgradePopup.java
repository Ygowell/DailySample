package com.muy.muysamples.popupwindow;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.muy.muysamples.R;

/**
 * Created by James on 2020-03-27.
 * Desc:
 */
public class VersionUpgradePopup extends PopupWindow {

    private static final String TAG = "VersionUpgradePopup";
    private Activity mActivity;
    private ViewGroup mDecorView;

    private OnUpgradeListener mListener;

    public interface OnUpgradeListener {
        void onUpdateNow();

        void onCancel();
    }

    public VersionUpgradePopup(Activity activity, UpdateData updateData) {
        super(activity);
        initView(activity, updateData);
    }

    public VersionUpgradePopup(ViewGroup parent, UpdateData updateData) {
        initView();
    }

    public void setOnUpgradeListener(OnUpgradeListener listener) {
        mListener = listener;
    }

    private void initView() {
    }

    private void initView(Activity activity, UpdateData updateData) {
        mActivity = activity;
        mDecorView = (ViewGroup) activity.getWindow().getDecorView();
        final View view = LayoutInflater.from(activity).inflate(R.layout.version_upgrade, mDecorView, false);
        setContentView(view);
        int widthSpec = View.MeasureSpec.makeMeasureSpec(mDecorView.getMeasuredWidth(), View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(mDecorView.getMeasuredHeight(), View.MeasureSpec.AT_MOST);
        view.measure(widthSpec, heightSpec);

        TextView apkInfoTv = view.findViewById(R.id.tv_apk_info);
        TextView contentTv = view.findViewById(R.id.tv_update_content);
        Button updateNowBtn = view.findViewById(R.id.btn_update_now);

        apkInfoTv.setText(updateData.versionName + updateData.apkSize);
        contentTv.setText(updateData.updateLog);
        updateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (mListener != null) {
                    mListener.onUpdateNow();
                }
            }
        });

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int viewHeight = view.getMeasuredHeight();
                Log.d(TAG, "Measured height = " + viewHeight); // 959

                return true;
            }
        });

        int viewHeight = view.getMeasuredHeight();
        Log.d(TAG, "final Measured height = " + viewHeight); // 959
        Log.d(TAG, "final height = " + view.getHeight());

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        setHeight(viewHeight);
        setAnimationStyle(R.style.VersionUpgradeWindowStyle);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        setOutsideTouchable(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mListener != null) {
                    mListener.onCancel();
                }
                dimBackground(0.5f, 1.0f);
            }
        });
    }

    public void show() {
        dimBackground(1.0f, 0.5f);
        showAtLocation(mDecorView, Gravity.BOTTOM, 0, 0);
    }

    public void show(View view) {
        dimBackground(1.0f, 0.5f);
        showAtLocation(view, Gravity.BOTTOM, 100, 100);
    }

    private void dimBackground(float from, float to) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Window window = mActivity.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }
}
