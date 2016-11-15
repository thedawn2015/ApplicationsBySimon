package com.simon.simple.async;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
            case R.id.simple_btn_to_draw:
                break;
        }
    }

    public class ProgressBarAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }
    }
}
