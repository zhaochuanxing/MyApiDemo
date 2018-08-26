package com.xing.apidemo.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.xing.apidemo.roomdata.MyDataBase;

public class MyLiveData extends MutableLiveData<String> {

    private static final String TAG = MyLiveData.class.getSimpleName();
    private int mCount = 0;
    private boolean mIsRun = true;
    private LongTimeWork mThread = new LongTimeWork();

    public MyLiveData(){
        mThread.start();
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.i(TAG,"onActive ");
        mIsRun = true;
        mThread.interrupt();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.i(TAG,"onInactive");
        mIsRun = false;

    }

    private class LongTimeWork extends Thread{

        @Override
        public void run() {
            super.run();
            while (true){
                if(!mIsRun){
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mCount ++;
                postValue(String.valueOf(mCount));
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
