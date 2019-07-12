package com.demo.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.android.R;
import com.demo.android.adapter.MessageListAdapter;
import com.demo.android.base.BasePresent;
import com.demo.android.base.MvpFragment;
import com.demo.android.present.SetWarnPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */

public class SetWarnFragment extends MvpFragment {
    @Bind(R.id.listView)
    ListView listView;

    @Override
    protected BasePresent createPresenter() {
        return new SetWarnPresent();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set_warn;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        List list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("");
        }
        MessageListAdapter messageListAdapter = new MessageListAdapter(getActivity(), list, R.layout.item_message);
        listView.setAdapter(messageListAdapter);
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
