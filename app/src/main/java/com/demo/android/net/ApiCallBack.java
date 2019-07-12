package com.demo.android.net;

/**
 * Created by jinxh on 16/5/30.
 */
public abstract class ApiCallBack<T> {
    public abstract void onSuccess(T model);

    public void onSuccess(T model,int count,int page){

    }
    public void onSuccess(T model,String msg){

    }
    public void onStart() {

    }
    public abstract void onFailure(int code, String msg);

    public void onCompleted() {

    }
}
