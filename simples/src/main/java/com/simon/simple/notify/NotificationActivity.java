package com.simon.simple.notify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity {
    public static String TAG = NotificationActivity.class.getSimpleName();

    public static final int NOTIFICATION_ID = 1;

    @BindView (R.id.notify_btn_basic)
    Button notifyBtnBasic;
    @BindView (R.id.notify_btn_custom)
    Button notifyBtnCustom;
    @BindView (R.id.notify_btn_big_custom)
    Button notifyBtnBigCustom;
    @BindView (R.id.notify_btn_more_custom)
    Button notifyBtnMoreCustom;

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

    @OnClick ({R.id.notify_btn_basic, R.id.notify_btn_custom, R.id.notify_btn_big_custom, R.id.notify_btn_more_custom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notify_btn_basic:
                NotificationUtil.sendBasicNotification(NotificationActivity.this);
                break;
            case R.id.notify_btn_custom:
                NotificationUtil.sendCustomNotification(NotificationActivity.this);
                break;
            case R.id.notify_btn_big_custom:
                NotificationUtil.sendBigAlarmNotification(NotificationActivity.this);
                break;
            case R.id.notify_btn_more_custom:
                NotificationUtil.sendBigAlarmNotification(NotificationActivity.this);
                break;
        }
    }
}
