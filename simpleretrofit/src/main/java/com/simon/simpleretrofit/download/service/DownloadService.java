package com.simon.simpleretrofit.download.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 下载
 * Created by xw on 2016/7/27.
 */
public interface DownloadService {

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String apkUrl);
}
