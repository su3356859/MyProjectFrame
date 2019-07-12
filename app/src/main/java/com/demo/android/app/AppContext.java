package com.demo.android.app;

import android.support.multidex.MultiDexApplication;

import com.demo.android.R;

/**
 * Created by luowei on 2017/11/8.
 */

public class AppContext extends MultiDexApplication {
    // 配置是否要沉浸式头部
    public final static boolean HIDDEN_STATUS_BAR = true;
    public static final String API_BASE_URL = "";//主url
    public static String NET_ERROR_MSG;
    private static AppContext mAppContext;
    private int loginType;//登录类型,1是经纪人,2是店长,3是区域经纪人

    @Override
    public void onCreate() {
        super.onCreate();
        initConstant();

    }

    private void initConstant() {
        NET_ERROR_MSG = getString(R.string.alert_net_error);
    }

    /**
     * 获取appcontext实例对象
     */
    public static AppContext getInstance() {
        if (mAppContext == null) {
            mAppContext = new AppContext();
        }
        return mAppContext;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
