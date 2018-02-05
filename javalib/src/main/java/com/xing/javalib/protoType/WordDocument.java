package com.xing.javalib.protoType;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * Created by zhao on 18-2-5.
 */

public class WordDocument implements Cloneable {

    //文本
    private String mText;
    private ArrayList<String> mImagList = new ArrayList<>();

    public WordDocument(){
        System.out.println("construct");
    }

    @Override
    protected WordDocument clone() {
        try{
            WordDocument document = (WordDocument)super.clone();
            //这个字段也是浅拷贝，但是这个是string，是不可变的，因此赋值并不会改变原来的值
            document.mText = this.mText;
            //这个是浅拷贝，引用同一个对象
//            document.mImagList = this.mImagList;
            //对imageList也进行调用clone函数，进行深拷贝
            document.mImagList = (ArrayList<String>)this.mImagList.clone();
            return document;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public ArrayList<String> getImagList() {
        return mImagList;
    }

    public void setImagList(ArrayList<String> imagList) {
        mImagList = imagList;
    }

    public void addImage(String image){
        this.mImagList.add(image);
    }

    public void showContent(){
        String content = "\ndocument is "+toString();
        System.out.print(content);
    }

    @Override
    public String toString() {
        return "WordDocument{" +
                "mText='" + mText + '\'' +
                ", mImagList=" + mImagList +
                '}';
    }
}
