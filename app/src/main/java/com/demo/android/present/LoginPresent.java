package com.demo.android.present;

import com.demo.android.APIService;
import com.demo.android.base.BasePresent;
import com.demo.android.bean.LoginRequestBean;
import com.demo.android.bean.ResponseBean;
import com.demo.android.net.ApiCallBack;
import com.demo.android.net.ApiSubscriber;
import com.demo.android.net.RetrofitClient;

import rx.Observable;

/**
 * Created by luowei on 2017/11/9.
 */

public class LoginPresent extends BasePresent {
    public void login(String userName, String passWord) {
        LoginRequestBean loginBean = new LoginRequestBean();
        loginBean.setUserName(userName);
        loginBean.setPassWord(passWord);
        Observable<ResponseBean<LoginRequestBean>> login = RetrofitClient.builderRetrofit()
                .create(APIService.class).login(loginBean);

        addIOSubscription(login, new ApiSubscriber(new ApiCallBack() {
            @Override
            public void onSuccess(Object model) {

            }

            @Override
            public void onFailure(int code, String msg) {

            }
        }));
    }
}
