package com.xing.apidemo.glide;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhao on 17-11-16.
 */

public class Md5Util {

    public static String getMd5(Bitmap bitmap){
        byte[] bitmapByte = getBitmapByte(bitmap);
        if(bitmapByte!=null&& bitmapByte.length>0){
            return md5(bitmapByte);
        }
        return "";
    }

    public static byte[] getBitmapByte(Bitmap bitmap){
        if(bitmap==null){
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;
    }

    public static String md5(byte[] input){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input);
            byte[] digest = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1,digest);
            String resultStr = bigInteger.toString(16);
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
