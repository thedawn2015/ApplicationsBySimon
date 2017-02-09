package com.simon.sample.notify;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.ToastUtil;
import com.simon.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity {
    public static String TAG = NotificationActivity.class.getSimpleName();

    public static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE_CALL = 100;

    @BindView(R.id.notify_btn_basic)
    Button notifyBtnBasic;
    @BindView(R.id.notify_btn_custom)
    Button notifyBtnCustom;
    @BindView(R.id.notify_btn_big_custom)
    Button notifyBtnBigCustom;
    @BindView(R.id.notify_btn_more_custom)
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

        checkPermission();
    }

    private void checkPermission() {
        int isChecked = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
        if (isChecked == PackageManager.PERMISSION_GRANTED) {
            callPhone();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE},
                    REQUEST_CODE_CALL);
        }
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CALL:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    ToastUtil.showShort(this, "Permission Denied");
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick({R.id.notify_btn_basic, R.id.notify_btn_custom, R.id.notify_btn_big_custom, R.id.notify_btn_more_custom})
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
