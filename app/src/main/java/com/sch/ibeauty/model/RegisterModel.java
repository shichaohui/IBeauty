package com.sch.ibeauty.model;

import com.sch.ibeauty.entity.LoginRegisterResult;

import rx.Observable;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 注册的model接口
 */
public interface RegisterModel {

    /**
     * 注册
     *
     * @param email    邮箱
     * @param account  账号
     * @param password 密码
     * @return
     */
    Observable<LoginRegisterResult> register(String email, String account, String password);

}
