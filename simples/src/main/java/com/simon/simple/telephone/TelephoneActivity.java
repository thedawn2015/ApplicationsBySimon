package com.simon.simple.telephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.simple.R;
import com.simon.simple.base.util.LogUtil;
import com.simon.simple.telephone.util.TelephoneUtil;

public class TelephoneActivity extends AppCompatActivity {
    public static String TAG = TelephoneActivity.class.getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TelephoneActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);

        String info = TelephoneUtil.getUserAgentInfo(this, "MyDemo");
        LogUtil.i(TAG, "onCreate: info=" + info);
    }
}
