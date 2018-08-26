package com.xing.apidemo.eventbus;

import android.app.Activity;
import android.os.Bundle;

import com.xing.apidemo.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        EventBus.getDefault().register(this);

    }
}
