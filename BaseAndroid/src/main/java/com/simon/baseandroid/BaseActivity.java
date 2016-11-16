package com.simon.baseandroid;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.tencent.stat.StatService;

public class BaseActivity extends AppCompatActivity {
    public static String TAG = BaseActivity.class.getSimpleName();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置为竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        StatService.onResume(this);
    }

    /**
     * 显示进度条
     *
     * @param content
     */
    public void showProgress(String content) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(content);
        progressDialog.show();
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

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }
}
