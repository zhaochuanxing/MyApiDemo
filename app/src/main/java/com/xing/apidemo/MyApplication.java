package com.xing.apidemo;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

/**
 * Created by zhao on 17-11-16.
 */

public class MyApplication extends Application {
    private static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
        initGlide(this);
    }

    private static void setApplication(Application context){
        mContext = context;
    }

    public static Application getApplication(){
        return mContext;
    }

    private void initGlide(Context context) {
        GlideBuilder glideBuilder = new GlideBuilder(context);
        glideBuilder.setMemoryCache(new LruResourceCache(1024 * 1024 * 3));
        glideBuilder.setBitmapPool(new LruBitmapPool(1024 * 1024 * 3));
        int diskCacheSize = 1024 * 1024 * 30;
        glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSize));
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        if(!Glide.isSetup()){
            Glide.setup(glideBuilder);
        }
    }
}
