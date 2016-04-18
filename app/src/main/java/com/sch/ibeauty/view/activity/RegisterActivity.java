package com.sch.ibeauty.view.activity;

import android.content.Intent;
import android.widget.EditText;

import com.sch.ibeauty.R;
import com.sch.ibeauty.presenter.RegisterPresenter;
import com.sch.ibeauty.view.RegisterView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Create by shichaohui on 16/4/13.
 * <p>
 * 注册页面
 */
public class RegisterActivity extends BaseActivity implements RegisterView {

    @Bind(R.id.edt_user_account_or_email)
    EditText userAccountEdt;
    @Bind(R.id.edt_email)
    EditText emailEdt;
    @Bind(R.id.edt_user_pwd)
    EditText userPwdEdt;

    private static final String KEY_USER_ACCOUNT = "account";
    private static final String KEY_USER_PASSWORD = "password";

    public static String getKeyUserAccount() {
        return KEY_USER_ACCOUNT;
    }

    public static String getKeyUserPassword() {
        return KEY_USER_PASSWORD;
    }

    private RegisterPresenter mRegisterPresenter;

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void init() {
        mRegisterPresenter = new RegisterPresenter(this, this);
    }

    @Override
    public String getTitleText() {
        return getString(R.string.title_register);
    }

    @OnClick(R.id.btn_register)
    public void registerClick() {
        mRegisterPresenter.register();
    }

    @Override
    public String getEmail() {
        return emailEdt.getText().toString().trim();
    }

    @Override
    public String getAccount() {
        return userAccountEdt.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return userPwdEdt.getText().toString().trim();
    }

    @Override
    public void registerSuccessed(String account, String password) {
        Intent intent = new Intent();
        intent.putExtra(getKeyUserAccount(), account);
        intent.putExtra(getKeyUserPassword(), password);
        setResult(0, intent);
        finish();
    }

}
