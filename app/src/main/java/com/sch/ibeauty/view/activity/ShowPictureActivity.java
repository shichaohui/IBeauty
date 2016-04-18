package com.sch.ibeauty.view.activity;

import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.sch.ibeauty.R;
import com.sch.ibeauty.entity.PictureResponse;
import com.sch.ibeauty.presenter.ShowPicturePresenter;
import com.sch.ibeauty.view.ShowPictureView;
import com.sch.ibeauty.view.adapter.PictureAdapter;
import com.sch.ibeauty.view.component.PageIndicator;

import java.util.List;

import butterknife.Bind;

/**
 * Created by shichaohui on 16/4/16.
 * <p>
 * 显示图片的页面
 */
public class ShowPictureActivity extends BaseActivity implements ShowPictureView {

    @Bind(R.id.rviewpager_picture)
    RecyclerViewPager pictureViewPager;
    @Bind(R.id.page_indicator)
    PageIndicator pageIndicator;

    private ShowPicturePresenter presenter;

    /**
     * Intent传递数据key(图库ID)
     */
    public static String INTENT_KEY_GALLERY_ID = "gallery_id";

    @Override
    public int getContentView() {
        return R.layout.activity_show_picture;
    }

    @Override
    public void init() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        pictureViewPager.setLayoutManager(layoutManager);
        pictureViewPager.addOnPageChangedListener(
                (oldPage, newPage) -> pageIndicator.setCurrentPage(newPage)
        );

        presenter = new ShowPicturePresenter(this, this);
        presenter.loadPicture(getIntent().getIntExtra(INTENT_KEY_GALLERY_ID, -1));
    }

    @Override
    public void showPicture(List<PictureResponse.Picture> pictureList) {
        pictureViewPager.setAdapter(new PictureAdapter(this, pictureList));

        pageIndicator.init(pictureList.size());
    }

}