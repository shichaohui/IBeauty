package com.sch.ibeauty.model.impl;

import com.sch.ibeauty.entity.PictureResponse;
import com.sch.ibeauty.model.ShowPictureModel;
import com.sch.ibeauty.repository.ApiHelper;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shichaohui on 16/4/16.
 * <p>
 * 显示图片的model
 */
public class ShowPictureModelImpl implements ShowPictureModel {

    @Override
    public Observable<PictureResponse> loadPicture(int id) {
        return ApiHelper.getApis().loadPicture(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
