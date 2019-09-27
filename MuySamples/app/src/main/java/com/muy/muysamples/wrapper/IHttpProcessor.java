package com.muy.muysamples.wrapper;

import java.util.Map;

public interface IHttpProcessor {

    void post(String url, Map<String, Object> params, ICallback callback);
}
