package com.demo.android.widget.bottompopfragmentmenu;

import android.view.View;

/**
 * Created by guorui.he on 2016/6/20.
 */
public abstract class MenuItemOnClickListener implements View.OnClickListener {

    public MenuItemOnClickListener(BottomMenuFragment _bottomMenuFragment, MenuItem _menuItem) {
        this.bottomMenuFragment = _bottomMenuFragment;
        this.menuItem = _menuItem;
    }

    public BottomMenuFragment getBottomMenuFragment() {
        return bottomMenuFragment;
    }

    public void setBottomMenuFragment(BottomMenuFragment bottomMenuFragment) {
        this.bottomMenuFragment = bottomMenuFragment;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    private BottomMenuFragment bottomMenuFragment;
    private MenuItem menuItem;

    @Override
    public void onClick(View v) {

        if (bottomMenuFragment != null && bottomMenuFragment.isVisible()) {
            bottomMenuFragment.dismiss();
        }

        this.onClickMenuItem(v, this.menuItem, this.menuItem.getPosition());
    }

    public abstract void onClickMenuItem(View v, MenuItem menuItem, int position);
}
