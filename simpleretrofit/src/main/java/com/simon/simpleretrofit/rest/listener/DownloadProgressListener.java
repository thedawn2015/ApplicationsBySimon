package com.simon.simpleretrofit.rest.listener;

/**
 * Created by xw on 2016/7/27.
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long currentLength, boolean done);
}
