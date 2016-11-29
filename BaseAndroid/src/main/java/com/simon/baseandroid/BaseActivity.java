package com.simon.baseandroid;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.baseandroid.broadcastreceiver.NetConnectChangeReceiver;
import com.tencent.stat.StatService;

public class BaseActivity extends AppCompatActivity {
    public static String TAG = BaseActivity.class.getSimpleName();

    private ProgressDialog progressDialog;

    private NetConnectChangeReceiver netConnectChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        netConnectChangeReceiver = new NetConnectChangeReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置为竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        StatService.onResume(this);

        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);

        unregisterReceiver();
    }

    /**
     * 添加监听
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netConnectChangeReceiver, filter);
    }

    /**
     * 取消监听
     */
    private void unregisterReceiver() {
        unregisterReceiver(netConnectChangeReceiver);
    }

    /**
     * 显示进度条
     *
     * @param content
     */
    public void showProgress(String content) {
        showProgress(content, false);
    }

    /**
     * 进度条
     *
     * @param content
     * @param isCancelable
     */
    public void showProgress(String content, boolean isCancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(content);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
    }

    /**
     * 关闭进度条
     */
    public void dismissProgress() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}
