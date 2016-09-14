package com.simon.simple.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.simon.simple.MainActivity;
import com.simon.simple.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {
    public static String TAG = NotificationActivity.class.getSimpleName();

    public static final int NOTIFICATION_ID = 1;

    @BindView (R.id.notify_btn_basic)
    Button notifyBtnBasic;
    @BindView (R.id.notify_btn_custom)
    Button notifyBtnCustom;
    @BindView (R.id.notify_btn_big_custom)
    Button notifyBtnBigCustom;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, NotificationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
    }

    /**
     * Send a sample notification using the NotificationCompat API.
     * <p>
     * https://developer.android.com/samples/BasicNotifications/src/com.example.android.basicnotifications/MainActivity.html
     */
    private void sendBasicNotification() {
        int notificationID = new Random().nextInt(10000000);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.notification_icon_large))
                // Set the intent that will fire when the user taps the notification.
                .setContentIntent(getPendingIntent())
                .setAutoCancel(false)
                .setContentTitle("BasicNotifications Sample")
                .setContentText("Time to learn about notifications!")
                .setSubText("Tap to view documentation about notifications.")
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notification);
    }

    /**
     * 发送自定义的通知
     */
    private void sendBigAlarmNotification() {
        int notificationID = new Random().nextInt(10000000);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("标题");
        bigTextStyle.bigText("内容");
        Notification notification = new android.support.v7.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(getPendingIntent())
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setTicker(getResources().getString(R.string.custom_notification))
//                .setAutoCancel(true)
                .setStyle(bigTextStyle)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .build();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //        nm.notify(0, notification);
        nm.notify(notificationID, notification);
    }

    /**
     * 发送自定义的通知
     */
    private void sendCustomNotification() {
        int notificationID = new Random().nextInt(10000000);
        //        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //        bigTextStyle.setBigContentTitle("标题");
        //        bigTextStyle.bigText("内容");
        Notification notification = new NotificationCompat.Builder(this)
                .setContentIntent(getPendingIntent())
                .setTicker(getResources().getString(R.string.custom_notification))
                .setSmallIcon(R.mipmap.notification_icon_small)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(false)
                //                .setStyle(bigTextStyle)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);
        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = getResources().getString(R.string.collapsed, time);
        contentView.setTextViewText(R.id.textView, text);
        /* Workaround: Need to set the content view here directly on the notification.
         * NotificationCompatBuilder contains a bug that prevents this from working on platform
         * versions HoneyComb.
         * See https://code.google.com/p/android/issues/detail?id=30495
         */
        notification.contentView = contentView;
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //        nm.notify(0, notification);
        nm.notify(notificationID, notification);
    }

    private PendingIntent getPendingIntent() {
        //        Intent intent = new Intent(Intent.ACTION_VIEW,
        //                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        //        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @OnClick ({R.id.notify_btn_basic, R.id.notify_btn_custom, R.id.notify_btn_big_custom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notify_btn_basic:
                sendBasicNotification();
                break;
            case R.id.notify_btn_custom:
                sendCustomNotification();
                break;
            case R.id.notify_btn_big_custom:
                sendBigAlarmNotification();
                break;
        }
    }
}
