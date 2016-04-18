package com.sch.ibeauty;

import android.app.Application;

import com.sch.ibeauty.util.DisplayUtil;
import com.sch.ibeauty.util.ImageLoaderHelper;

/**
 * Created by shichaohui on 16/4/14.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayUtil.init(this);

        ImageLoaderHelper.initImageLoader(this);
    }

}
