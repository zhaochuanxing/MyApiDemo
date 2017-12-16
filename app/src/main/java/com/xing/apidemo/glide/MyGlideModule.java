package com.xing.apidemo.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by zhao on 17-11-16.
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
//        GlideBuilder glideBuilder = new GlideBuilder(context);
        glideBuilder.setMemoryCache(new LruResourceCache(1024 * 1024 * 3));
        glideBuilder.setBitmapPool(new LruBitmapPool(1024 * 1024 * 3));
        int diskCacheSize = 1024 * 1024 * 30;
        glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSize));
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        Log.i("zcx","apply");
//        if(!Glide.isSetup()){
//            Glide.setup(glideBuilder);
//        }
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class,new OkHttpGlideUrlLoader.Factory());
        Log.i("zcx","register");
    }
}
