package com.simon.sample.time;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.simon.baseandroid.BaseActivity;
import com.simon.sample.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
    @BindView(R.id.time_pick_view)
    PickerView timePickView;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TimeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);

        assignViews();
    }

    private void assignViews() {
        List<String> list = new ArrayList<>();
        list.add("星期1");
        list.add("星期2");
        list.add("星期3");
        list.add("星期4");
        list.add("星期5");
        list.add("星期6");
        list.add("星期7");
        timePickView.setData(list);
        timePickView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                Log.i(TAG, "onSelect: text=" + text);
            }
        });
    }

    @OnClick({R.id.date_btn_pick, R.id.time_btn_pick})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_btn_pick:
                createDateDialog();
                break;
            case R.id.time_btn_pick:
                createTimeDialog();
                break;
        }
    }

    private void createTimeDialog() {
//        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(TimeActivity.this, R.style.Theme_Dialog_Time,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Log.i(TAG, "onTimeSet: hour=" + hour + "; minute=" + minute);
                    }
                }, hour, minute, true);
//        timePickerDialog.setTitle("时间选择");
        timePickerDialog.show();
    }

    private void createDateDialog() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar
        DatePickerDialog datePickerDialog = new DatePickerDialog(TimeActivity.this, R.style.Theme_Dialog_Date,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Log.i(TAG, "onTimeSet: year=" + year + "; month=" + month + "; day=" + day);
                    }
                }, year, month, day);
//        datePickerDialog.setTitle("日期选择");
        datePickerDialog.show();
    }
}
