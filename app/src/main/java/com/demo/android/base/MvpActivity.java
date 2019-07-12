package com.demo.android.base;

import android.os.Bundle;

import com.demo.android.mvp.MvpView;


/**
 * Created by jinxh on 16/1/4.
 * QQ:123489504
 */
public abstract class MvpActivity<P extends BasePresent> extends BaseActivity implements MvpView {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView(false);
        }
    }
}
