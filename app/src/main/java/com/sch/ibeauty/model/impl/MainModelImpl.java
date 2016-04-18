package com.sch.ibeauty.model.impl;

import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.entity.Galleryclass;
import com.sch.ibeauty.entity.JsonResponse;
import com.sch.ibeauty.model.MainModel;
import com.sch.ibeauty.repository.ApiHelper;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 主页的model
 */
public class MainModelImpl implements MainModel {

    @Override
    public Observable<JsonResponse<List<Galleryclass>>> loadGalleryclass() {
        return ApiHelper.getApis().loadGalleryclass()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<Gallery>>> loadGallery(int page, int rows, int id) {
        return ApiHelper.getApis().loadGallery(page, rows, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
