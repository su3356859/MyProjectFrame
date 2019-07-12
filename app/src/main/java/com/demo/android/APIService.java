package com.demo.android;


import com.demo.android.bean.LoginRequestBean;
import com.demo.android.bean.ResponseBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by luowei on 2017/11/9.
 */

public interface APIService {
    @POST("/api/Member/MemberLogin")
    Observable<ResponseBean<LoginRequestBean>> login(@Body LoginRequestBean loginBean);
}
