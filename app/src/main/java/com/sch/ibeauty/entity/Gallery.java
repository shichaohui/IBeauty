package com.sch.ibeauty.entity;

import com.sch.ibeauty.Constants;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 图库
 */
public class Gallery {

    private int id;
    private int galleryclass; // 图片分类
    private String title; // 标题
    private String img; // 图库封面
    private int count; // 访问数
    private int rcount; // 回复数
    private int fcount; // 收藏数
    private int size; // 图片多少张
    private long time; // 发布时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return String.format(Constants.getLocalBaseUrlForImage(), img);
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
