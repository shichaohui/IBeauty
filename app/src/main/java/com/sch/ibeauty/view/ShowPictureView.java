package com.sch.ibeauty.view;

import com.sch.ibeauty.entity.PictureResponse;

import java.util.List;

/**
 * Created by shichaohui on 16/4/16.
 * <p>
 * 显示图片的view接口
 */
public interface ShowPictureView {

    /**
     * 显示图片
     *
     * @param pictureList 图片列表
     */
    void showPicture(List<PictureResponse.Picture> pictureList);

}
