package com.simon.simpleretrofit.download.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.simon.simpleretrofit.download.model.DownloadItem;

/**
 * 下载进度的广播，通过回调进行页面的刷新
 * Created by xw on 2016/7/29.
 */
public class DownloadProgressReceiver extends BroadcastReceiver {
    public static String TAG = DownloadProgressReceiver.class.getSimpleName();

    public static final String DOWNLOAD_PROGRESS_ACTION = "com.simon.simple.DOWNLOAD_PROGRESS_ACTION";
    public static final String DOWNLOAD_ITEM_EXTRA = "com.simon.simple.DOWNLOAD_PROGRESS_ACTION";

    private OnDownloadItenChangeListener onDownloadItenChangeListener;

    public DownloadProgressReceiver(OnDownloadItenChangeListener onDownloadItenChangeListener) {
        this.onDownloadItenChangeListener = onDownloadItenChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        DownloadItem downloadItem = intent.getParcelableExtra(DOWNLOAD_ITEM_EXTRA);
        onDownloadItenChangeListener.updateProgress(downloadItem);
    }

    public interface OnDownloadItenChangeListener {
        void updateProgress(DownloadItem downloadItem);
    }
}
