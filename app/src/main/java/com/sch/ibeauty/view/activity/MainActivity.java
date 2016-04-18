package com.sch.ibeauty.view.activity;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.sch.ibeauty.R;
import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.entity.Galleryclass;
import com.sch.ibeauty.presenter.MainPresenter;
import com.sch.ibeauty.util.ToastUtil;
import com.sch.ibeauty.view.MainView;
import com.sch.ibeauty.view.adapter.GalleryclassAdapter;
import com.sch.ibeauty.view.adapter.GalleryAdapter;
import com.sch.ibeauty.view.component.RefreshRecyclerView;
import com.sch.ibeauty.view.component.ResideLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 首页
 */
public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.reside_layout)
    ResideLayout resideLayout;
    @Bind(R.id.recycler_view_galleryclass)
    RecyclerView galleryclassRecyclerView;
    @Bind(R.id.recycler_view_gallery)
    RefreshRecyclerView galleryRecyclerView;

    private MainPresenter presenter;
    private GalleryclassAdapter galleryclassAdapter;
    private GalleryAdapter galleryAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.app_name);
    }

    @Override
    public int getNavigationIcon() {
        return R.mipmap.btn_menu;
    }

    @Override
    public View.OnClickListener getNavigationOnClickListener() {
        return v -> {
            if (!resideLayout.isOpen()) {
                resideLayout.openPane();
            }
        };
    }

    @Override
    public void init() {
        galleryclassRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        galleryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        galleryRecyclerView.setOnRefreshListener(() -> presenter.refresh());
        galleryRecyclerView.setOnLoadMoreListener(() -> presenter.loadMore());

        presenter = new MainPresenter(this, this);
        presenter.loadGalleryclass();
    }

    @Override
    public void showClassifies(List<Galleryclass> galleryclassList) {
        galleryclassAdapter = new GalleryclassAdapter(this, galleryclassList);
        galleryclassAdapter.setOnItemClickListener(
                (position, object) -> presenter.toggleGalleryclass(((Galleryclass) object).getId()));
        galleryclassRecyclerView.setAdapter(galleryclassAdapter);
    }

    @Override
    public void showGalleries(Point coordinate, List<Gallery> galleryList) {

        galleryRecyclerView.setRefreshing(false);

        if (galleryList.size() == 0) {
            galleryRecyclerView.setLoadMoreEnable(false);
            ToastUtil.toastShort(this, R.string.no_more_pictures);
        } else {
            galleryRecyclerView.setLoadMoreEnable(true);
        }

        if (galleryAdapter == null) {
            galleryAdapter = createGalleryAdapter(galleryList);
            galleryRecyclerView.setAdapter(galleryAdapter);
        } else {
            galleryAdapter.insert(galleryList);
        }
        galleryRecyclerView.scrollContentTo(coordinate.x, coordinate.y);
    }

    // 创建图库适配器
    private GalleryAdapter createGalleryAdapter(List<Gallery> galleryList) {

        GalleryAdapter adapter = new GalleryAdapter(this, galleryList);
        adapter.setOnItemClickListener(
                (position, object) -> presenter.showPicture(((Gallery) object).getId()));

        return adapter;
    }

    @Override
    public void clearGallerifies() {
        galleryAdapter.clear();
    }

    @Override
    public Point getGalleriesCoordinate() {
        return new Point(galleryRecyclerView.getContentScrollX(), galleryRecyclerView.getContentScrollY());
    }

    @OnClick(R.id.btn_logout)
    public void logoutClick() {
        presenter.logout();
    }

    @Override
    public void logout() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private long startTime;

    @Override
    public void onBackPressed() {
        if (resideLayout.isOpen()) {
            resideLayout.closePane();
        } else {
            long time = System.currentTimeMillis();
            if (time - startTime > 2000) {
                ToastUtil.toastShort(this, R.string.Press_again_to_exit_the_app);
                startTime = time;
            } else {
                super.onBackPressed();
            }
        }
    }
}
