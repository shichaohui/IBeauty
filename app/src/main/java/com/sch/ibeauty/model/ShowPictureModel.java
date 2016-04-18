package com.sch.ibeauty.model;

import com.sch.ibeauty.entity.PictureResponse;

import rx.Observable;

/**
 * Created by shichaohui on 16/4/16.
 * <p>
 * 显示图片的model接口
 */
public interface ShowPictureModel {

    /**
     * 获取图片列表
     *
     * @param id 图库ID
     */
    Observable<PictureResponse> loadPicture(int id);

}
