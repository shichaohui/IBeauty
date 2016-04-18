package com.sch.ibeauty.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * 应用程序工具类
 */
public class AppUtil {

    /**
     * 返回当前应用的当前版本号
     */
    public static String getVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format("v%s", info != null ? info.versionName : "未知");
    }

}
