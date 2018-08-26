package com.xing.apidemo.livedata;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyObserver myObserver = new MyObserver();
        MyLiveData myLiveData = new MyLiveData();
        myLiveData.observe(this,myObserver);
    }

    private class MyObserver implements Observer<String>{

        @Override
        public void onChanged(@Nullable String s) {
            Toast.makeText(LiveDataActivity.this,"MyObserv:"+s,Toast.LENGTH_SHORT).show();
        }
    }
}
