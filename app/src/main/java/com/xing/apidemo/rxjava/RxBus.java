package com.xing.apidemo.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by zhao on 18-1-21.
 */

public class RxBus {

    private volatile static RxBus mInstace;


    //注意，Subject 并不是线程安全的，如果想要其线程安全需要调用toSerialized()方法。
    // (在RxJava1.x的时代还可以用 SerializedSubject 代替 Subject，但是在RxJava2.x以后SerializedSubject不再是一个public class）
    //然而，很多基于 EventBus 改造的 RxBus 并没有这么做，包括我以前也写过这样的 RxBus :( 。这样的做法是非常危险的，因为会遇到并发的情况。

    private final Subject<Object,Object> mSubject;

    private RxBus(){
        mSubject = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance(){
        if(mInstace==null){
            synchronized (RxBus.class){
                if(mInstace ==null){
                    mInstace = new RxBus();
                }
            }
        }
        return mInstace;
    }

    public void post(Object object){
        mSubject.onNext(object);
    }

    public <T>Observable<T> toObservable(Class<T> eventType){
        //只会发送指定类型的数据
        return mSubject.ofType(eventType);
    }
}
