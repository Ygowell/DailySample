package com.muy.muysamples;

import android.app.Application;
import android.content.Context;

/**
 * Created by James on 2019-11-30.
 * Desc:
 */
public class App extends Application {

    private static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
