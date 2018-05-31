package com.xing.apidemo.okhttp.utils;

/**
 * Created by zhao on 18-4-4.
 */

public class ExceptionUtil {

    public static void illegalArgument(String msg,Object... params){
        throw new IllegalArgumentException(String.format(msg,params));
    }
}
