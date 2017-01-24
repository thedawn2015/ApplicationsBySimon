package com.simon.sample.amap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.simon.baseandroid.BaseActivity;
import com.simon.sample.R;

/**
 * desc: 高德地图
 * author: xw
 * time: 2017/1/3
 */
public class AMapActivity extends BaseActivity {
    public static final String TAG = AMapActivity.class.getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AMapActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
    }
}
