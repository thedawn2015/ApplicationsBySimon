package com.simon.simpleretrofit.rest.util;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xw on 2016/7/27.
 */
public class DownloadUtil {

    public static void downloadNewVersionApk(String apkUrl) {
        ServiceInstance.getInstance().getRetrofitService()
                .getDownloadInterface()
                .downloadNewVersionApk(apkUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }
                });
    }
}
