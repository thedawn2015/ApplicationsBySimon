package com.simon.simpleretrofit.rx;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xw on 2016/7/28.
 */
public class SimpleRxJavaUtil {
    public static String TAG = SimpleRxJavaUtil.class.getSimpleName();

    public static void SimpleMethod() {
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
}
