package com.xing.javalib.protoType;

/**
 * Created by zhao on 18-2-5.
 */

public class Client {
    public static void main(String[] args){
        WordDocument wordDocument = new WordDocument();
        wordDocument.setText("text");
        wordDocument.addImage("pic1");
        wordDocument.addImage("pic2");
        wordDocument.addImage("pic3");
        wordDocument.showContent();

        WordDocument cp = wordDocument.clone();
        cp.setText("copy");
        cp.addImage("cp image");
        cp.showContent();
        wordDocument.showContent();
    }
}
