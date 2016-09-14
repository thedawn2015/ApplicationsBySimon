package com.simon.simple.polling;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.simon.simple.base.util.LogUtil;

/**
 * 轮询服务
 * Created by xw on 2016/9/14.
 */
public class PollingService extends Service {
    public static String TAG = PollingService.class.getSimpleName();

    public static final String POLLING_SERVICE_ACTION = "com.simon.simple.action.POLLING_SERVICE";

    private int count = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LogUtil.i(TAG, "onStart: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i(TAG, "onStartCommand: ");
        new PollingThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy: ");
    }

    /**
     * 轮询线程
     */
    private class PollingThread extends Thread {
        @Override
        public void run() {
            LogUtil.i(TAG, "run: " + count++);
        }
    }
}
