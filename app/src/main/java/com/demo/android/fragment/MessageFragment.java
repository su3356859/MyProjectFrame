package com.demo.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.demo.android.R;
import com.demo.android.adapter.MainAdapter;
import com.demo.android.base.BasePresent;
import com.demo.android.base.MvpFragment;
import com.demo.android.present.MessagePresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */

public class MessageFragment extends MvpFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @Bind(R.id.set_rb)
    RadioButton setRb;
    @Bind(R.id.business_rb)
    RadioButton businessRb;
    @Bind(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.all_read)
    TextView allRead;
//    @Bind(R.id.fragment_top)
//    RelativeLayout fragmentTop;

    @Override
    protected BasePresent createPresenter() {
        return new MessagePresent();
    }

    @Override
    protected void initLinstener() {
        viewPager.setOnPageChangeListener(this);
        setRb.setOnClickListener(this);
        businessRb.setOnClickListener(this);
//        allRead.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SetWarnFragment());
        fragments.add(new SetWarnFragment());
        MainAdapter mainAdapter = new MainAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(mainAdapter);
        mainRadiogroup.check(R.id.set_rb);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mainRadiogroup.check(R.id.set_rb);
                break;
            case 1:
                mainRadiogroup.check(R.id.business_rb);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_rb:
                viewPager.setCurrentItem(0);
                break;
            case R.id.business_rb:
                viewPager.setCurrentItem(1);
                break;
//            case R.id.all_read://全部已读

//                break;
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
