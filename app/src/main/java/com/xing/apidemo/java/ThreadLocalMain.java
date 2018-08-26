package com.xing.apidemo.java;

public class ThreadLocalMain {

    private void testThreadLocal(){
        Thread thread = new Thread(){

            ThreadLocal<String> mStringThreadLocal = new ThreadLocal<>();
            @Override
            public void run() {
                super.run();
                mStringThreadLocal.set("zhao");
                String s = mStringThreadLocal.get();
            }
        };
        thread.start();
    }

}
