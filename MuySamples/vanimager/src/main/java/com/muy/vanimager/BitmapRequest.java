package com.muy.vanimager;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

public class BitmapRequest {

    // 图片请求地址
    private String url;

    private Context context;

    private SoftReference<ImageView> imageView;

    // 占位图
    private int placeHolderImageId;

    private RequestListener requestListener;

    // 图片的标识
    private String urlMd5;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMd5 = MD5Utils.getMD5(url);
        return this;
    }

    public BitmapRequest placeHolder(int resId) {
        this.placeHolderImageId = resId;
        return this;
    }

    public BitmapRequest listener(RequestListener listener) {
        this.requestListener = listener;
        return this;
    }

    public void into(ImageView imageView) {
        this.imageView = new SoftReference<>(imageView);

    }
}
