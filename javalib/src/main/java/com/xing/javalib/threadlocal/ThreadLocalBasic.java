package com.xing.javalib.threadlocal;

public class ThreadLocalBasic {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("zhao");
        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("value = "+threadLocal.get());
                threadLocal.set(200);
                System.out.println("new value = "+threadLocal.get());
            }
        });
        threadLocal.set(300);
        childThread.start();
        childThread.join();
        System.out.println("main value "+threadLocal.get());

    }

}
