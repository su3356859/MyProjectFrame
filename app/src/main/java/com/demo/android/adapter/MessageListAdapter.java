package com.demo.android.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by luowei on 2017/11/13.
 */

public class MessageListAdapter extends CommonAdapter {
    public MessageListAdapter(Context context, List data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, Object item) {

    }
}
