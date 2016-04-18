package com.sch.ibeauty.entity;

import com.google.gson.annotations.SerializedName;
import com.sch.ibeauty.Constants;

import java.util.List;

/**
 * Created by shichaohui on 16/4/16.
 * <p>
 * 图片
 */
public class PictureResponse {

    public boolean status;
    @SerializedName("list")
    public List<Picture> response;

    public class Picture {

        private int id;
        private int gallery; //图片库
        private String src; //图片地址

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGallery() {
            return gallery;
        }

        public void setGallery(int gallery) {
            this.gallery = gallery;
        }

        public String getSrc() {
            return String.format(Constants.getLocalBaseUrlForImage(), src);
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
