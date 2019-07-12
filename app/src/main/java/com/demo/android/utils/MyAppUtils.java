package com.demo.android.utils;

import android.app.Activity;
import android.view.View;

import com.demo.android.widget.bottompopfragmentmenu.BottomMenuFragment;
import com.demo.android.widget.bottompopfragmentmenu.MenuItem;
import com.demo.android.widget.bottompopfragmentmenu.MenuItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luowei on 2017/11/9.
 */

public class MyAppUtils {
    public static void showSelectPopWindow(Activity activity, String[] arr,
            final OnSelectLinstener onSelectLinstener) {


        BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        List<MenuItem> menuItemList = new ArrayList<MenuItem>();

        for (int i = 0; i < arr.length; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setText(arr[i]);
            menuItem.setStyle(MenuItem.MenuItemStyle.COMMON);
            menuItem.setPosition(i);
            menuItem.setMenuItemOnClickListener(
                    new MenuItemOnClickListener(bottomMenuFragment, menuItem) {
                        @Override
                        public void onClickMenuItem(View v, MenuItem menuItem, int position) {
                            onSelectLinstener.onSelect(position);
                        }
                    });
            menuItemList.add(menuItem);
        }
        bottomMenuFragment.setMenuItems(menuItemList);
        bottomMenuFragment.show(activity.getFragmentManager(), "BottomMenuFragment");
    }

    public interface OnSelectLinstener {
        void onSelect(int position);
    }
}

