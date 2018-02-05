package com.xing.javalib.builder;

/**
 * Created by zhao on 18-2-5.
 */

public class Test {
    public static void main(String[] args){
        Builder builder = new MacBookBuilder();
        Director director = new Director(builder);
        director.construct("intel","retina");
        Computer computer = builder.create();
        System.out.println("computer info "+computer);
    }
}
