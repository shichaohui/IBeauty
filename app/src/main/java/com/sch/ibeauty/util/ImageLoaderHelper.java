package com.sch.ibeauty.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by shichaohui on 15/9/10.
 * <br/>
 * ImageLoader辅助类.
 */
public class ImageLoaderHelper {

    private static class SingletonImageLoader {
        public static ImageLoader imageLoader = ImageLoader.getInstance();
    }

    /**
     * @return ImageLoader实例
     */
    public static ImageLoader getImgLoader() {
        return SingletonImageLoader.imageLoader;
    }

    /**
     * 创建DisplayImageOptions实例
     *
     * @param defaultImageResId 默认图片的资源id
     * @return DisplayImageOptions实例
     */
    public static DisplayImageOptions createImageOptions(int defaultImageResId) {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImageResId) // 设置图片在下载期间显示的图片
                .showImageOnFail(defaultImageResId) // 设置图片加载/解码过程中错误时候显示的图片
                .showImageForEmptyUri(defaultImageResId) // 设置图片Uri为空或是错误的时候显示的图片
                .cacheInMemory(true) // 设置下载的图片缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
                .imageScaleType(ImageScaleType.EXACTLY) // 按比例缩放到目标大小
                .build(); // 构建
    }

    /**
     * 初始化ImageLoader
     *
     * @param context 上下文
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

}