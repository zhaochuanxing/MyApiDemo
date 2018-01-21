package com.xing.apidemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xing.apidemo.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.plugins.RxJavaCompletableExecutionHook;

public class RxjavaActivity extends AppCompatActivity {

    private Observable<String> mObservable;
    private Subscriber<String> mSubscriber;
    private static final String TAG = RxjavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        createSubscriber();
        createObervable();
//        subscribe();
//        createAction();
        createInterval();
    }

    // 创建观察者(订阅者） 同observer
    private void createSubscriber() {
        mSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "oncompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError", e);
            }

            @Override
            public void onNext(String o) {
                Log.i(TAG, "onNext " + o);
            }

            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart");
            }
        };
    }

    //创建观察者的另一种写法
    private void createObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
    }

    /**
     * 创建被观察者，决定什么时候触发事件，以及触发怎样的事件
     * 被观察者，更加具体的说应该是指产生事件的事件源头，负责产生事件不负责处理事件
     * 这个也非常重要，怎么做很重要，但是什么时候做，做什么也非常重要
     */
    private void createObervable() {
        mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("zcx");
                subscriber.onNext("chuanshuo");
                subscriber.onCompleted();
            }
        });

        //创建被观察者，
        mObservable = Observable.just("tian", "xia", "android");
        mObservable = Observable.from(new String[]{"ios", "android", "apple", "google"});
    }

    /*
    * 实现观察者和被观察者的绑定关系 观察者订阅被观察者
    * 代码中是observable调用方法，传入subsciber作为参数，似乎是反道而行
    * 其实合理的，用户订阅了报纸，报社需要知道用户住在那里，用户则不要知道报社的位置
    * 只需要产生一个订阅关系就可以了
     */
    private void subscribe() {
        mObservable.subscribe(mSubscriber);
    }

    /**
     * 不完整的定义回调
     * 是一种对与事件处理的一种便利方法
     * 更类似于一种面向过程的方法
     * 或者面向函数的方法
     * 传入的实际上是方法体
     */
    private void createAction() {

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "onNextAction " + s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable s) {
                Log.i(TAG, "onErrorAction");
            }
        };

        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                Log.i(TAG, "onCompleteAction haha");
            }
        };

        //通过将参数的数量和类型来做匹配
        // 最终还是包装成了ActionSubscriber
        mObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }

    private void createInterval() {
        //每隔3s发送整数序列
        Observable.interval(3, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.i(TAG, "interval " + aLong);
            }
        });

        // 创建发射指定范围内的整数序列，左闭右开，可以代替for循环，没有延时
        Observable.range(0, 10)
                //设置重复次数 10 * 3 call 执行次数
                .repeat(3)
                //设置完成绑定，将事件发送给处理事件的对象中
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "range " + integer);
                    }
                });
    }


}
