package com.simon.simple.polling;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.simon.simple.base.util.LogUtil;

/**
 * 请求服务类，在后台长期运行，每个一段时间就向服务器发送一次请求
 * Created by xw on 2016/9/14.
 */
public class RequestService extends Service {
    public static String TAG = RequestService.class.getSimpleName();

    public static final String REQUEST_SERVICE_ACTION = "com.simon.simple.action.REQUEST_SERVICE";

    private boolean flag = true;
    private RequestThread requestThread;
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate: ");
        flag = true;
        requestThread = new RequestThread();
        requestThread.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestThread = null;
        flag = false;
    }

    /**
     * 请求的线程
     */
    private class RequestThread extends Thread {
        @Override
        public void run() {
            while (flag) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.i(TAG, "run: " + count++);
            }
        }
    }
}
