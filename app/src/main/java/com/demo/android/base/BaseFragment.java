package com.demo.android.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.android.R;
import com.demo.android.app.AppContext;
import com.demo.android.event.EventAction;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Fragment基类
 * <p/>
 * Created by jinxh on 15/11/19.
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {

    protected View mRootView;
    private CompositeSubscription mCompositeSubscription;
    protected int mStatusBarHeight;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isHiddenStatusBar()) {
            initTopBar(mRootView);
        }
        registEvent();
        initView();
        initLinstener();
        initData();
    }

    protected abstract void initLinstener();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        unregisterEvent();
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 绑定eventbus
     */
    public void registEvent() {
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注销eventbus
     */
    public void unregisterEvent() {
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接受Eventbus消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(EventAction action) {
        if (action == null || action.getEventType() == null) {
            return;
        }
        onEventBusListener(action);
    }

    /**
     * 方便子类继承
     */
    protected void onEventBusListener(EventAction action) {
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void showMessage(int res) {
        Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(CharSequence text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }


    public void showLoading() {
        ((BaseActivity) getActivity()).showLoading();
    }

    public void dismissLoading() {
        ((BaseActivity) getActivity()).dismissLoading();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mRootView.setOnTouchListener(this);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    protected boolean isHiddenStatusBar() {
        return AppContext.HIDDEN_STATUS_BAR;
    }

    private void initTopBar(View view) {
        // 4.4以上设置头部导航高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View topBar = view.findViewById(R.id.fragment_top_bar);
            if (topBar == null) {
                return;
            }
            SystemBarTintManager.SystemBarConfig config = new SystemBarTintManager(getActivity()).getConfig();
            mStatusBarHeight = config.getStatusBarHeight();
            topBar.setPadding(0, mStatusBarHeight, 0, 0);
            int height = topBar.getLayoutParams().height;
            topBar.getLayoutParams().height = mStatusBarHeight + height;
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}