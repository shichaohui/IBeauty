package com.sch.ibeauty.model;

import com.sch.ibeauty.entity.LoginRegisterResult;

import rx.Observable;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 登录的model接口
 */
public interface LoginModel {

    /**
     * 登录
     *
     * @param accountOrEmail 账号或邮箱
     * @param password       密码
     * @return
     */
    Observable<LoginRegisterResult> login(String accountOrEmail, String password);

}
