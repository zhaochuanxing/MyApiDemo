package com.xing.javalib.builder;

/**
 * Created by zhao on 18-2-5.
 *
 * 负责构造computer
 * 相当于是客户端代码了
 */

public class Director {
    Builder mBuilder = null;

    public Director(Builder builder){
        this.mBuilder = builder;
    }

    public void construct(String borad,String display){
        mBuilder.buildBoard(borad);
        mBuilder.buildDisplay(display);
        mBuilder.buildOs();
    }

}
