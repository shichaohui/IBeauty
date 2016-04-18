package com.sch.ibeauty.view;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * 登录的View接口
 */
public interface LoginView {

    /**
     * 注册按钮被点击的事件
     */
    void registerClick();

    /**
     * 登录按钮被点击的事件
     */
    void loginClick();

    /**
     * 返回账号或者邮箱
     */
    String getAccountOrEmail();

    /**
     * 设置账号或者邮箱
     */
    void setAccountOrEmail(String accountOrEmail);

    /**
     * 返回密码
     */
    String getPassword();

    /**
     * 设置密码
     */
    void setPassword(String password);

    /**
     * 开始登录
     */
    void loginStart();

    /**
     * 登录成功
     */
    void loginSuccessed();

    /**
     * 登录失败
     *
     * @param err 错误信息
     */
    void loginFailed(String err);

}
