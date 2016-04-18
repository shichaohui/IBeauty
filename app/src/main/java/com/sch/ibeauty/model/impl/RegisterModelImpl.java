package com.sch.ibeauty.model.impl;

import com.sch.ibeauty.Constants;
import com.sch.ibeauty.entity.LoginRegisterResult;
import com.sch.ibeauty.repository.ApiHelper;
import com.sch.ibeauty.model.RegisterModel;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 注册的model
 */
public class RegisterModelImpl implements RegisterModel {

    @Override
    public Observable<LoginRegisterResult> register(String email, String account, String password) {
        return ApiHelper.getApis().register(Constants.getClientId(), Constants.getClientSecret(), email, account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
