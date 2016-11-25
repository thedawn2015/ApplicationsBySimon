package com.simon.simple.rx.util;

import com.simon.baseandroid.util.LogUtil;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * desc:创建操作
 * author: xiao
 * time: 2016/11/24
 */
public class CreateUtil {
    public static String TAG = CreateUtil.class.getSimpleName();

    public static void createMethod() {
        //Observable是被观察者
        /*Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
            }
        });*/
        //Subscribe订阅，它实现了Observer，即观察者
        /*Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {            }
            @Override
            public void onError(Throwable e) {            }
            @Override
            public void onNext(Object o) {            }
        };*/
        /*Observer observer = new Observer<String>() {
            @Override
            public void onCompleted() {            }
            @Override
            public void onError(Throwable e) {            }
            @Override
            public void onNext(String s) {            }
        };*/

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //call的操作，是在主线程还是子线程，是根据subscribeOn方法来设定的。要实现异步，一般都是子线程
                try {
                    //如果没有取消订阅
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            //这个是在主线程里面吧？
                            Thread.sleep(1000);
                            subscriber.onNext("这是第 " + i + "个");
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        })
                //订阅的方式，是在主线程还是在子线程中，在这里的意思是call的调用是在主线程还是子线程中
                .subscribeOn(Schedulers.io())
                //                .subscribeOn(AndroidSchedulers.mainThread())
                //订阅
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i(TAG, "onNext: String=" + s);
                    }
                });
    }



    public static void justMethod() {
        Observable<String> observable = Observable.just("第一个","第二个","第三个");
        //        observable.
    }


    public static void deferMethod() {
        Observable<Object> observable = Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable<Object> call() {
                return null;
            }
        });
        //        observable.
    }

}
