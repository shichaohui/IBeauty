package com.sch.ibeauty.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;

import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.entity.JsonResponse;
import com.sch.ibeauty.model.MainModel;
import com.sch.ibeauty.model.impl.MainModelImpl;
import com.sch.ibeauty.util.SharedPreferencesUtil;
import com.sch.ibeauty.util.ToastUtil;
import com.sch.ibeauty.view.MainView;
import com.sch.ibeauty.view.activity.ShowPictureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 主页的Presenter
 */
public class MainPresenter extends BasePresenter {

    private final int ROWS = 20; // 每页数据量
    private int curGalleryclassId = -1; // 当前分类ID

    // <id, page>
    private Map<Integer, Integer> idPageMap = new HashMap<>();
    // <id, 图库列表> 备份数据
    private Map<Integer, List<Gallery>> idGalleryMap = new HashMap<>();
    // <id, 坐标>
    private Map<Integer, Point> idGalleryCoordinateMap = new HashMap<>();
    // 当前数据列表, 不直接使用备份数据, 以防被删除
    private List<Gallery> galleryList = new ArrayList<>();

    private MainView mainView;
    private MainModel mainModel;

    /**
     * 实例化Presenter
     *
     * @param context  上下文
     * @param mainView View
     */
    public MainPresenter(Context context, MainView mainView) {
        super(context);
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }

    /**
     * 加载图库分类数据
     */
    public void loadGalleryclass() {
        mainModel.loadGalleryclass().subscribe(
                // 成功
                response -> {
                    mainView.showClassifies(response.response);
                    if (response.response.size() > 0) {
                        loadGallery(1, response.response.get(0).getId());
                    }
                },
                // 失败
                throwable -> ToastUtil.toastShort(context, throwable)
        );
    }

    /**
     * 切换图库分类
     *
     * @param id 分类ID
     */
    public void toggleGalleryclass(int id) {

        idGalleryCoordinateMap.put(this.curGalleryclassId, mainView.getGalleriesCoordinate());

        this.curGalleryclassId = id;

        mainView.clearGallerifies();

        List<Gallery> galleryList = idGalleryMap.get(id);
        if (galleryList == null) {
            loadGallery(1, id);
        } else {
            mainView.showGalleries(idGalleryCoordinateMap.get(id), galleryList);
        }
    }

    /**
     * 获取某一分类下的图库列表
     *
     * @param page 页码
     * @param id   分类ID
     */
    public void loadGallery(int page, int id) {
        idPageMap.put(id, page);
        this.curGalleryclassId = id;

        mainModel.loadGallery(page, ROWS, id).subscribe(
                // 成功
                response -> loadGallerySuccessed(page, id, response),
                // 失败
                throwable -> {
                    ToastUtil.toastShort(context, throwable);
                    idPageMap.put(id, page - 1); // 回滚页码
                }
        );
    }

    // 加载图库成功
    private void loadGallerySuccessed(int page, int id, JsonResponse<List<Gallery>> response) {
        if (page == 1) {
            idGalleryMap.put(id, response.response); // 备份数据
            MainPresenter.this.galleryList.clear(); // 清理当前显示数据
            MainPresenter.this.galleryList.addAll(response.response); // 加入当前显示数据列表
            // 显示数据, 不直接使用备份数据, 以防被删除
            mainView.showGalleries(mainView.getGalleriesCoordinate(), MainPresenter.this.galleryList);
        } else {
            idGalleryMap.get(id).addAll(response.response); // 新数据加入备份
            // 显示数据
            mainView.showGalleries(mainView.getGalleriesCoordinate(), response.response);
        }
    }

    /**
     * 刷新
     */
    public void refresh() {
        loadGallery(1, curGalleryclassId);
    }

    /**
     * 获取更多数据
     */
    public void loadMore() {
        // 更新页码
        int page = idPageMap.get(curGalleryclassId) + 1;
        idPageMap.put(curGalleryclassId, page);
        // 加载数据
        loadGallery(page, curGalleryclassId);
    }

    /**
     * 退出登录
     */
    public void logout() {
        SharedPreferencesUtil.getInstance(context)
                .putBoolean(SharedPreferencesUtil.getKeyAutoLogin(), false);
        mainView.logout();
    }

    /**
     * 显示图片
     * @param id
     */
    public void showPicture(int id) {
        if (id < 0) {
            return;
        }

        Intent intent = new Intent(context, ShowPictureActivity.class);
        intent.putExtra(ShowPictureActivity.INTENT_KEY_GALLERY_ID, id);
        context.startActivity(intent);
    }

}
