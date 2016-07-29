package com.simon.simpleretrofit.download.listener;

/**
 * 下载进度的回调接口
 * Created by xw on 2016/7/27.
 */
public interface OnDownloadProgressListener {
    void updateProgress(long currentSize, long totalSize, boolean done);
}
