package com.simon.simpleretrofit.download.listener;

import com.simon.simpleretrofit.download.model.DownloadItem;

/**
 * Created by xw on 2016/7/27.
 */
public interface OnDownloadProgressListener {
    //    void onResponseProgress(long bytesRead, long currentLength, boolean done);
    void updateProgress(DownloadItem downloadItem);
}
