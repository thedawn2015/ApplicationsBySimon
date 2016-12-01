package com.simon.simple;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.animator.AnimatorActivity;
import com.simon.simple.async.AsyncActivity;
import com.simon.simple.db.DBActivity;
import com.simon.simple.dialog.DialogActivity;
import com.simon.simple.download.DownloadActivity;
import com.simon.simple.keyboard.KeyBoardActivity;
import com.simon.simple.nati.FullscreenActivity;
import com.simon.simple.nati.LoginActivity;
import com.simon.simple.nati.NavigationActivity;
import com.simon.simple.nati.ScrollingActivity;
import com.simon.simple.nati.SettingsActivity;
import com.simon.simple.nati.TabActivity;
import com.simon.simple.notify.NotificationActivity;
import com.simon.simple.polling.PollingServiceActivity;
import com.simon.simple.progress.ProgressActivity;
import com.simon.simple.recyclerview.RecyclerViewActivity;
import com.simon.simple.rx.RxActivity;
import com.simon.simple.telephone.TelephoneActivity;
import com.simon.simple.third_login.ThirdLoginActivity;
import com.simon.simple.titlebar.TitleBarActivity;
import com.simon.simple.toolbar.ToolbarActivity;
import com.simon.simple.webview.WebViewActivity;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatService;
import com.tencent.stat.common.StatConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
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
    @BindView (R.id.simple_btn_to_fullscreen)
    Button simpleBtnToFullscreen;
    @BindView (R.id.simple_btn_to_login)
    Button simpleBtnToLogin;
    @BindView (R.id.simple_btn_to_navigation)
    Button simpleBtnToNavigation;
    @BindView (R.id.simple_btn_to_scrolling)
    Button simpleBtnToScrolling;
    @BindView (R.id.simple_btn_to_setting)
    Button simpleBtnToSetting;
    @BindView (R.id.simple_btn_to_tab)
    Button simpleBtnToTab;
    @BindView (R.id.simple_btn_to_async)
    Button simpleBtnToAsync;
    @BindView (R.id.simple_btn_to_toolbar)
    Button simpleBtnToToolbar;
    @BindView (R.id.simple_btn_to_dialog)
    Button simpleBtnToDialog;
    @BindView (R.id.simple_btn_to_webview)
    Button simpleBtnToWebview;
    @BindView (R.id.simple_btn_to_third_login)
    Button simpleBtnToThirdLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "6Oi7On587s3aIG542lk3Rw7D");

        try {
            StatService.startStatService(this, "A6WA4JVMD33X", StatConstants.VERSION);
        } catch (MtaSDkException e) {
            e.printStackTrace();
        }
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
            R.id.simple_btn_to_key, R.id.simple_btn_to_fullscreen, R.id.simple_btn_to_login,
            R.id.simple_btn_to_navigation, R.id.simple_btn_to_scrolling, R.id.simple_btn_to_setting,
            R.id.simple_btn_to_tab, R.id.simple_btn_to_async, R.id.simple_btn_to_toolbar, R.id.simple_btn_to_dialog,
            R.id.simple_btn_to_webview,R.id.simple_btn_to_third_login})
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
                RxActivity.launch(MainActivity.this);
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
            case R.id.simple_btn_to_fullscreen:
                FullscreenActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_login:
                LoginActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_navigation:
                NavigationActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_scrolling:
                ScrollingActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_setting:
                SettingsActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_tab:
                TabActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_async:
                AsyncActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_toolbar:
                ToolbarActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_dialog:
                DialogActivity.launch(MainActivity.this);
                break;
            case R.id.simple_btn_to_webview:
                WebViewActivity.launch(MainActivity.this, null);
                break;
            case R.id.simple_btn_to_third_login:
                ThirdLoginActivity.launch(MainActivity.this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PushManager.stopWork(getApplicationContext());
    }
}
