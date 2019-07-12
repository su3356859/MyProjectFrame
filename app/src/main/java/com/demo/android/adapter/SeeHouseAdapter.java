package com.demo.android.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by luowei on 2017/11/14.
 */

public class SeeHouseAdapter extends CommonAdapter {


    public SeeHouseAdapter(Context context, List data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public int getCount() {
        if (isShowOnlyOne) {
            return 1;
        } else {
            return super.getCount();
        }
    }

    private boolean isShowOnlyOne;

    @Override

    public void convert(int position, ViewHolder helper, Object item) {

    }

    public void isShowOnlyOne(boolean isShowOnlyOne) {
        this.isShowOnlyOne = isShowOnlyOne;
    }
}
