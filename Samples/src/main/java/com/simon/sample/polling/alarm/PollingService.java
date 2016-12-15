package com.simon.sample.polling.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.simon.baseandroid.util.LogUtil;

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i(TAG, "onStartCommand: ");
        new PollingThread().start();
        //发送广播
        AlarmUtil.setAlarm(PollingService.this, 5, AlarmReceiver.class, PollingService.POLLING_SERVICE_ACTION);
        //        return Service.START_NOT_STICKY;
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
