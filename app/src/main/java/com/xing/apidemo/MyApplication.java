package com.xing.apidemo;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.database.SqliteDatabaseDriver;
import com.xing.apidemo.greendao.DaoMaster;
import com.xing.apidemo.greendao.DaoSession;

/**
 * Created by zhao on 17-11-16.
 */

public class MyApplication extends Application {
    private static MyApplication mContext;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
        initGlide(this);
        initGreenDao();
        Stetho.initializeWithDefaults(this);
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"demo.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private static void setApplication(MyApplication context){
        mContext = context;
    }

    public static MyApplication getApplication(){
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
