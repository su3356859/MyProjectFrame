package com.demo.android.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.android.R;
import com.demo.android.base.MvpActivity;
import com.demo.android.present.LoginPresent;
import com.demo.android.utils.MyAppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luowei on 2017/11/8.
 */

public class LoginActivity extends MvpActivity<LoginPresent> implements View.OnClickListener {


    @Bind(R.id.tv_register)
    TextView mTvRegister;
    @Bind(R.id.activity_top_bar)
    RelativeLayout mActivityTopBar;
//    @Bind(R.id.iv_logo)
//    ImageView mIvLogo;
    @Bind(R.id.iv_login_account)
    ImageView mIvLoginAccount;
    @Bind(R.id.et_mobile)
    EditText mEtMobile;
    @Bind(R.id.cleanNum)
    ImageView mCleanNum;
    @Bind(R.id.iv_login_pwd)
    ImageView mIvLoginPwd;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.cleanPwd)
    ImageView mCleanPwd;
    @Bind(R.id.ll_login)
    LinearLayout mLlLogin;
//    @Bind(R.id.loginType)
//    TextView mLoginType;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    private String[] arr = {"经纪人", "店长", "区经"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    protected void initLinstener() {
        mBtnLogin.setOnClickListener(this);
        mCleanNum.setOnClickListener(this);
        mCleanPwd.setOnClickListener(this);
//        mLoginType.setOnClickListener(this);
        mEtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mCleanNum.setVisibility(View.GONE);
                } else {
                    mCleanNum.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBtnLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mCleanPwd.setVisibility(View.GONE);
                } else {
                    mCleanPwd.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://登录
                String userName = mEtMobile.getText().toString().trim();//帐号
                String passWord = mEtPassword.getText().toString().trim();//密码
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
                    showMessage("请输入用户名/密码!");
                    return;
                }
//                mPresenter.login(userName, passWord);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
            case R.id.cleanNum://清除帐号
                mEtMobile.setText("");
                break;
            case R.id.cleanPwd://清除密码
                mEtPassword.setText("");
                break;
//            case R.id.loginType://选择
//                showSelectPopWindow();
//                break;
        }
    }

    private void showSelectPopWindow() {
//        MyAppUtils.showSelectPopWindow(this, arr, new MyAppUtils.OnSelectLinstener() {
//            @Override
//            public void onSelect(int position) {
////                mLoginType.setText(arr[position]);
//            }
//        });
    }
}
