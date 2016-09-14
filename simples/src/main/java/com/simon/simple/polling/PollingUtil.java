package com.simon.simple.polling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import com.simon.simple.base.util.LogUtil;

/**
 * 轮询工具类
 * Created by xw on 2016/9/14.
 */
public class PollingUtil {
    public static String TAG = PollingUtil.class.getSimpleName();

    /**
     * 开启轮询服务
     *
     * @param context
     * @param seconds
     * @param cls
     * @param action
     */
    public static void startPollingService(Context context, int seconds, Class<?> cls, String action) {
        //获取AlarmManager系统服务
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //包装需要执行Service的Intent
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();
        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        //        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, seconds * 1000, pendingIntent);
        if (Build.VERSION.SDK_INT >= 19) {
            LogUtil.i(TAG, "startPollingService: SDK_INT >= 19");
            //不会重复，windowLengthMillis 是允许系统调控的时间
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP,triggerAtTime,pendingIntent);
            alarmManager.setWindow(AlarmManager.RTC_WAKEUP, 0, seconds * 1000, pendingIntent);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, seconds * 1000, pendingIntent);
        } else {
            LogUtil.i(TAG, "startPollingService: SDK_INT < 19");
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, seconds * 1000, pendingIntent);
        }

    }

    /**
     * 停止轮询服务
     *
     * @param context
     * @param cls
     * @param action
     */
    public static void stopPollingService(Context context, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //取消正在执行的服务
        manager.cancel(pendingIntent);
    }
}
