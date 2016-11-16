package com.simon.simple.toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;

/**
 * desc: toolbar
 * author: xiao
 * time: 2016/11/16
 */
public class ToolbarActivity extends BaseActivity {
    public static String TAG = ToolbarActivity.class.getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ToolbarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
    }
}
