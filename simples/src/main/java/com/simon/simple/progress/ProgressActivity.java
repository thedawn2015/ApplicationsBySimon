package com.simon.simple.progress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.simple.R;

public class ProgressActivity extends AppCompatActivity {
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
