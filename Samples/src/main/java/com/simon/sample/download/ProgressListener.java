package com.simon.sample.download;

/**
 * 下载进度listener
 * Created by xw on 2016/8/2
 */
public interface ProgressListener {
    void update(long currentLength, long totalLength, boolean isDownloaded);
}
