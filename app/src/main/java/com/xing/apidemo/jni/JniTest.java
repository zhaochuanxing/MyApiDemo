package com.xing.apidemo.jni;

public class JniTest {

    static{
        System.loadLibrary("jni-test");
    }

    public static void main(String args[]){
        JniTest jniTest = new JniTest();
        System.out.println(jniTest.get());
        jniTest.set("Hello java");
    }

    public native String get();
    public native void set(String str);
}
