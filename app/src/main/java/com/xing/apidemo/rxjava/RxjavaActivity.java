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
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

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
//        createInterval();

//        doAsyncSubject();
//        doBehaviorSubject();
//        doPublisSubject();
        doReplySubject();
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

    /**
     * https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
     *
     * Subject可以看成是一个桥梁或者代理，在某些ReactiveX实现中（如RxJava），它同时充当了Observer和Observable的角色。因为它是一个Observer，它可以订阅一个或多个Observable；又因为它是一个Observable，它可以转发它收到(Observe)的数据，也可以发射新的数据。
     由于一个Subject订阅一个Observable，它可以触发这个Observable开始发射数据（如果那个Observable是"冷"的--就是说，它等待有订阅才开始发射数据）。因此有这样的效果，Subject可以把原来那个"冷"的Observable变成"热"的。

     */


    /**
     * 一个AsyncSubject只在原始Observable完成后，发射来自原始Observable的最后一个值。
     * （如果原始Observable没有发射任何值，AsyncObject也不发射任何值）它会把这最后一个值发射给任何后续的观察者。
     *
     * https://www.jianshu.com/p/1257c8ba7c0c
     */
    private void doAsyncSubject(){
        //值得注意的是一定要用Subcect.create()的方式创建并使用，不要用just(T)、from(T)、create(T)创建，否则会导致失效...
        //// 因为just(T)、from(T)、create(T)会把Subject转换为Obserable
        AsyncSubject<Integer> as = AsyncSubject.create();
        //触发事件
        as.onNext(1);
        as.onNext(2);
        as.onNext(3);
        //结束后订阅收到 3
        //无论输入多少参数，永远只输出最后一个参数。
        as.onCompleted();

        as.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                Log.i(TAG,"doAsyncSubject "+value);
            }
        });

    }

    /**
     * 当观察者订阅BehaviorSubject时，它开始发射原始Observable最近发射的数据（如果此时还没有收到任何数据，它会发射一个默认值）
     * ，然后继续发射其它任何来自原始Observable的数据。
     */
    private void doBehaviorSubject(){
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create(1);
//        behaviorSubject.onNext(1);
//        behaviorSubject.onNext(2);
//        behaviorSubject.onNext(3);
        behaviorSubject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"doBehaviorSubject "+integer);
            }
        });
        behaviorSubject.onNext(4);
        behaviorSubject.onNext(5);
        behaviorSubject.onCompleted();
        //根据上面的说明 输出为1 4 5
    }

    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
     * PublishSubject可能会一创建完成就立刻开始发射数据（除非你可以阻止它发生）
     * ，因此这里有一个风险：在Subject被创建后到有观察者订阅它之前这个时间段内，一个或多个数据可能会丢失。如果要确保来自原始Observable的所有数据都被分发，你需要这样做：或者使用Create创建那个Observable以便手动给它引入"冷"Observable的行为（当所有观察者都已经订阅时才开始发射数据）
     */
    private void doPublisSubject(){
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(11);
        publishSubject.onNext(12);
        publishSubject.onNext(13);
//        publishSubject.onCompleted();
        publishSubject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"doPublisSubject "+integer);
            }
        });
        //只能在订阅之后的才能收到
        publishSubject.onNext(15);
        publishSubject.onNext(16);
    }

    /**
     * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。也有其它版本的ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据（原始Observable发射的）。

     如果你把ReplaySubject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法（包括其它的on系列方法），这可能导致同时（非顺序）调用，这会违反Observable协议，给Subject的结果增加了不确定性。
     */
    private void doReplySubject(){
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("12");
        replaySubject.onNext("23");
        replaySubject.onNext("333");
        replaySubject.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"doReplySubject "+s);

            }
        });
        replaySubject.onNext("tianxia");
        replaySubject.onNext("tege");
    }

}
