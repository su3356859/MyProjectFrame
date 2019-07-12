package com.demo.android.activity;


//import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.android.R;
import com.demo.android.adapter.MainAdapter;
import com.demo.android.base.BaseActivity;
import com.demo.android.fragment.CustomerFragment;
import com.demo.android.fragment.FormFragment;
import com.demo.android.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @Bind(R.id.vp_fragment)
    ViewPager vpFragment;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.ll_home)
    LinearLayout llHome;
    @Bind(R.id.numszongshu)
    TextView numszongshu;
    @Bind(R.id.iv_message)
    ImageView ivMessage;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.ll_message_tab)
    LinearLayout llMessageTab;
    @Bind(R.id.iv_me)
    ImageView ivMe;
    @Bind(R.id.tv_me)
    TextView tvMe;
    @Bind(R.id.ll_me)
    LinearLayout llMe;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FormFragment());
        fragments.add(new MessageFragment());
        fragments.add(new CustomerFragment());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
        vpFragment.setOffscreenPageLimit(fragments.size());
        vpFragment.setAdapter(mainAdapter);
    }

    @Override
    protected void initLinstener() {
        vpFragment.setOnPageChangeListener(this);
        llHome.setOnClickListener(this);
        llMessageTab.setOnClickListener(this);
        llMe.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initIconState(position);

    }

    private void initIconState(int position) {
        switch (position) {
            case 0:
                ivHome.setImageResource(R.mipmap.ic_tab_home_selected);
                ivMessage.setImageResource(R.mipmap.ic_tab_message_default);
                ivMe.setImageResource(R.mipmap.ic_tab_me_default);
                tvHome.setTextColor(getResources().getColor(R.color.app_color));
                tvMessage.setTextColor(getResources().getColor(R.color.dark));
                tvMe.setTextColor(getResources().getColor(R.color.dark));
                break;
            case 1:
                ivHome.setImageResource(R.mipmap.ic_tab_home_default);
                ivMessage.setImageResource(R.mipmap.ic_tab_messsage_selected);
                ivMe.setImageResource(R.mipmap.ic_tab_me_default);
                tvHome.setTextColor(getResources().getColor(R.color.dark));
                tvMessage.setTextColor(getResources().getColor(R.color.app_color));
                tvMe.setTextColor(getResources().getColor(R.color.dark));
                break;
            case 2:
                ivHome.setImageResource(R.mipmap.ic_tab_home_default);
                ivMessage.setImageResource(R.mipmap.ic_tab_message_default);
                ivMe.setImageResource(R.mipmap.ic_tab_me_selected);
                tvHome.setTextColor(getResources().getColor(R.color.dark));
                tvMessage.setTextColor(getResources().getColor(R.color.dark));
                tvMe.setTextColor(getResources().getColor(R.color.app_color));
                break;

        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                initIconState(0);
                vpFragment.setCurrentItem(0);
                break;
            case R.id.ll_message_tab:
                initIconState(1);
                vpFragment.setCurrentItem(1);
                break;
            case R.id.ll_me:
                initIconState(2);
                vpFragment.setCurrentItem(2);
                break;
        }
    }
}
