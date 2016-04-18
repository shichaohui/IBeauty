package com.sch.ibeauty.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/9/21.
 * <br/>
 * 状态栏适配
 */
public class StatusBarCompat {

    private static final int INVALID_VAL = -1;
    // 默认颜色
    private static final int COLOR_DEFAULT = Color.parseColor("#20000000");

    /**
     * 适配状态栏为 Android 5.0 样式
     *
     * @param activity    Activity
     * @param statusColor 颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, int statusColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int color = COLOR_DEFAULT;
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }

    }

    /**
     * 适配状态栏为 Android 5.0 样式
     *
     * @param activity Activity
     */
    public static void compat(Activity activity) {
        compat(activity, INVALID_VAL);
    }


    /**
     * 获取状态栏的高度
     *
     * @param context 上下文
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}