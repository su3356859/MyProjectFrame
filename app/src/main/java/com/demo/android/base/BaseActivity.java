package com.demo.android.base;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.demo.android.R;
import com.demo.android.app.AppContext;
import com.demo.android.event.EventAction;
import com.demo.android.widget.LoadingDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 */
public abstract class BaseActivity extends AppCompatActivity {
    private SystemBarTintManager mTintManager;
    private LoadingDialog mLoadingDialog;
    private CompositeSubscription mCompositeSubscription;
    protected Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initStatusBarColor();
        if (isHiddenStatusBar()) {
            initBarTint();
            initTopBar();
        }
        initView();
        initLinstener();
        initData();
        registEvent();
    }

    protected abstract void initLinstener();

    protected void initStatusBarColor() {

    }

    protected boolean isHiddenStatusBar() {
        return AppContext.HIDDEN_STATUS_BAR;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        unregisterEvent();
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void backButtonPressed(View view) {
        finish();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

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

    public void back(View view) {
        finish();
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    protected int statusBarColor = Color.TRANSPARENT;

    private void initBarTint() {
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window = getWindow();
            // 全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(statusBarColor);
                setStatusBarColor(window);
            } else {
                setTranslucentStatus(window);
            }
        }
        setTintResource(getTintResourceId());
    }

    protected void setStatusBarColor(Window window) {

    }

    private void initTopBar() {
        // 4.4以上设置头部导航高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View topBar = findViewById(R.id.activity_top_bar);
            if (topBar == null) {
                return;
            }
            SystemBarTintManager.SystemBarConfig config = new SystemBarTintManager(this).getConfig();
            int statusBarHeight = config.getStatusBarHeight();
            topBar.setPadding(0, statusBarHeight, 0, 0);

            int height = topBar.getLayoutParams().height;
            topBar.getLayoutParams().height = statusBarHeight + height;
        }
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @TargetApi(19)
    private void setTranslucentStatus(Window window) {
        WindowManager.LayoutParams winParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        winParams.flags |= bits;
        window.setAttributes(winParams);
    }

    public void showMessage(int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    protected void setTintResource(int res) {
        mTintManager.setTintResource(res);
    }

    protected int getTintResourceId() {
        return R.color.transparent;
    }
}
