package com.demo.android.fragment;

import com.demo.android.R;
import com.demo.android.base.BasePresent;
import com.demo.android.base.MvpFragment;
import com.demo.android.present.BusinessPresent;

/**
 */

public class BusinessFragment extends MvpFragment {
    @Override
    protected BasePresent createPresenter() {
        return new BusinessPresent();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
