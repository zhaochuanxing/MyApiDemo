package com.xing.javalib.builder;

/**
 * Created by zhao on 18-2-5.
 */

public class MacBook extends Computer {

    protected MacBook(){

    }

    @Override
    public void setOs() {
        this.mOs = "Mac OS X";
    }
}
