package com.sch.ibeauty.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sch.ibeauty.R;
import com.sch.ibeauty.entity.PictureResponse;
import com.sch.ibeauty.util.DisplayUtil;
import com.sch.ibeauty.util.ImageLoaderHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shichaohui on 16/4/16.
 *
 * 图片列表适配器
 */
public class PictureAdapter extends RecyclerViewPager.Adapter<PictureAdapter.ViewHolder> {

    private List<PictureResponse.Picture> pictureList;
    private LayoutInflater inflater;

    private int imageWidth = DisplayUtil.SCREEN_WIDTH_PX;
    private int imageHeight = DisplayUtil.SCREEN_HEIGHT_PX;

    public PictureAdapter(Context context, List<PictureResponse.Picture> pictureList) {
        inflater = LayoutInflater.from(context);
        this.pictureList = pictureList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_picture, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageSize imageSize = new ImageSize(imageWidth, imageHeight);
        ImageLoaderHelper.getImgLoader().loadImage(pictureList.get(position).getSrc(), imageSize,
                ImageLoaderHelper.createImageOptions(R.mipmap.ic_launcher), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        holder.imgPicture.setImageBitmap(loadedImage);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_picture)
        ImageView imgPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
