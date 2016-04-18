package com.sch.ibeauty.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.sch.ibeauty.R;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * Toast工具
 */
public class ToastUtil {

    /**
     * 短时间弹出提示信息
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void toastShort(Context context, @StringRes int msg) {
        toastShort(context, context.getApplicationContext().getResources().getString(msg));
    }

    /**
     * 短时间弹出提示信息
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void toastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间弹出异常信息
     *
     * @param context   上下文
     * @param throwable 异常
     */
    public static void toastShort(Context context, Throwable throwable) {
        toastShort(context, TextUtils.isEmpty(throwable.getMessage()) ?
                context.getApplicationContext().getResources().getString(R.string.load_failed) :
                throwable.getMessage());
    }

    /**
     * 短时间弹出提示信息
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void toastLong(Context context, @StringRes int msg) {
        toastLong(context, context.getApplicationContext().getResources().getString(msg));
    }

    /**
     * 长时间弹出提示信息
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void toastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间弹出异常信息
     *
     * @param context   上下文
     * @param throwable 异常
     */
    public static void toastLong(Context context, Throwable throwable) {
        toastLong(context, TextUtils.isEmpty(throwable.getMessage()) ?
                "加载失败" : throwable.getMessage());
    }


}
