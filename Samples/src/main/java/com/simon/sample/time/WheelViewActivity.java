package com.simon.sample.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.simon.baseandroid.widget.WheelView;
import com.simon.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WheelViewActivity extends AppCompatActivity {
    public static final String TAG = WheelViewActivity.class.getSimpleName();
    @BindView(R.id.number_picker)
    WheelView numberPicker;
    @BindView(R.id.number_picker2)
    WheelView numberPicker2;
    @BindView(R.id.number_picker3)
    WheelView numberPicker3;
    @BindView(R.id.picker_btn_sure)
    Button pickerBtnSure;


    private List<String> strings;


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, WheelViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        ButterKnife.bind(this);

        initData();
        assignViews();
    }

    private void assignViews() {
        numberPicker.setItems(strings);
        numberPicker.setOffset(1);
        numberPicker.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.i(TAG, "onSelected: selectedIndex=" + selectedIndex + ": " + item);
            }
        });
        numberPicker2.setItems(strings);
        numberPicker2.setOffset(2);
        numberPicker2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.i(TAG, "onSelected: selectedIndex=" + selectedIndex + ": " + item);
            }
        });
        numberPicker3.setItems(strings);
        numberPicker3.setOffset(3);
        numberPicker3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.i(TAG, "onSelected: selectedIndex=" + selectedIndex + ": " + item);
            }
        });
    }

    private void initData() {
        strings = new ArrayList<>();
        strings.add("星期1");
        strings.add("星期2");
        strings.add("星期3");
        strings.add("星期4");
        strings.add("星期5");
        strings.add("星期6");
        strings.add("星期7");
    }
}
