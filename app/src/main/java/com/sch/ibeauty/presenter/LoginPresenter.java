package com.sch.ibeauty.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.sch.ibeauty.entity.LoginRegisterResult;
import com.sch.ibeauty.model.LoginModel;
import com.sch.ibeauty.model.impl.LoginModelImpl;
import com.sch.ibeauty.view.LoginView;
import com.sch.ibeauty.util.SharedPreferencesUtil;
import com.sch.ibeauty.util.ToastUtil;

import java.util.regex.Pattern;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 登录的Presenter
 */
public class LoginPresenter extends BasePresenter {

    private LoginView loginView;
    private LoginModel loginModel;

    /**
     * 实例化Presenter
     *
     * @param context   上下文
     * @param loginView View
     */
    public LoginPresenter(Context context, LoginView loginView) {
        super(context);
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    /**
     * 自动登录
     */
    public void autoLogin() {
        SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance(context);
        loginView.setAccountOrEmail(sp.getUserName());
        loginView.setPassword(sp.getUserPwd());
        if (sp.isAutoLogin()) {
            login();
        }
    }

    /**
     * 登录
     */
    public void login() {
        loginView.loginStart();

        String accountOrEmail = loginView.getAccountOrEmail();
        String password = loginView.getPassword();

        if (!verifyData(accountOrEmail, password)) {
            loginView.loginFailed(null);
            return;
        }

        loginModel.login(loginView.getAccountOrEmail(), loginView.getPassword()).subscribe(
                // 成功
                response -> loginSuccessed(accountOrEmail, password, response),
                // 失败
                this::loginError
        );
    }

    // 登录成功
    private void loginSuccessed(String accountOrEmail, String password, LoginRegisterResult result) {
        if (result.isStatus()) {
            // 保存账号密码以便自动登录
            SharedPreferencesUtil.getInstance(context).saveLoginInfo(true, accountOrEmail, password);
            loginView.loginSuccessed();
        } else {
            loginView.loginFailed(result.getMsg());
        }
    }

    // 登录失败
    private void loginError(Throwable throwable) {
        loginView.loginFailed(TextUtils.isEmpty(throwable.getMessage()) ? "登录失败" : throwable.getMessage());
    }

    // 验证数据
    private boolean verifyData(String accountorEmail, String password) {
        Pattern patternEmail = Pattern.compile("([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,5})+");
        Pattern patternAcc = Pattern.compile("[\\w]{6,16}");
        if (!patternAcc.matcher(accountorEmail).matches() &&
                !patternEmail.matcher(accountorEmail).matches()) {
            ToastUtil.toastLong(context, "账号格式不正确");
            return false;
        }
        if (password.length() < 6 || password.length() > 16) {
            ToastUtil.toastLong(context, "密码格式不正确");
            return false;
        }
        return true;
    }

}
