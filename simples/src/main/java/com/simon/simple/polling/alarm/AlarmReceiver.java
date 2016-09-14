package com.simon.simple.polling.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.simon.simple.base.util.LogUtil;

/**
 * Alarm Receiver
 * Created by xw on 2016/9/14.
 */
public class AlarmReceiver extends BroadcastReceiver {
    public static String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
//        LogUtil.i(TAG, "onReceive: intent.getAction()=" + intent.getAction());
        AlarmUtil.cancelAlarm(context, AlarmReceiver.class, null);
        Intent toIntent = new Intent(context, PollingService.class);
        context.startService(toIntent);
    }
}
