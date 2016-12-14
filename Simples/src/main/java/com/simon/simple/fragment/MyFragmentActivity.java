package com.simon.simple.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;

/**
 * desc: Fragment 手动addView
 * author: xw
 * time: 2016/12/14
 */
public class MyFragmentActivity extends BaseActivity {
    public static final String TAG = MyFragmentActivity.class.getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MyFragmentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
    }
}
