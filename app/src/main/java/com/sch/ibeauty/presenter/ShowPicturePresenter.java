package com.sch.ibeauty.presenter;

import android.content.Context;

import com.sch.ibeauty.model.ShowPictureModel;
import com.sch.ibeauty.model.impl.ShowPictureModelImpl;
import com.sch.ibeauty.util.ToastUtil;
import com.sch.ibeauty.view.ShowPictureView;

/**
 * Created by shichaohui on 16/4/16.
 * <p>
 * 显示图片的Presenter
 */
public class ShowPicturePresenter extends BasePresenter {

    private ShowPictureView showPictureView;
    private ShowPictureModel showPictureModel;

    /**
     * 实例化Presenter
     *
     * @param context         上下文
     * @param showPictureView View
     */
    public ShowPicturePresenter(Context context, ShowPictureView showPictureView) {
        super(context);
        this.showPictureView = showPictureView;
        this.showPictureModel = new ShowPictureModelImpl();
    }

    /**
     * 加载图片
     *
     * @param id 图库ID
     */
    public void loadPicture(int id) {

        if (id < 0) {
            return;
        }

        showPictureModel.loadPicture(id).subscribe(
                // 成功
                response -> showPictureView.showPicture(response.response),
                // 失败
                throwable -> ToastUtil.toastShort(context, throwable)
        );
    }

}
