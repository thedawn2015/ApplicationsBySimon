package com.simon.simple.progress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.simon.baseandroid.util.BaseActivity;
import com.simon.simple.R;

public class ProgressActivity extends BaseActivity {
    public static String TAG = ProgressActivity.class.getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ProgressActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }
}
