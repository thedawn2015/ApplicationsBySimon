package com.simon.sample.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.simon.baseandroid.util.DateUtil;
import com.simon.sample.MainActivity;
import com.simon.sample.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by xw on 2016/9/14.
 */
public class NotificationUtil {
    /**
     * Send a sample notification using the NotificationCompat API.
     * <p/>
     * https://developer.android.com/samples/BasicNotifications/src/com.example.android.basicnotifications/MainActivity.html
     */
    public static void sendBasicNotification(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.string2Date("2017/01/24 10:20:30", "yyyy/MM/dd HH:mm:ss"));

        int notificationID = new Random().nextInt(10000000);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.notification_icon_large))
                .setShowWhen(true)
                .setWhen(calendar.getTimeInMillis())
                // Set the intent that will fire when the user taps the notification.
                .setContentIntent(getPendingIntent(context))
                .setAutoCancel(false)
                .setContentTitle("BasicNotifications Sample")
                .setContentText("Time to learn about notifications!")
                .setSubText("Tap to view documentation about notifications.")
                .setDefaults(Notification.FLAG_SHOW_LIGHTS)
                //                .setDefaults(Notification.DEFAULT_ALL)
                //                .setStyle(bigTextStyle)
                .setPriority(Notification.PRIORITY_MAX)
                //                .setWhen(calendar.getTimeInMillis())
                .build();
        //        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        //        notification.ledARGB = 0xffff0000;
        //        notification.ledOnMS = 1000;
        //        notification.ledOffMS = 1000;
        //        notification.when = 1483217000;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notification);
    }

    /**
     * 发送自定义的通知
     */
    public static void sendCustomNotification(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.string2Date("2017/01/24 10:20:30", "yyyy/MM/dd HH:mm:ss"));
        int notificationID = new Random().nextInt(10000000);
        //        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //        bigTextStyle.setBigContentTitle("标题");
        //        bigTextStyle.bigText("内容");
        Notification notification = new NotificationCompat.Builder(context)
                .setContentIntent(getPendingIntent(context))
                //状态栏通知
                .setTicker(context.getResources().getString(R.string.custom_notification))
                .setShowWhen(true)
                .setWhen(calendar.getTimeInMillis())
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(false)
                //替代了在外部设置的变量
                //                .setCustomContentView()

                //                .setStyle(bigTextStyle)
                .setDefaults(Notification.FLAG_SHOW_LIGHTS)
                .setLights(0xff00ff00, 1000, 1000)
                //                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        //        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        //        notification.ledARGB = 0xffff0000;
        //        notification.ledOnMS = 1000;
        //        notification.ledOffMS = 1000;
        //        notification.when = 1483217000;
        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification);
        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = context.getResources().getString(R.string.collapsed, time);
        contentView.setTextViewText(R.id.textView, text);
        /* Workaround: Need to set the content view here directly on the notification.
         * NotificationCompatBuilder contains a bug that prevents this from working on platform
         * versions HoneyComb.
         * See https://code.google.com/p/android/issues/detail?id=30495
         */
        notification.contentView = contentView;
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //        nm.notify(0, notification);
        nm.notify(notificationID, notification);
    }

    /**
     * 发送自定义的通知
     */
    public static void sendBigAlarmNotification(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.string2Date("2017/01/24 10:20:30", "yyyy/MM/dd HH:mm:ss"));
        int notificationID = new Random().nextInt(10000000);
        //        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //        bigTextStyle.setBigContentTitle("标题");
        //        bigTextStyle.bigText("内容");
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setShowWhen(true)
                .setWhen(calendar.getTimeInMillis())
                .setContentTitle("BigAlarmNotification")
                .setContentText("BigAlarmNotificationText")
                .setContentIntent(getPendingIntent(context))
                //顶部弹出来一句话
                .setTicker(context.getResources().getString(R.string.custom_notification))
                //                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setDefaults(Notification.FLAG_SHOW_LIGHTS)
                .setLights(0xffff0000, 1000, 1000)
                //                .setStyle(bigTextStyle)
                .setPriority(Notification.PRIORITY_MAX)
                .build();
        //        notification.when = 1483217000;
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //        nm.notify(0, notification);
        nm.notify(notificationID, notification);
    }


    /**
     * XML自定义的通知
     */
    public static void sendXMLCustomNotification(Context context) {
        int notificationID = new Random().nextInt(10000000);

        //        RemoteViews contenView = new RemoteViews(context.getPackageName(),R.layout);

        Notification notification = new NotificationCompat.Builder(context)
                .setContentIntent(getPendingIntent(context))
                .setTicker(context.getResources().getString(R.string.custom_notification))
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(false)
                //                .setStyle(bigTextStyle)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .build();

        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification);
        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = context.getResources().getString(R.string.collapsed, time);
        contentView.setTextViewText(R.id.textView, text);
        /* Workaround: Need to set the content view here directly on the notification.
         * NotificationCompatBuilder contains a bug that prevents this from working on platform
         * versions HoneyComb.
         * See https://code.google.com/p/android/issues/detail?id=30495
         */
        notification.contentView = contentView;
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //        nm.notify(0, notification);
        nm.notify(notificationID, notification);
    }

    private static PendingIntent getPendingIntent(Context context) {
        //        Intent intent = new Intent(Intent.ACTION_VIEW,
        //                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        //        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
