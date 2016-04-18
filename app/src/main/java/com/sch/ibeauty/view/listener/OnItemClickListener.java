package com.sch.ibeauty.view.listener;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * RecyclerView的点击事件
 */
public interface OnItemClickListener {

    /**
     * 点击回调方法
     *
     * @param position 位置
     * @param object   该位置的对象
     */
    void onItemClick(int position, Object object);

}
