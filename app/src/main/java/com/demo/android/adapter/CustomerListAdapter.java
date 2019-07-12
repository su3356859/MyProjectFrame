package com.demo.android.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by luowei on 2017/11/13.
 */

public class CustomerListAdapter extends CommonAdapter {
    public CustomerListAdapter(Context context, List data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, Object item) {

    }
}
