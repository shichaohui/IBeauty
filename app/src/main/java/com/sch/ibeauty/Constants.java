package com.sch.ibeauty;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * 常量
 */
public class Constants {

    // 天狗云应用ID
    private static String CLIENT_ID = "1166890";

    // 天狗云应用Key
    private static String CLIENT_SECRET = "3a48ec75144b3bf07b3042df05b08553";

    /**
     * 网络图片的基础路径
     */
    private static String LOCAL_BASE_URL_FOR_IMAGE = "http://tnfs.tngou.net/image/%s";

    /**
     * 返回天狗云应用ID
     */
    public static String getClientId() {
        return CLIENT_ID;
    }

    /**
     * 返回天狗云应用Key
     */
    public static String getClientSecret() {
        return CLIENT_SECRET;
    }

    /**
     * 获取网络图片的基础路径
     *
     * @return
     */
    public static String getLocalBaseUrlForImage() {
        return LOCAL_BASE_URL_FOR_IMAGE;
    }
}
