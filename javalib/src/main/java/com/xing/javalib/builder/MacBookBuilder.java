package com.xing.javalib.builder;

/**
 * Created by zhao on 18-2-5.
 */

public class MacBookBuilder extends Builder {
    private Computer mComputer = new MacBook();
    @Override
    public void buildBoard(String borad) {
        mComputer.setBoard(borad);
    }

    @Override
    public void buildDisplay(String display) {
        mComputer.setDisplay(display);
    }

    @Override
    public void buildOs() {
        mComputer.setOs();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
