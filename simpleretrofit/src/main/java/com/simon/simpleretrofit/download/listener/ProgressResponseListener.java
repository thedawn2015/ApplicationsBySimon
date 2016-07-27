package com.simon.simpleretrofit.download.listener;

/**
 * Created by xw on 2016/7/27.
 */
public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long currentLength, boolean done);
}
