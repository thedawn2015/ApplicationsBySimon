package com.simon.sample.time;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc: 时间相关的控件（系统）
 * author: xw
 * time: 2017/2/17
 */
public class TimeActivity extends BaseActivity {
    public static final String TAG = TimeActivity.class.getSimpleName();

    @BindView(R.id.date_btn_pick)
    Button dateBtnPick;
    @BindView(R.id.time_btn_pick)
    Button timeBtnPick;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TimeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.date_btn_pick, R.id.time_btn_pick})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_btn_pick:
//                DatePickerDialog datePickerDialog = new DatePickerDialog(this);
                break;
            case R.id.time_btn_pick:
                break;
        }
    }
}
