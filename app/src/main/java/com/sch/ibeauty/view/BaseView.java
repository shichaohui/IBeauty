package com.sch.ibeauty.view;

import android.view.View;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * 视图相关公用接口
 */
public interface BaseView {

    /**
     * 内容视图的布局文件ID
     */
    int getContentView();

    /**
     * 初始化标题
     */
    void initTitle();

    /**
     * 标题文本
     */
    String getTitleText();

    /**
     * 是否需要返回按钮
     */
    boolean hasNavigationIcon();

    /**
     * 初始化
     */
    void init();

    /**
     * 给View设置监听
     */
    void setListenerForView();

    /**
     * 获得导航图标的ID, 默认返回键图标
     */
    int getNavigationIcon();

    /**
     * 获取导航图标的点击监听, 默认为结束当前Activity
     */
    View.OnClickListener getNavigationOnClickListener();
}
