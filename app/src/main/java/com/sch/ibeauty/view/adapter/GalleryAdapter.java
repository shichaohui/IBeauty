package com.sch.ibeauty.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sch.ibeauty.R;
import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.util.DateUtil;
import com.sch.ibeauty.util.DisplayUtil;
import com.sch.ibeauty.util.ImageLoaderHelper;
import com.sch.ibeauty.view.component.RefreshRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 图库f封面适配器
 */
public class GalleryAdapter extends RefreshRecyclerView.RefreshAdapter<GalleryAdapter.ViewHolder, Gallery> {

    private int imageWidth = DisplayUtil.dp2px(150);
    private int imageHeight = DisplayUtil.dp2px(250);

    public GalleryAdapter(Context context, List<Gallery> galleryList) {
        super(context, galleryList);
    }

    @Override
    public ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(super.inflater.inflate(R.layout.item_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Gallery gallery, int position) {
        ImageSize imageSize = new ImageSize(imageWidth, imageHeight);
        ImageLoaderHelper.getImgLoader().loadImage(gallery.getImg(), imageSize,
                ImageLoaderHelper.createImageOptions(R.mipmap.ic_launcher), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        holder.galleryImg.setImageBitmap(loadedImage);
                        // 取图片主色
                        Palette.Swatch swatch = Palette.from(loadedImage).generate().getMutedSwatch();
                        setHolderViewColors(swatch, holder);
                    }
                });
        holder.titleTxt.setText(gallery.getTitle());
        holder.timeTxt.setText(DateUtil.getShowTime(gallery.getTime()));
        holder.countTxt.setText(String.format("%s张", gallery.getSize()));

        holder.itemView.setOnClickListener(v -> {
            if (getOnItemClickListener() != null) {
                getOnItemClickListener().onItemClick(position, gallery);
            }
        });
    }

    // 设置view的颜色
    private void setHolderViewColors(Palette.Swatch swatch, ViewHolder holder) {
        if (swatch == null) {
            return;
        }
        holder.bottomRLayout.setBackgroundColor(ColorUtils.setAlphaComponent(swatch.getRgb(), 180));
        holder.titleTxt.setTextColor(swatch.getBodyTextColor());
        holder.timeTxt.setTextColor(swatch.getTitleTextColor());
        holder.countTxt.setTextColor(swatch.getTitleTextColor());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_gallery)
        ImageView galleryImg;
        @Bind(R.id.txt_title)
        TextView titleTxt;
        @Bind(R.id.txt_time)
        TextView timeTxt;
        @Bind(R.id.txt_count)
        TextView countTxt;
        @Bind(R.id.rlayout_bottom)
        View bottomRLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
