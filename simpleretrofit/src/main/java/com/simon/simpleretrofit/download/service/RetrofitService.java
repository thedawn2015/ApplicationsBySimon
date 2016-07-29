package com.simon.simpleretrofit.download.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by xw on 2016/7/27.
 */
public class RetrofitService {
    public static String TAG = RetrofitService.class.getSimpleName();

    public static final String url = "http://t.klicen.com/";

    private DownloadService downloadService;
    private static final int DEFAULT_TIMEOUT = 15;
    public Retrofit retrofit;

    public RetrofitService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        downloadService = retrofit.create(DownloadService.class);
    }

    public DownloadService getDownloadService() {
        return downloadService;
    }
}
