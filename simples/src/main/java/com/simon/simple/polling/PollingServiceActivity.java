package com.simon.simple.polling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simon.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PollingServiceActivity extends AppCompatActivity {
    public static String TAG = PollingServiceActivity.class.getSimpleName();
    @BindView (R.id.polling_service_btn_service)
    Button pollingServiceBtnService;
    @BindView (R.id.polling_service_btn_alarm)
    Button pollingServiceBtnAlarm;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PollingServiceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_service);
        ButterKnife.bind(this);
    }

    Intent pollingIntent;

    @OnClick ({R.id.polling_service_btn_service, R.id.polling_service_btn_alarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.polling_service_btn_service:
                pollingIntent = new Intent(this, RequestService.class);
                pollingIntent.setAction(RequestService.REQUEST_SERVICE_ACTION);
                startService(pollingIntent);
                break;
            case R.id.polling_service_btn_alarm:
                PollingUtil.startPollingService(this, 1, PollingService.class, PollingService.POLLING_SERVICE_ACTION);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(pollingIntent);
        PollingUtil.stopPollingService(this, PollingService.class, PollingService.POLLING_SERVICE_ACTION);
    }
}
