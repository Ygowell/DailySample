package com.muy.muysamples.wrapper;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = analysisClassInfo(this);
        Result obj = (Result) gson.fromJson(result, clazz);
        onSuccess(obj);
    }

    public abstract void onSuccess(Result result);

    private Class<?> analysisClassInfo(Object obj) {
        // 得到原始类型，参数化类型，类型变量
        Type genType = obj.getClass().getGenericSuperclass();
        Type[] typeArguments = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) typeArguments[0];
    }

    @Override
    public void onFailure() {
    }
}
