package com.simon.simpleretrofit.rx;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by xw on 2016/7/28.
 */
public class SimpleRxJavaUtil {
    public static String TAG = SimpleRxJavaUtil.class.getSimpleName();

    /**
     * 基本的订阅和观察
     */
    public static void simpleMethod() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World1");
                subscriber.onNext("Hello World2");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        });

        //        Subscriber<String> subscriber = ;
        //        observable.subscribe(subscriber);
    }

    /**
     * 嵌套操作
     */
    public static void nestMethod() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("first");
                subscriber.onNext("second");
                subscriber.onCompleted();
            }
        })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        Log.i(TAG, "call1: s=" + s);
                        int num = 0;
                        switch (s) {
                            case "first":
                                num = 1;
                                break;
                            case "second":
                                num = 2;
                                break;
                        }
                        return num;
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        Log.i(TAG, "call2: integer=" + integer);
                        String str = null;
                        switch (integer) {
                            case 1:
                                str = "这是1";
                                break;
                            case 2:
                                str = "这是2";
                                break;
                        }
                        return str;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String string) {
                        Log.i(TAG, "call: string=" + string);

                    }
                });
    }
}
