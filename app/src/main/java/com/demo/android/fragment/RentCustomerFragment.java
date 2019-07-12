package com.demo.android.fragment;

import com.demo.android.R;
import com.demo.android.base.BasePresent;
import com.demo.android.base.MvpFragment;
import com.demo.android.present.RentCustomerPresent;

/**
 */

public class RentCustomerFragment extends MvpFragment {
    @Override
    protected BasePresent createPresenter() {
        return new RentCustomerPresent();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rent_customer;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
