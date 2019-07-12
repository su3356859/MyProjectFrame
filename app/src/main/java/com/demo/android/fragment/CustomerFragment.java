package com.demo.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.android.R;
import com.demo.android.base.BasePresent;
import com.demo.android.base.MvpFragment;
import com.demo.android.present.CustomerPresent;

import butterknife.ButterKnife;

/**
 *
 */

public class CustomerFragment extends MvpFragment implements View.OnClickListener {

    @Override
    protected BasePresent createPresenter() {
        return new CustomerPresent();
    }

    @Override
    protected void initLinstener() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
