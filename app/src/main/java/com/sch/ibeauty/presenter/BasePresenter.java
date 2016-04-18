package com.sch.ibeauty.presenter;

import android.content.Context;

import com.sch.ibeauty.MyApplication;

/**
 * Created by shichaohui on 16/4/16.
 */
public abstract class BasePresenter {

    protected Context context;
    protected MyApplication appContext;

    /**
     * 实例化Presenter
     *
     * @param context 上下文
     */
    public BasePresenter(Context context) {
        this.context = context;
        appContext = (MyApplication) context.getApplicationContext();
    }

}