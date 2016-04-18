package com.sch.ibeauty.view;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 注册的View接口
 */
public interface RegisterView {

    /**
     * 返回邮箱
     */
    String getEmail();

    /**
     * 返回账号
     */
    String getAccount();

    /**
     * 返回密码
     */
    String getPassword();

    /**
     * 注册成功
     *
     * @param account  账号
     * @param password 密码
     */
    void registerSuccessed(String account, String password);

}
