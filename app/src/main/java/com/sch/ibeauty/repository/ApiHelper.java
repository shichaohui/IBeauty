package com.sch.ibeauty.repository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * ApiHelper
 */
public class ApiHelper {

    private static final String API_BASE_URL = "http://www.tngou.net/";

    private static class ApiFactory {

        public static ApiService apiService = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
                .build()
                .create(ApiService.class);
    }

    /**
     * 返回Api接口
     */
    public static ApiService getApis() {
        return ApiFactory.apiService;
    }

}
