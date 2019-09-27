package com.muy.muysamples.wrapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper implements IHttpProcessor {

    private static HttpHelper instance;

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    // 持有HttpProcessor
    private static IHttpProcessor mHttpProcessor;

    // 指定具体的网络访问类
    public static void init(IHttpProcessor processor) {
        mHttpProcessor = processor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        // 直接做网络访问操作，调用第三方
        String finalUrl = appendParams(url, params);
        mHttpProcessor.post(url, params, callback);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (builder.indexOf("?") <= 0) {
            builder.append("?");
        } else if (!builder.toString().endsWith("?")) { // TODO: ???
            builder.append("&");
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.append("&");
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(encode(entry.getValue().toString()));
        }
        return builder.toString();
    }

    private String encode(String value) {
        String result = null;
        try {
            result = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


}
