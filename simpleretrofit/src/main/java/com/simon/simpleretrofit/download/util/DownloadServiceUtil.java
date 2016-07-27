package com.simon.simpleretrofit.download.util;

import android.util.Log;

import com.simon.simpleretrofit.download.service.ServiceProvider;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xw on 2016/7/27.
 */
public class DownloadServiceUtil {
    public static String TAG = DownloadServiceUtil.class.getSimpleName();

    /**
     * 下载apk文件
     *
     * @param url
     */
    public static void downloadApk(String url) {

        ServiceProvider.getInstance().getRetrofitService()
                .getDownloadService()
                .download(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            Log.i(TAG, "call: responseBody.bytes()=" + responseBody.bytes());
                            Log.i(TAG, "call: responseBody.contentLength()=" + responseBody.contentLength());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //                        InputStream stream = responseBody.byteStream();
                        Log.i(TAG, "onNext: responseBody." + responseBody.contentLength());
                    }
                });
    }
}
