package com.xing.javalib.builder;

/**
 * Created by zhao on 18-2-1.
 *
 * 计算机抽象类，即product角色
 */

public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;
    protected Computer(){

    }

    public void setBoard(String board){
        this.mBoard = board;
    }

    public void setDisplay(String display){
        this.mDisplay = display;
    }

    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOs='" + mOs + '\'' +
                '}';
    }
}
