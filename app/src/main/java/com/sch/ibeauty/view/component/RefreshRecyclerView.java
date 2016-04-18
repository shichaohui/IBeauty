package com.sch.ibeauty.view.component;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sch.ibeauty.R;
import com.sch.ibeauty.view.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.LayoutManager;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by shichaohui on 16/1/22.
 * <p>
 * 可刷新的RecycleView, 也可分页
 */
public class RefreshRecyclerView extends SwipeRefreshLayout {

    /**
     * 加载更多
     */
    public interface OnLoadMoreListener {
        /**
         * 加载更多
         */
        void onLoadMore();
    }

    private static RefreshRecyclerView refreshView;
    private LoadMoreRecyclerView mRecycleView;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean loadMoreEnable = true;
    private View header, footer; // 头部和脚部

    private ImageLoader imageLoader;

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        refreshView = this;
        mRecycleView = new LoadMoreRecyclerView(context);
        addView(mRecycleView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        // 设置刷新动画的颜色
        setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
    }

    public LoadMoreRecyclerView getRecycleView() {
        return mRecycleView;
    }

    /**
     * 绑定ImageLoader, 滚动时不加载图片
     */
    public void bindImageLoder(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    public void setAdapter(RefreshAdapter adapter) {
        mRecycleView.setAdapter(adapter);
    }

    /**
     * @return 当前正在使用的适配器
     */
    public RefreshAdapter getAdapter() {
        return (RefreshAdapter) mRecycleView.getAdapter();
    }

    /**
     * 设置布局管理器
     *
     * @param manager 布局管理器
     */
    public void setLayoutManager(LayoutManager manager) {
        if (manager instanceof GridLayoutManager) {
            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getAdapter().isFooter(position) || getAdapter().isHeader(position) ?
                            ((GridLayoutManager) manager).getSpanCount() : 1;
                }
            });
        }
        mRecycleView.setLayoutManager(manager);
        initDefaultFooter();
    }

    /**
     * @return 布局管理器
     */
    public LayoutManager getLayoutManager() {
        return mRecycleView.getLayoutManager();
    }

    /**
     * 添加分割线
     *
     * @param decor 分割线
     */
    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecycleView.addItemDecoration(decor);
    }

    /**
     * 设置是否可以加载更多, 默认true
     */
    public void setLoadMoreEnable(boolean enable) {
        loadMoreEnable = enable;
    }

    /**
     * @return 是否可以加载更多
     */
    public boolean getLoadMoreEnable() {
        return loadMoreEnable;
    }

    /**
     * 设置加载更多监听
     *
     * @param listener {@link RefreshRecyclerView.OnLoadMoreListener}
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    /**
     * 初始化默认的footer
     */
    private void initDefaultFooter() {
        // 默认的footer
        setFooter(R.layout.load_more);
    }

    /**
     * 添加头部
     *
     * @param header 作为头部的布局
     */
    public void setHeader(View header) {
        this.header = header;
    }

    /**
     * 添加头部
     *
     * @param resId 作为头部的布局资源id
     */
    public void setHeader(int resId) {
        this.header = LayoutInflater.from(getContext()).inflate(resId, mRecycleView, false);
    }

    /**
     * @return 头部视图
     */
    public View getHeader() {
        return header;
    }

    /**
     * 添加脚部
     *
     * @param footer 作为脚部的布局
     */
    public void setFooter(View footer) {
        this.footer = footer;
    }

    /**
     * 添加头部
     *
     * @param resId 作为脚部的布局资源id
     */
    public void setFooter(int resId) {
        this.footer = LayoutInflater.from(getContext()).inflate(resId, mRecycleView, false);
    }

    /**
     * @return 脚部视图
     */
    public View getFooter() {
        return footer;
    }

    public int getContentScrollX(){
        return mRecycleView.scrollX;
    }

    public int getContentScrollY(){
        return mRecycleView.scrollY;
    }

    public void scrollContentTo(int x, int y) {
        mRecycleView.scrollTo(x, y);
    }

    /**
     * @return 获取最后一个可见视图的位置
     */
    public int findLastVisibleItemPosition() {
        LayoutManager manager = mRecycleView.getLayoutManager();
        // 获取最后一个正在显示的View
        if (manager instanceof GridLayoutManager) {
            return ((GridLayoutManager) manager).findLastVisibleItemPosition();
        } else if (manager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) manager).findLastVisibleItemPosition();
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int[] into = new int[((StaggeredGridLayoutManager) manager).getSpanCount()];
            ((StaggeredGridLayoutManager) manager).findLastVisibleItemPositions(into);
            return findMax(into);
        }
        return -1;
    }

    /**
     * 设置子视图充满一行
     *
     * @param view 子视图
     */
    private void setFullSpan(View view) {
        LayoutManager manager = getLayoutManager();
        // 根据布局设置参数, 使"加载更多"的布局充满一行
        if (manager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params = new StaggeredGridLayoutManager.LayoutParams(
                    StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT, StaggeredGridLayoutManager.LayoutParams.WRAP_CONTENT);
            params.setFullSpan(true);
            view.setLayoutParams(params);
        }
    }

    /**
     * 可分页加载更多的RecyclerView
     */
    private class LoadMoreRecyclerView extends RecyclerView {

        public LoadMoreRecyclerView(Context context) {
            super(context);
            post(() -> {
                int index = findLastVisibleItemPosition();
                if (index < 0) {
                    return;
                }
                if (getBottom() >= getChildAt(index).getBottom()) {
                    // 最后一条正在显示的子视图在RecyclerView的上面, 说明子视图未充满RecyclerView
                    setLoadMoreEnable(false); // 未充满则不能加载更多
                    getAdapter().notifyDataSetChanged();
                }
            });
        }

        int scrollX, scrollY;

        @Override
        public void scrollTo(int x, int y) {
            scrollBy(x - scrollX, y - scrollY);
            scrollX = x;
            scrollY = y;
        }

        @Override
        public void onScrolled(int dx, int dy) {
            super.onScrolled(dx, dy);
            scrollX += dx;
            scrollY += dy;
        }

        @Override
        public void onScrollStateChanged(int state) {
            super.onScrollStateChanged(state);
            switch (state) {
                // 停止滚动
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    if (imageLoader != null) {
                        imageLoader.resume();
                    }
                    if (loadMoreEnable && refreshView.mOnLoadMoreListener != null && // 可以加载更多, 且有加载监听
                            findLastVisibleItemPosition() == getLayoutManager().getItemCount() - 1) { // 滚动到了最后一个子视图
                        refreshView.mOnLoadMoreListener.onLoadMore(); // 执行加载更多
                    }
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    if (imageLoader != null) {
                        imageLoader.pause();
                    }
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 刷新和加载的适配器
     *
     * @param <VH> ViewHolder的子类
     */
    public static abstract class RefreshAdapter<VH extends ViewHolder, T> extends RecyclerView.Adapter {

        // 内容类型
        protected final int TYPE_CONTENT = 1;
        // 底部加载更多
        private final int TYPE_FOOTER = TYPE_CONTENT + 1;
        // 头部
        private final int TYPE_HEADER = TYPE_CONTENT + 2;

        private List<Integer> viewTypes; // 子视图类型
        private List<T> datas;

        protected LayoutInflater inflater;

        protected OnItemClickListener onItemClickListener;

        /**
         * 创建适配器
         *
         * @param list 要显示的数据列表
         */
        public RefreshAdapter(Context context, List<T> list) {
            datas = list;
            viewTypes = new ArrayList<>();
            setItemTypes(viewTypes);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_CONTENT || viewTypes.contains(viewType)) {
                return onCreateHolder(parent, viewType);
            } else if (viewType == TYPE_HEADER) {
                refreshView.setFullSpan(refreshView.header);
                return new ViewHolder(refreshView.header) {
                };
            } else if (viewType == TYPE_FOOTER) {
                refreshView.setFullSpan(refreshView.footer);
                return new ViewHolder(refreshView.footer) {
                };
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int viewType = holder.getItemViewType();
            if (viewType == TYPE_CONTENT || viewTypes.contains(viewType)) {
                int p = refreshView.header != null ? position - 1 : position;
                onBindViewHolder((VH) holder, getItem(p), p);
            }
        }

        /**
         * 创建ViewHolder, 用来代替onCreateViewHolder()方法, 用法还是一样的
         *
         * @param parent   父控件
         * @param viewType 类型
         * @return ViewHolder的子类实例
         */
        public abstract VH onCreateHolder(ViewGroup parent, int viewType);

        /**
         * 给ViewHolder绑定数据, 用来代替onBindViewHolder(), 用法一样
         *
         * @param holder   ViewHolder的子类实例
         * @param position 位置
         */
        public abstract void onBindViewHolder(VH holder, T object, int position);

        @Override
        public int getItemCount() {
            int count = datas.size();
            if (refreshView.loadMoreEnable && refreshView.footer != null) count += 1;
            if (refreshView.header != null) count += 1;
            return count;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0 && refreshView.header != null) {
                return TYPE_HEADER;
            } else if (refreshView.loadMoreEnable &&
                    refreshView.footer != null && position == getItemCount() - 1) {
                return TYPE_FOOTER;
            } else {
                int viewType = getItemType(refreshView.header != null ? position - 1 : position);
                return viewType == -1 ? TYPE_CONTENT : viewType;
            }
        }

        /**
         * 获取Item
         *
         * @param position 位置
         */
        public T getItem(int position) {
            return datas.get(position);
        }

        /**
         * 自定义获取子视图类型的方法
         *
         * @param position 位置
         * @return 类型
         */
        public int getItemType(int position) {
            return -1;
        }

        /**
         * 设置子视图类型, 如果有新的子视图类型, 直接往参数viewTypes中添加即可, 每个类型的值都要>3, 且不能重复
         *
         * @param viewTypes 子视图类型列表
         */
        public void setItemTypes(List<Integer> viewTypes) {
        }

        /**
         * 设置点击事件
         * @param onItemClickListener 点击事件
         */
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        /**
         * 获取点击事件
         */
        public OnItemClickListener getOnItemClickListener() {
            return onItemClickListener;
        }

        /**
         * @param position 位置
         * @return 是否为脚部布局
         */
        public boolean isFooter(int position) {
            return TYPE_FOOTER == getItemViewType(position);
        }

        /**
         * @param position 位置
         * @return 是否为头部布局
         */
        public boolean isHeader(int position) {
            return TYPE_HEADER == getItemViewType(position);
        }

        /**
         * 添加数据
         *
         * @param list 待添加数据
         */
        public void insert(List<T> list) {
            datas.addAll(list);
            notifyDataSetChanged();
        }

        /**
         * 清理数据
         */
        public void clear() {
            datas.clear();
            notifyDataSetChanged();
        }

    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}