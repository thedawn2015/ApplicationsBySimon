package com.simon.simple;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.simon.baseandroid.util.LogUtil;
import com.simon.baseandroid.util.ToastUtil;

/**
 * Created by xw on 2016/9/28.
 */
public class TheApplication extends Application {
    public static String TAG = TheApplication.class.getSimpleName();

    private int activityCount;//activity的count数
    private boolean isForeground;//是否在前台

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate: TheApplication");
        ToastUtil.showShort(getApplicationContext(), "TheApplication");

        lifecycleCallbacks();

//        getCurProcessName(getApplicationContext());
    }

    private void lifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                activityCount++;
                /*if (activityCount == 1) {
                    ToastUtil.showShort(getApplicationContext(), "进入前台了");
                }*/
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                activityCount--;
                if (activityCount == 0) {
                    isForeground = false;
                    ToastUtil.showShort(getApplicationContext(), "进入后台了");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            LogUtil.i(TAG, "getCurProcessName: processName=" + appProcess.processName);
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }
}
