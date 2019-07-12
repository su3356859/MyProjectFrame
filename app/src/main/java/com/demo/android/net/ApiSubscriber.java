package com.demo.android.net;



import com.demo.android.app.AppContext;
import com.demo.android.bean.ResponseBean;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

//import com.orhanobut.logger.Logger;

/**
 * Created by jinxh on 16/5/30.
 */
public class ApiSubscriber<T> extends Subscriber<ResponseBean<T>> {

    public static int UNKNOWN_CODE = -1;
    private ApiCallBack apiCallback;


    public ApiSubscriber(ApiCallBack apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onStart() {
        super.onStart();
        apiCallback.onStart();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException || e instanceof UnknownHostException) {
            apiCallback.onFailure(UNKNOWN_CODE, AppContext.NET_ERROR_MSG);
        } else {
            apiCallback.onFailure(UNKNOWN_CODE, e.getMessage());
        }
        apiCallback.onCompleted();
    }

    private static final String TAG = "ApiSubscriber";

    @Override
    public void onNext(ResponseBean<T> httpBean) {
//        if(httpBean!=null&&httpBean.getResults()!=null){
////            Logger.t(TAG).e("onNext : " + httpBean.toString());
////            Logger.t(TAG).e("onNext : " + httpBean.getResults().toString());
//        }

        if (httpBean.isSucc()) {
            apiCallback.onSuccess(httpBean.getResults());
            apiCallback.onSuccess(httpBean.getResults(),httpBean.getMsg());
            if(httpBean.getCount()!=0){
                apiCallback.onSuccess(httpBean.getResults(),httpBean.getCount(),httpBean.getPage());
            }else {
                apiCallback.onSuccess(httpBean.getResults(),httpBean.getTotalRowsCount(),httpBean.getTotalPages());
            }

        } else {
            apiCallback.onFailure(-1, httpBean.getMsg());
        }
    }
}
