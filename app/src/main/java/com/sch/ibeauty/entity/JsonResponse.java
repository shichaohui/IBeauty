package com.sch.ibeauty.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shichaohui on 16/4/16.
 */
public class JsonResponse<T> {

    @SerializedName("status") public boolean status;
    @SerializedName("msg") public String msg;
    @SerializedName("tngou") public T response;

}
