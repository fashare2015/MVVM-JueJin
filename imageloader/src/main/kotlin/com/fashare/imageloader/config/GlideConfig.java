package com.fashare.imageloader.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.fashare.net.widget.OkHttpFactory;

import java.io.InputStream;

/**
 * Created by jinliangshan on 17/2/13.
 * Glide 全局配置, 包括缓存策略、Https等。。<br/>
 *
 * <a href="https://github.com/bumptech/glide/wiki/Configuration">文档</a>
 *
 * <a href="https://github.com/bumptech/glide/issues/305>使用 ARGB_8888 防止图片过度压缩时变绿</a>
 */

public class GlideConfig implements GlideModule {
    static int DISK_CACHE_SIZE = 1024 * 1024 * 50;
//    static int MEMORY_CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder
                // 下面三项都是默认的, 不必设置
//                .setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE))
//                .setBitmapPool(new LruBitmapPool(MEMORY_CACHE_SIZE))
                // 默认 rgb565
                .setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
                .setDiskCache(new InternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));
    }

    // https 配置
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpFactory.INSTANCE.getClient()));
    }

//    // https 配置
//    // TODO: registerComponents() 无效, 暂用这个接口代替
//    public static void initGlide(Context context){
//        Glide.get(context).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpFactory.INSTANCE.getClient()));
//    }
}
