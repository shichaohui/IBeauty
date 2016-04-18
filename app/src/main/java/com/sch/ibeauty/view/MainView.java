package com.sch.ibeauty.view;

import android.graphics.Point;

import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.entity.Galleryclass;

import java.util.List;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 主页相关接口
 */
public interface MainView {

    /**
     * 显示分类
     *
     * @param galleryclassList 分类列表
     */
    void showClassifies(List<Galleryclass> galleryclassList);

    /**
     * 显示图片列表
     *
     * @param coordinate  列表的坐标
     * @param galleryList 图片列表
     */
    void showGalleries(Point coordinate, List<Gallery> galleryList);

    /**
     * 清理图库
     */
    void clearGallerifies();

    /**
     * 返回分类图库的坐标
     */
    Point getGalleriesCoordinate();

    /**
     * 退出登录
     */
    void logout();

}
