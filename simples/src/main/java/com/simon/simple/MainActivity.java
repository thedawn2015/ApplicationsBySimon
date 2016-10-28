package com.simon.simple;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.simon.base.util.LogUtil;
import com.simon.simple.animator.AnimatorActivity;
import com.simon.simple.db.DBActivity;
import com.simon.simple.download.DownloadActivity;
import com.simon.simple.keyboard.KeyBoardActivity;
import com.simon.simple.notify.NotificationActivity;
import com.simon.simple.polling.PollingServiceActivity;
import com.simon.simple.progress.ProgressActivity;
import com.simon.simple.recyclerview.RecyclerViewActivity;
import com.simon.simple.telephone.TelephoneActivity;
import com.simon.simple.titlebar.TitleBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    @BindView (R.id.simple_btn_to_draw)
    Button simpleBtnToDraw;
    @BindView (R.id.simple_btn_to_animator)
    Button simpleBtnToAnimator;
    @BindView (R.id.simple_btn_to_titlebar)
    Button simpleBtnToTitlebar;
    @BindView (R.id.simple_btn_to_recycler_view)
    Button simpleBtnToRecyclerView;
    @BindView (R.id.simple_btn_to_retrofit)
    Button simpleBtnToRetrofit;
    @BindView (R.id.simple_btn_to_rx)
    Button simpleBtnToRx;
    @BindView (R.id.simple_btn_to_download)
    Button simpleBtnToDownload;
    @BindView (R.id.simple_btn_to_notifycation)
    Button simpleBtnToNotifycation;
    @BindView (R.id.simple_btn_to_db)
    Button simpleBtnToDb;
    @BindView (R.id.simple_btn_to_service)
    Button simpleBtnToService;
    @BindView (R.id.simple_btn_to_progress)
    Button simpleBtnToProgress;
    @BindView (R.id.simple_btn_to_telephone)
    Button simpleBtnToTelephone;
    @BindView (R.id.simple_btn_to_key)
    Button simpleBtnToKeyBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "6Oi7On587s3aIG542lk3Rw7D");

        //        getRunningService(this);
        //        getRunningTask(this);
        //        getRecentTask(this);
    }

    private void getRunningService(Context context) {
        //        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            LogUtil.i(TAG, "getRunningService: processName=" + appProcess.processName);
            /*if (appProcess.pid == pid) {
                return appProcess.processName;
            }*/
        }
    }

    private void getRunningTask(Context context) {
        //        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningTaskInfo running : activityManager.getRunningTasks(100)) {
            LogUtil.i(TAG, "getRunningTask: running=" + running.baseActivity.getClassName());
            /*if (appProcess.pid == pid) {
                return appProcess.processName;
            }*/
        }
    }

    @TargetApi (Build.VERSION_CODES.M)
    private void getRecentTask(Context context) {
        //        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
            LogUtil.i(TAG, "getRecentTask: appTask=" + appTask.getTaskInfo().topActivity.getClassName());
                /*if (appProcess.pid == pid) {
                    return appProcess.processName;
                }*/
        }
    }

    @OnClick ({R.id.simple_btn_to_draw, R.id.simple_btn_to_animator, R.id.simple_btn_to_titlebar,
            R.id.simple_btn_to_recycler_view, R.id.simple_btn_to_retrofit, R.id.simple_btn_to_rx,
            R.id.simple_btn_to_download, R.id.simple_btn_to_notifycation, R.id.simple_btn_to_db,
            R.id.simple_btn_to_service, R.id.simple_btn_to_progress, R.id.simple_btn_to_telephone,
            R.id.simple_btn_to_key})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simple_btn_to_draw:
                break;
            case R.id.simple_btn_to_notifycation:
                NotificationActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_animator:
                AnimatorActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_titlebar:
                TitleBarActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_recycler_view:
                RecyclerViewActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_retrofit:
                break;
            case R.id.simple_btn_to_rx:
                break;
            case R.id.simple_btn_to_download:
                DownloadActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_db:
                DBActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_service:
                PollingServiceActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_progress:
                ProgressActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_telephone:
                TelephoneActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_key:
                KeyBoardActivity.launch(MainActivity.this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PushManager.stopWork(getApplicationContext());
    }
}
