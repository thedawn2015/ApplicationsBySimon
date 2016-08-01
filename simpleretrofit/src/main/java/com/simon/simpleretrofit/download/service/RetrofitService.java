package com.simon.simpleretrofit.download.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    //不要http log，影响下载进度。
    private static final boolean DEBUG = false;

    private DownloadService downloadService;
    public Retrofit retrofit;
    //断点续传
    public long startPoints;

    public RetrofitService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        request = request.newBuilder()
                                .addHeader("Range", "bytes=" + startPoints + "-")
                                //                        .addHeader("deviceplatform", "android")
                                //                        .removeHeader("User-Agent")
                                //                        .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                                .build();
                        return chain.proceed(request);
                    }
                });
        //这个东西简直太凶残了，只要添加这个筛选，下载的时候，连接上了资源，但是要等待好久...
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

    public void setStartPoints(long startPoints) {
        this.startPoints = startPoints;
    }
}
