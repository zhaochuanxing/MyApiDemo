package com.xing.apidemo.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.xing.apidemo.R;
import com.xing.apidemo.material.CardActivity;

public class NotificationActivity extends AppCompatActivity {

    private static final int ID_BASIC = 1;
    private static final int ID_COLLAPSE = 2;
    private Button mBasicBtn;
    private Button mCollapseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mBasicBtn = (Button)findViewById(R.id.btn_basic);
        mBasicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasicBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showBasicNotification();

                    }
                },3000);
            }
        });

        mCollapseBtn = (Button)findViewById(R.id.btn_collapse);
        mCollapseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemoteNotification();
            }
        });
    }

    private void showBasicNotification(){
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        builder.setSmallIcon(R.drawable.card_history);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.fish));
        builder.setContentTitle("Basic");
        builder.setContentText("It is a Basic notification");
        builder.setSubText("It is really basic");
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setLights(0xff00ff00,300,100);

        //设置悬挂式的通知
        Intent handleIntent = new Intent();
        handleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        handleIntent.setClass(this,CardActivity.class);
        PendingIntent handlePendingIntent = PendingIntent.getActivity(this,0,handleIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(handlePendingIntent,true);

        //设置级别
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
            builder.setVisibility(Notification.VISIBILITY_PRIVATE);
//            builder.setVisibility(Notification.VISIBILITY_SECRET);
        }


        Notification notification = builder.build();

//        notification.defaults |=Notification.DEFAULT_SOUND;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
//        notification.defaults |= Notification.DEFAULT_LIGHTS;

//        notification.defaults |= Notification.DEFAULT_ALL;
//        notification.defaults |= Notification.FLAG_SHOW_LIGHTS;
//        notification.ledARGB = 0xffff0000;
//        notification.ledOnMS = 3000;
//        notification.ledOffMS = 3000;
//        builder.setLights()


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(ID_BASIC,notification);
        }
    }

    private void showRemoteNotification(){

        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:123"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.card_history);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.fish));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠式通知");

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.activity_notification);
        remoteViews.setTextViewText(R.id.tx_title,"show me when collapsed");
        Notification notification = builder.build();
        notification.bigContentView = remoteViews;

        RemoteViews normalView = new RemoteViews(getPackageName(),R.layout.activity_from);
        notification.contentView = normalView;

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(ID_COLLAPSE,notification);
        }
//        RemoteViews expandView = new RemoteViews(getPackageName(),R.layout.activity_ripple);
    }

}
