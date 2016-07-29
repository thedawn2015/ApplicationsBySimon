package com.simon.simpleretrofit.download.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Service
 * Created by xw on 2016/7/27.
 */
public class RetrofitService {
    public static String TAG = RetrofitService.class.getSimpleName();

    public static final String url = "http://t.klicen.com/";
    private static final boolean DEBUG = true;

    private DownloadService downloadService;
    public Retrofit retrofit;

    public RetrofitService() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (DEBUG) {
            //http log
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        OkHttpClient httpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        downloadService = retrofit.create(DownloadService.class);
    }

    public DownloadService getDownloadService() {
        return downloadService;
    }
}
