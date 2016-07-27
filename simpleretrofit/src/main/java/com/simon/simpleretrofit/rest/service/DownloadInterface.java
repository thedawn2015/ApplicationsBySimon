package com.simon.simpleretrofit.rest.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 下载
 * Created by xw on 2016/7/27.
 */
public interface DownloadInterface {
    @GET
    Observable<ResponseBody> downloadNewVersionApk(@Url String apkUrl);
}
