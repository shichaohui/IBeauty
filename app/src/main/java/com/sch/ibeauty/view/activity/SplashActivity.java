package com.sch.ibeauty.view.activity;

import android.content.Intent;
import android.widget.TextView;

import com.sch.ibeauty.R;
import com.sch.ibeauty.util.AppUtil;
import com.sch.ibeauty.view.SplashView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;

/**
 * Create by shichaohui on 16/4/13.
 * <p>
 * 欢迎页
 */
public class SplashActivity extends BaseActivity implements SplashView {

    @Bind(R.id.txt_app_version)
    TextView appVersionTxt;

    @Override
    public void showAppVersion() {
        appVersionTxt.setText(String.format(getString(R.string.format_app_version), AppUtil.getVersion(this)));
    }

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {

        Observable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        });

        showAppVersion();

    }

}