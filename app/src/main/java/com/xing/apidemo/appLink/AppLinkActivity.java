package com.xing.apidemo.appLink;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

import java.util.List;

public class AppLinkActivity extends AppCompatActivity {

    private static final String TAG = AppLinkActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_link);

        Button appStoreBtn = (Button)findViewById(R.id.btn_go_appstore);
        appStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.APP_MARKET");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AppLinkActivity.this.startActivity(intent);
            }
        });

        Button wechatBtn = (Button)findViewById(R.id.btn_app_down);
        wechatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse("market://details?id=com.zhihu.android");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppLinkActivity.this.startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        Button webBtn = (Button)findViewById(R.id.btn_web);
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addCategory("android.intent.category.BROWSABLE");
                Uri uri = Uri.parse("http://imtt.dd.qq.com/16891/F062E6419344215C7142B9600A2D85BF.apk?fsname=com.tencent.mobileqq_7.6.0_832.apk&csr=2097&_track_d99957f7=acbd3cb8-7b0b-49e2-a20b-ab4386510687");
                intent.setData(uri);
                AppLinkActivity.this.startActivity(intent);
            }
        });

        final String appPackageName = "com.hisense.hiphone.appstore";
        final String gamePackge = "com.hisense.hiphone.gamecenter";
        Button appCenterBtn = (Button)findViewById(R.id.btn_appcenter);
        appCenterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAppInstalled(appPackageName)){
                    String appName = "com.ss.android.article.news";
                    Uri uri = Uri.parse("market://details?id="+appName+"&isAutoDownload=true");
                    Intent intent = new Intent();
                    intent.setPackage(appPackageName);
                    intent.setData(uri);
                    AppLinkActivity.this.startActivity(intent);
                }
            }
        });

        Button hiMarketBtn = (Button)findViewById(R.id.btn_himarket);
        hiMarketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("himarket://details?id=com.zhihu.android&isAutoDownload=1");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                AppLinkActivity.this.startActivity(intent);
            }
        });
    }

    //标准的方式
    private boolean isAppInstalled(String packageName){
        Log.d(TAG,"isAppExist start "+System.currentTimeMillis());
        PackageManager packageManager = this.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName,0);
            //这个耗时为1ms，与下面有惊人的差距。一个基础方法，带来的影响竟然这么大
            Log.d(TAG,"isAppExist endnd "+System.currentTimeMillis());
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    //另外的一种方式，效率比较低
    //执行速度要慢一点
    private boolean isAppExist(String packageName){
        if(TextUtils.isEmpty(packageName)){
            return false;
        }
        Log.d(TAG,"isAppExist start "+System.currentTimeMillis());
        PackageManager packageManager = AppLinkActivity.this.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for(PackageInfo packageInfo :installedPackages){
            if(packageInfo!=null && packageName.equals(packageInfo.packageName)){
                //耗时接近一秒
                Log.d(TAG,"isAppExist endnd "+System.currentTimeMillis());
                return true;
            }
        }
        return false;
    }
}
