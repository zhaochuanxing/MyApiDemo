package com.xing.apidemo.glide;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by zhao on 17-11-16.
 */

public class OkHttpGlideUrlLoader implements ModelLoader<GlideUrl,InputStream> {

    private OkHttpClient mOkHttpClient;

    public static class Factory implements ModelLoaderFactory<GlideUrl,InputStream>{

        private OkHttpClient okHttpClient;

        public Factory(){

        }

        public Factory(OkHttpClient okHttpClient){
            this.okHttpClient = okHttpClient;
        }

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new OkHttpGlideUrlLoader(getOkHttp());
        }

        private synchronized OkHttpClient getOkHttp(){
            if(okHttpClient==null){
                okHttpClient = new OkHttpClient();
            }
            return okHttpClient;
        }

        @Override
        public void teardown() {

        }
    }

    public OkHttpGlideUrlLoader(OkHttpClient okHttpClient){
        this.mOkHttpClient = okHttpClient;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new OkHttpFetcher(mOkHttpClient,model);
    }
}
