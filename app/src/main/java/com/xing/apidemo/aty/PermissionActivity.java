package com.xing.apidemo.aty;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.content.ContextCompat;

import com.xing.apidemo.R;

public class PermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        checkAndRequestPermission();
    }

    private void checkAndRequestPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);
        //上面方法是这个方法的便利封装
        int result = checkPermission(Manifest.permission.WRITE_CALENDAR, Process.myPid(), Process.myUid());
        //强制去请求权限，否则抛出异常
        enforcePermission(Manifest.permission.WRITE_CONTACTS, android.os.Process.myPid(), android.os.Process.myUid(), "force");
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){//0 获取权限

        }else if(permissionCheck == PackageManager.PERMISSION_DENIED){//未能获取权限

        }

    }
}
