package com.simon.simple.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.simple.view.TitleBarView;

public class BaseActivity extends AppCompatActivity {

    private TitleBarView titleBarView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initTitleBar(Activity activity) {
        if (titleBarView == null) {
            titleBarView = new TitleBarView(activity);
        }
    }

    public void initTitleBar(Activity activity, String title, String menu,
                             TitleBarView.OnTitleBarClickListener onTitleBarClickListener) {
        initTitleBar(activity);
        titleBarView.setTitle(title);
        titleBarView.setMenu(menu);
        titleBarView.setOnTitleBarClickListener(onTitleBarClickListener);
    }

    public void showProgressDialog(String message) {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        if (!progressDialog.isShowing()) {
            if (!isFinishing()) {
                progressDialog.show();
                progressDialog.setMessage(message);
            }
        }
    }

    public void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
