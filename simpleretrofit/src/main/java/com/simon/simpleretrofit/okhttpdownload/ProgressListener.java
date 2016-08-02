package com.simon.simpleretrofit.okhttpdownload;

/**
 * 下载进度listener
 * Created by JokAr on 16/5/11.
 */
public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
