package com.muy.vanimager;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 处理图片请求
 */
public class BitmapDispatcher extends Thread {

    private LinkedBlockingDeque<BitmapRequest> requestQueue;

    public BitmapDispatcher(LinkedBlockingDeque<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        super.run();

    }
}
