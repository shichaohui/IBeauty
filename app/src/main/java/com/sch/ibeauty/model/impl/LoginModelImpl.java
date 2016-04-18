package com.sch.ibeauty.model.impl;

import com.sch.ibeauty.Constants;
import com.sch.ibeauty.entity.LoginRegisterResult;
import com.sch.ibeauty.repository.ApiHelper;
import com.sch.ibeauty.model.LoginModel;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 注册的model
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public Observable<LoginRegisterResult> login(String accountOrEmail, String password) {
        return ApiHelper.getApis().login(Constants.getClientId(), Constants.getClientSecret(), accountOrEmail, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
