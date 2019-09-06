package com.muy.library;

import android.app.Activity;

import java.lang.reflect.Field;

public class InjectManager {

    public static void inject(Activity activity) {
        injectViews(activity);
    }

    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
//            field.getAnnotation()
        }
    }
}
