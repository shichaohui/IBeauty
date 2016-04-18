/**
 * Copyright (C) 2014-2015 The MeiRiQ All Right Reserve.
 */
package com.sch.ibeauty.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Create by shichaohui on 15.11.12
 * <p/>
 * 各种单位的尺寸转换.
 */
public class DisplayUtil {
    /**
     * 屏幕宽度 px
     */
    public static int SCREEN_WIDTH_PX;
    /**
     * 屏幕高度px
     */
    public static int SCREEN_HEIGHT_PX;
    /**
     * 屏幕密度
     */
    public static float SCREEN_DENSITY;
    /**
     * 屏幕宽度 dp
     */
    public static int SCREEN_WIDTH_DP;
    /**
     * 屏幕高度dp
     */
    public static int SCREEN_HEIGHT_DP;

    private static boolean sInitialed;

    public static void init(Context context) {
        if (sInitialed || context == null) {
            return;
        }
        sInitialed = true;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        SCREEN_WIDTH_PX = dm.widthPixels;
        SCREEN_HEIGHT_PX = dm.heightPixels;
        SCREEN_DENSITY = dm.density;
        SCREEN_WIDTH_DP = (int) (SCREEN_WIDTH_PX / dm.density);
        SCREEN_HEIGHT_DP = (int) (SCREEN_HEIGHT_PX / dm.density);
    }

    /**
     * dp转换为px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return (int) (dp * SCREEN_DENSITY + 0.5f);
    }

    /**
     * px转换为dp
     *
     * @param px
     * @return
     */
    public static float px2dp(int px) {
        return px / SCREEN_DENSITY + 0.5f;
    }

    /**
     * @param designedDp
     * @return
     */
    public static int designedDP2px(float designedDp) {
        if (SCREEN_WIDTH_DP != 320) {
            designedDp = designedDp * SCREEN_WIDTH_DP / 320f;
        }
        return dp2px(designedDp);
    }

}
