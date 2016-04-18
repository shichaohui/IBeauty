package com.sch.ibeauty.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sch.ibeauty.R;
import com.sch.ibeauty.entity.Galleryclass;
import com.sch.ibeauty.view.listener.OnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 分类适配器
 */
public class GalleryclassAdapter extends RecyclerView.Adapter<GalleryclassAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Galleryclass> galleryclassList;
    private OnItemClickListener mOnItemClickListener;
    private int curIndex = 0; // 当前选中

    public GalleryclassAdapter(Context context, List<Galleryclass> galleryclassList) {
        mInflater = LayoutInflater.from(context);
        this.galleryclassList = galleryclassList;
    }

    /**
     * 设置点击事件
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_galleryclass, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.galleryclassBtn.setText(galleryclassList.get(position).getName());
        holder.galleryclassBtn.setEnabled(position != curIndex);
        if (mOnItemClickListener != null) {
            holder.galleryclassBtn.setTag(position);
            holder.galleryclassBtn.setOnClickListener(v -> {
                int p = (int) v.getTag();
                if (p == curIndex) {
                    return;
                }
                mOnItemClickListener.onItemClick(p, galleryclassList.get(p));
                curIndex = p;
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return galleryclassList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.btn_galleryclass)
        Button galleryclassBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
