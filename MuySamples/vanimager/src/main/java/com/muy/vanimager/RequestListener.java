package com.muy.vanimager;

import android.graphics.Bitmap;

interface RequestListener {

    void onSuccess(Bitmap bitmap);

    void onFail();
}
