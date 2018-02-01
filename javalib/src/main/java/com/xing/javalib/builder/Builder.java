package com.xing.javalib.builder;

/**
 * Created by zhao on 18-2-1.
 */

public abstract class Builder {
    public abstract void buildBoard(String borad);
    public abstract void buildDisplay(String display);
    public abstract void buildOs();
    public abstract Computer create();
}
