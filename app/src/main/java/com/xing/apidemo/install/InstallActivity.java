package com.xing.apidemo.install;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

import java.io.File;

public class InstallActivity extends AppCompatActivity {

    public static final String TAG = InstallActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        final Button installBtn = (Button)findViewById(R.id.btn_install);
        installBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                installApk();
            }
        });
//        printPath();

//        installApk();
    }

    private void installApk() {
        File downDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String apkName = "com.xinli.yixinli_4.9.5_43.apk";
        File apkFile = new File(downDir,apkName);
        Log.i(TAG,"apkFile : "+apkFile);
        Log.i(TAG,"exists: "+apkFile.exists());

        Intent intent = new Intent(Intent.ACTION_VIEW);

        String type = "application/vnd.android.package-archive";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this,this.getPackageName()+".fileProvider",apkFile);
            Log.i(TAG,"content uri: "+contentUri);
            intent.setDataAndType(contentUri,type);
        }else{
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apkFile),type);
        }
        startActivity(intent);
    }

    private void printPath() {
        File sdCard = Environment.getExternalStorageDirectory();
        Log.i(TAG,"sdcard Path: "+sdCard);
        ///storage/emulated/0
        File pictureFile = new File(sdCard,"Picture");
        ///storage/emulated/0/Picture
        Log.i(TAG,"picture path: "+pictureFile);

        File download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //storage/emulated/0/Download
        Log.i(TAG,"download: "+download);

        File fileDir = this.getFilesDir();
        File cacheDir = this.getCacheDir();
        ///data/user/0/com.xing.apidemo/files
        Log.i(TAG,"file Dir: "+fileDir);
        ///data/user/0/com.xing.apidemo/cache
        Log.i(TAG,"cache Dir: "+cacheDir);

        File externalFile = this.getExternalFilesDir(null);
        // /storage/emulated/0/Android/data/com.xing.apidemo/files
        Log.i(TAG,"externalFile: "+externalFile);
        File musicFile = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        // /storage/emulated/0/Android/data/com.xing.apidemo/files/Music
        Log.i(TAG,"musicFile : "+musicFile);
        File cacheFile = this.getExternalCacheDir();
        //   /storage/emulated/0/Android/data/com.xing.apidemo/cache
        Log.i(TAG,"cache File : "+cacheFile);
        File myFile = this.getExternalFilesDir("zhao");
        //  /storage/emulated/0/Android/data/com.xing.apidemo/files/zhao
        Log.i(TAG,"myFile : "+myFile);
        // mounted Environment.MEDIA_MOUNTED
        Log.i(TAG,"status: "+Environment.getExternalStorageState());
    }


}
