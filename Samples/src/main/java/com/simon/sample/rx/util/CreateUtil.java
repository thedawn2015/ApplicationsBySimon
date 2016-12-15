package com.simon.sample.rx.util;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.simon.base.listener.OnRequestCompletedListener;
import com.simon.baseandroid.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * desc:创建操作
 * author: xw
 * time: 2016/11/24
 */
public class CreateUtil {
    public static final String TAG = CreateUtil.class.getSimpleName();

    /**
     * Observable.create
     */
    public static void createMethod(final OnRequestCompletedListener<String> listener) {
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
                //观察者，即订阅者的显示，如果要在UI上改变，需要这样写
                .observeOn(AndroidSchedulers.mainThread())
                //订阅
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i(TAG, "onError: ");
                        listener.onCompleted(null, "error");
                    }

                    @Override
                    public void onNext(String response) {
                        LogUtil.i(TAG, "onNext: String=" + response);
                        listener.onCompleted(response, "success");
                    }
                });
    }

    /**
     * Observable.just
     */
    public static void justMethod(final OnRequestCompletedListener<String> listener) {
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();
        Observable.just("Hello", "Hi", "Aloha")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i(TAG, "justMethod onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i(TAG, "justMethod onError: ");
                        listener.onCompleted(null, "error");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i(TAG, "justMethod onNext: String=" + s);
                        listener.onCompleted(s, "success");
                    }
                });
    }

    /**
     * Observable.from
     * <p>
     * just(T...) 的例子和 from(T[]) 的例子，都和之前的 create(OnSubscribe) 的例子是等价的。
     */
    public static void fromMethod(final OnRequestCompletedListener<String> listener) {
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable.from(words)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i(TAG, "fromMethod onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i(TAG, "fromMethod onError: ");
                        listener.onCompleted(null, "error");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i(TAG, "fromMethod onNext: String=" + s);
                        listener.onCompleted(s, "success");
                    }
                });
    }

    /**
     * subscribe(new Action1<String>() {})
     */
    public static void actionMethod(final OnRequestCompletedListener<String> listener) {
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable<String> observable = Observable.from(words);

        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                LogUtil.i(TAG, "actionMethod call next: String = " + s);
                listener.onCompleted(s, "success");
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
                LogUtil.i(TAG, "actionMethod call error: error");
                listener.onCompleted(null, "error");
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                LogUtil.i(TAG, "actionMethod call complete: ");
            }
        };

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * subscribe(new Action1<String>() {})
     */
    public static void mapMethod(final OnRequestCompletedListener<Integer> listener) {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        Observable.from(list)
                //map是将原数据对象转换为新的数据对象
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        int num = 0;
                        switch (s) {
                            case "first":
                                num = 1;
                                break;
                            case "second":
                                num = 2;
                                break;
                            case "third":
                                num = 3;
                                break;
                        }
                        return num;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer i) {
                        LogUtil.i(TAG, "action1Method call: i=" + i);
                        listener.onCompleted(i, "success");
                    }
                });
    }

    /**
     * observeOn多次切换、map转换
     */
    public static Subscriber observeOnMethod(final OnRequestCompletedListener<Integer> listener) {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("four");

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                LogUtil.d(TAG, "onCompleted：");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i(TAG, "onError：");
            }

            @Override
            public void onNext(Integer i) {
                LogUtil.d(TAG, "onNext：i=" + i);
                listener.onCompleted(i, "i=" + i);
            }
        };
        Observable.from(list)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                //map是将原数据对象转换为新的数据对象
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        LogUtil.d(TAG, "map1: start");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        int num = 0;
                        switch (s) {
                            case "first":
                                num = 1;
                                break;
                            case "second":
                                num = 2;
                                break;
                            case "third":
                                num = 3;
                                break;
                            case "four":
                                num = 4;
                                break;
                        }
                        LogUtil.d(TAG, "map1: num=" + num);
                        return num;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        int num = 10;
                        num += integer;
                        LogUtil.d(TAG, "map2: num=" + num);
                        return "num=" + num;
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        LogUtil.d(TAG, "map3: s=" + s);
                        return Integer.parseInt(s.substring(4));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return subscriber;
    }


    /**
     * 判断是否点击
     *
     * @param view
     * @param listener
     */
    public static void rxBindingMethod(View view, final OnRequestCompletedListener<Integer> listener) {
        RxView.clicks(view)
                //防止连续点击
                .throttleFirst(100, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        LogUtil.i(TAG, "call: clicks");
                        listener.onCompleted(null, "clicked");
                    }
                });
    }

    /**
     * combineLatest +
     *
     * @param editText1
     * @param editText2
     * @param listener
     */
    public static void combineLatestMethod(EditText editText1, EditText editText2, final Button button, final OnRequestCompletedListener<String> listener) {
        Observable.combineLatest(
                RxTextView.textChanges(editText1).skip(1),
                RxTextView.textChanges(editText2).skip(1),
                new Func2<CharSequence, CharSequence, Map<String, String>>() {
                    @Override
                    public Map<String, String> call(CharSequence charSequence, CharSequence charSequence2) {
                        Map<String, String> map = new HashMap<>();
                        map.put("username", charSequence.toString());
                        map.put("password", charSequence2.toString());
                        return map;
                    }
                })
                .subscribe(new Action1<Map<String, String>>() {
                    @Override
                    public void call(Map<String, String> map) {
                        String username = map.get("username");
                        String password = map.get("password");
                        if (username.length() > 6 && password.length() > 6) {
                            listener.onCompleted("username=" + username + ";password=" + password, "成功");
                            button.setEnabled(true);
                        } else {
                            button.setEnabled(false);
                        }
                    }
                });

    }

}
