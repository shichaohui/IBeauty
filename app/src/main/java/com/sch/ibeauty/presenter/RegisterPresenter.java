package com.sch.ibeauty.presenter;

import android.content.Context;

import com.sch.ibeauty.R;
import com.sch.ibeauty.entity.LoginRegisterResult;
import com.sch.ibeauty.model.RegisterModel;
import com.sch.ibeauty.model.impl.RegisterModelImpl;
import com.sch.ibeauty.util.ToastUtil;
import com.sch.ibeauty.view.RegisterView;

import java.util.regex.Pattern;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 注册的Presenter
 */
public class RegisterPresenter extends BasePresenter {

    private RegisterView registerView;
    private RegisterModel registerModel;

    /**
     * 实例化Presenter
     *
     * @param context      上下文
     * @param registerView View
     */
    public RegisterPresenter(Context context, RegisterView registerView) {
        super(context);
        this.registerView = registerView;
        this.registerModel = new RegisterModelImpl();
    }

    /**
     * 注册
     */
    public void register() {
        String email = registerView.getEmail();
        String account = registerView.getAccount();
        String password = registerView.getPassword();
        if (!verifyData(email, account, password)) {
            return;
        }
        registerModel.register(email, account, password).subscribe(
                // 成功
                response -> registerSuccessed(account, password, response),
                // 失败
                throwable -> ToastUtil.toastShort(context, throwable)
        );
    }

    // 注册成功
    private void registerSuccessed(String account, String password, LoginRegisterResult result) {
        if (result.isStatus()) {
            registerView.registerSuccessed(account, password);
        } else {
            ToastUtil.toastShort(context, R.string.register_failed);
        }
    }

    // 验证数据
    private boolean verifyData(String email, String account, String password) {
        Pattern patternEmail = Pattern.compile("([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,5})+");
        if (!patternEmail.matcher(email).matches()) {
            ToastUtil.toastLong(context, R.string.email_format_err);
            return false;
        }
        // 匹配6-16位的数字字母下划线的组合
        Pattern patternAcc = Pattern.compile("[\\w]{6,16}");
        if (!patternAcc.matcher(account).matches()) {
            ToastUtil.toastLong(context, R.string.account_format_err);
            return false;
        }
        if (password.length() < 6 || password.length() > 16) {
            ToastUtil.toastLong(context, R.string.password_format_err);
            return false;
        }
        return true;
    }

}
