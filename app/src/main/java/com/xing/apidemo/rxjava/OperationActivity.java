package com.xing.apidemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xing.apidemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class OperationActivity extends AppCompatActivity {

    private static final String TAG = OperationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
//        doMap();
//        doFlatMap();
//        doContactMap();
//        doFlatMapIterable();
//        doBuffer();
//            doGroupBy();
//        doFilter();
//            doElementAt();
//        doDistinct();
//        doSkipAndTake();
//        doIgnoreElements();
        doThrottleFirst();
    }

    /**
     * 节流器
     */
    private void doThrottleFirst() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<10;i++){
                    //定义生成事件的方式，是每隔100ms发送一次，递增
                    subscriber.onNext(i);
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        }).throttleFirst(200, TimeUnit.MILLISECONDS)//发射在200ms内的第一个数据，如果不加这个就会发射所有的，相当于是控制流速，节流器的作用
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"doThrottleFirst "+integer);
            }
        });
    }

    /**
     * 忽略所有源observalbe的操作的结果，只会把onCompleted和onError事件通知给订阅者
     */
    private void doIgnoreElements() {
        Observable.just(1,2,3,4).ignoreElements().subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                //只会收到这个回调
                Log.i(TAG,"onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"OnError ",e);
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG,"onNext "+integer);
            }
        });
    }

    private void doSkipAndTake() {
        Observable.just(10,11,12,13,15,17).skip(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"skip "+integer);
            }
        });
        Observable.just("a","b","c","d","e").take(3).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"take "+s);
            }
        });
    }

    private void doDistinct() {
        //链式操作随时可以加入，便于组合处理
        Observable.just(1,22,22,3,3,5,6).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"doDistinct "+integer);
            }
        });
    }

    private void doMap(){
        final String host = "http://blog.csdn.net/";
        //将事件添加到任务队列中的便利方法
        Observable.just("zhao").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return host+s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"call "+s);
            }
        });
    }

    /**
     *     批量的处理，效果同上面的加上for循环
     *  合并允许交叉，输出的顺序可能不是发送的顺序
     */

    private void doFlatMap(){
        final String host = "http://blog.csdn.net/";
        List<String> nameList = new ArrayList<>();
        nameList.add("zhao");
        nameList.add("tian");
        nameList.add("xia");
        nameList.add("gege");
        //function 是做observable的预处理的或者是做转换的
        // action是做observable的处理监听的
        Observable.from(nameList).flatMap(new Func1<String, Observable<?>>() {
            @Override
            public Observable<?> call(String s) {
                return Observable.just(host+s);
            }
        }).cast(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"flatMap = "+s);
            }
        });
    }

    /**
     * 同flatMap,但是解决了交叉的问题，将发射的值连续在一起，而不是合并起来。
     */
    private void doContactMap(){
        final String host = "http://blog.csdn.net/";
        List<String> nameList = new ArrayList<>();
        nameList.add("zhao");
        nameList.add("tian");
        nameList.add("xia");
        nameList.add("gege");
        //function 是做observable的预处理的或者是做转换的
        // action是做observable的处理监听的
        Observable.from(nameList).concatMap(new Func1<String, Observable<?>>() {
            @Override
            public Observable<?> call(String s) {
                return Observable.just(host+s);
            }
        }).cast(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"concatMap = "+s);
            }
        });
    }

    private void doFlatMapIterable(){
        Observable.just(1,2,3).flatMapIterable(new Func1<Integer, Iterable<?>>() {
            @Override
            public Iterable<Integer> call(Integer integer) {
                List<Integer> list = new ArrayList<>();
                list.add(integer+1);
                Log.i(TAG,"Iterable "+integer);
                return list;
            }
        }).cast(Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"doFlatMapIterable "+integer);
            }
        });
    }

    private void doBuffer(){
        Observable.just(1,2,3,4,5,6,7,8)
                //一次发送3个，缓存容量是3
                .buffer(3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        for(int i:integers){
                            Log.i(TAG,"list item = "+i);
                        }
                        Log.i(TAG,"show buffer");
                    }
                });

    }

    /**
     * 分组
     */
    private void doGroupBy(){
        Swodman swodman = new Swodman("zhao",1);
        Swodman swodman1 = new Swodman("tian",2) ;
        Swodman swodman2 = new Swodman("haha",3);
        Swodman swodman3 = new Swodman("jia",2);
        Swodman swodman4 = new Swodman("com",1);

        Observable<GroupedObservable<String,Swodman>> groupedObservableObservable =
                Observable.just(swodman,swodman1,swodman3,swodman2,swodman4).groupBy(new Func1<Swodman, String>() {
                    @Override
                    public String call(Swodman swodman) {
                        //分组的依据
                        return swodman.mLevel+"";
                    }
                });
        Observable.concat(groupedObservableObservable).subscribe(new Action1<Swodman>() {
            @Override
            public void call(Swodman swodman) {
                Log.i(TAG,"swodman "+swodman);
            }
        });
    }

     class Swodman{
        public String mName;
        public int mLevel;

         public Swodman(String name, int level) {
             mName = name;
             mLevel = level;
         }

         @Override
         public String toString() {
             return "Swodman{" +
                     "mName='" + mName + '\'' +
                     ", mLevel=" + mLevel +
                     '}';
         }
     }

    /**
     * 对数据增加筛选条件
     */
    private void doFilter(){
        Observable.just(1,2,3,4).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer>2;
            }
        }) .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"doFilter"+integer);
            }
        });
     }

     private void doElementAt(){
        //设置事件 （数据信息的发送）的便利方法
         Observable.just(1,2,78,7).elementAt(3).subscribe(new Action1<Integer>() {
             @Override
             public void call(Integer integer) {
                 Log.i(TAG,"doElement at"+integer);
             }
         });
     }

}
