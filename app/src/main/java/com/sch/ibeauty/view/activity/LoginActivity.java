package com.sch.ibeauty.view.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;

import com.sch.ibeauty.R;
import com.sch.ibeauty.presenter.LoginPresenter;
import com.sch.ibeauty.view.LoginView;
import com.sch.ibeauty.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Create by shichaohui on 16/4/13.
 * <p>
 * 登录页
 */
public class LoginActivity extends BaseActivity implements LoginView {

    @Bind(R.id.edt_user_account_or_email)
    EditText userAccountOrEmailEdt;
    @Bind(R.id.edt_user_pwd)
    EditText userPwdEdt;
    @Bind(R.id.btn_login)
    Button loginBtn;

    private LoginPresenter mLoginPresenter;
    private ValueAnimator loginAnim;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        mLoginPresenter = new LoginPresenter(this, this);
        mLoginPresenter.autoLogin();
    }

    @Override
    @OnClick(R.id.btn_register)
    public void registerClick() {
        startActivityForResult(new Intent(this, RegisterActivity.class), 0);
    }

    @Override
    @OnClick(R.id.btn_login)
    public void loginClick() {
        mLoginPresenter.login();
    }

    @Override
    public String getAccountOrEmail() {
        return userAccountOrEmailEdt.getText().toString().trim();
    }

    @Override
    public void setAccountOrEmail(String accountOrEmail) {
        userAccountOrEmailEdt.setText(accountOrEmail);
    }

    @Override
    public String getPassword() {
        return userPwdEdt.getText().toString().trim();
    }

    @Override
    public void setPassword(String password) {
        userPwdEdt.setText(password);
    }

    @Override
    public void loginStart() {
        loginBtn.setEnabled(false);

        loginAnim = ValueAnimator.ofInt(1, 6);
        loginAnim.setDuration(2000);
        loginAnim.setInterpolator(new LinearInterpolator());
        loginAnim.setRepeatCount(ValueAnimator.INFINITE);
        loginAnim.addUpdateListener(animation -> {
            int m = (int) animation.getAnimatedValue() % 6;
            StringBuilder buffer = new StringBuilder("正在登录");
            for (int i = 0; i < m; i++) {
                buffer.append(".");
            }
            loginBtn.setText(buffer.toString());
        });
        loginAnim.start();
    }

    @Override
    public void loginSuccessed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String err) {
        if (!TextUtils.isEmpty(err)) {
            ToastUtil.toastShort(this, err);
        }
        loginBtn.setText("登录");
        loginBtn.setEnabled(true);
        loginAnim.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            userAccountOrEmailEdt.setText(data.getStringExtra(RegisterActivity.getKeyUserAccount()));
            userPwdEdt.setText(data.getStringExtra(RegisterActivity.getKeyUserPassword()));
            // 自动登录
            mLoginPresenter.login();
        }
    }

}
