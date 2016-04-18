package com.sch.ibeauty.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.sch.ibeauty.MyApplication;
import com.sch.ibeauty.R;
import com.sch.ibeauty.util.StatusBarCompat;
import com.sch.ibeauty.view.BaseView;

import butterknife.ButterKnife;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected MyApplication app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        ButterKnife.bind(this);

        app = (MyApplication) getApplicationContext();

        init();

        initTitle();

        setListenerForView();

        StatusBarCompat.compat(this, app.getResources().getColor(R.color.colorPrimaryDark));

    }

    @Override
    public void initTitle() {

        String title = getTitleText();
        if (TextUtils.isEmpty(title)) {
            return;
        }

        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);

        setSupportActionBar(toolbar);

        setTitle(title);

        if (hasNavigationIcon()) {
            toolbar.setNavigationIcon(getNavigationIcon());
            toolbar.setNavigationOnClickListener(getNavigationOnClickListener());
        }

    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public boolean hasNavigationIcon() {
        return true;
    }

    @Override
    public int getNavigationIcon() {
        return R.mipmap.btn_back;
    }

    @Override
    public View.OnClickListener getNavigationOnClickListener() {
        return v -> finish();
    }

    @Override
    public void setListenerForView() {
    }

}
