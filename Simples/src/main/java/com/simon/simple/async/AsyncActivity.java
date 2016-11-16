package com.simon.simple.async;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AsyncActivity extends BaseActivity {
    public static String TAG = AsyncActivity.class.getSimpleName();
    @BindView (R.id.async_btn_start)
    Button asyncBtnStart;
    @BindView (R.id.async_progress)
    ProgressBar asyncProgress;
    @BindView (R.id.async_text_progress)
    TextView asyncTextProgress;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AsyncActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.async_btn_start)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.async_btn_start:
                ProgressBarAsyncTask progressBarAsyncTask = new ProgressBarAsyncTask(asyncTextProgress, asyncProgress);
                progressBarAsyncTask.execute();
                break;
        }
    }

    /**
     * 第一个参数
     */
    public class ProgressBarAsyncTask extends AsyncTask<Integer, Integer, String> {
        private TextView textView;
        private ProgressBar progressBar;

        public ProgressBarAsyncTask(TextView textView, ProgressBar progressBar) {
            super();
            this.textView = textView;
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute: 异步开始！");
            showProgress("正在准备数据...", false);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int i;
            for (i = 10; i <= 100; i += 10) {
                Log.i(TAG, "doInBackground: i=" + i);
                publishProgress(i, 100);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String str = "数据处理完毕";
            Log.i(TAG, "doInBackground: String=" + str);
            return str;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "onProgressUpdate: 异步进行中...");
            showProgress("进度为 " + values[0] + " / " + values[1], false);
            textView.setText(values[0] + " / " + values[1]);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "onPostExecute: 异步完成");
            dismissProgress();
            textView.setText(s);
        }
    }
}
