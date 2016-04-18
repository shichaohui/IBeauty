package com.sch.ibeauty.model;

import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.entity.Galleryclass;
import com.sch.ibeauty.entity.JsonResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 主页的model接口
 */
public interface MainModel {

    /**
     * 返回图片分类数据
     */
    Observable<JsonResponse<List<Galleryclass>>> loadGalleryclass();

    /**
     * 获取图片列表
     *
     * @param page 页码
     * @param rows 每页数据量
     * @param id   分类ID
     */
    Observable<JsonResponse<List<Gallery>>> loadGallery(int page, int rows, int id);

}
