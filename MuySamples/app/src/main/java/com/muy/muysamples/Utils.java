package com.muy.muysamples;

import android.content.Context;
import android.view.WindowManager;

public class Utils {

    private static int sScreenHeight = -1;

    public static int getScreenHeight() {
        if (sScreenHeight == -1) {
            WindowManager wm = (WindowManager) App.getAppContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            sScreenHeight = wm.getDefaultDisplay().getHeight();
        }
        return sScreenHeight;
    }
}
