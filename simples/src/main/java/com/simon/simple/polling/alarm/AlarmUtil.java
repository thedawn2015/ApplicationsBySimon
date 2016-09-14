package com.simon.simple.polling.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.simon.simple.base.util.LogUtil;

/**
 * 闹钟 工具类
 * Created by xw on 2016/9/14.
 */
public class AlarmUtil {
    public static String TAG = AlarmUtil.class.getSimpleName();

    public static void setAlarm(Context context, int seconds, Class<?> receiverCls, String action) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //        long triggerAtTime = SystemClock.elapsedRealtime() + seconds * 1000;
        long triggerAtTime = System.currentTimeMillis() + seconds * 1000;
        Intent intent = new Intent(context, receiverCls);
        //        intent.setAction(action);
        //FLAG_CANCEL_CURRENT，如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= 19) {
//            LogUtil.i(TAG, "setAlarm: Build.VERSION.SDK_INT >= 19");
            manager.setExact(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        } else {
//            LogUtil.i(TAG, "setAlarm: Build.VERSION.SDK_INT < 19");
            manager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, seconds * 1000, pendingIntent);
        }
    }

    /**
     * 取消闹钟
     *
     * @param context
     * @param receiverCls
     * @param action
     */
    public static void cancelAlarm(Context context, Class<?> receiverCls, String action) {
//        LogUtil.i(TAG, "cancelAlarm: ");
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, receiverCls);
        //        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //取消正在执行的服务
        manager.cancel(pendingIntent);
    }
}
