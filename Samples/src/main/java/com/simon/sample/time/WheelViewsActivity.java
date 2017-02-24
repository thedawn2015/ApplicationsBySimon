package com.simon.sample.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.baseandroid.widget.WheelViewS;
import com.simon.sample.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc: wheel view
 * author: xw
 * time: 2017/2/24
 */
public class WheelViewsActivity extends AppCompatActivity {
    public static final String TAG = WheelViewsActivity.class.getSimpleName();

    @BindView(R.id.wheels1)
    WheelViewS wheels1;
    @BindView(R.id.wheels2)
    WheelViewS wheels2;
    @BindView(R.id.wheels3)
    WheelViewS wheels3;

    private ArrayList<String> list;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, WheelViewsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_views);
        ButterKnife.bind(this);

        initData();
        assignViews();
    }

    private void assignViews() {
        wheels1.setData(list);
        wheels1.setOnSelectListener(new WheelViewS.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {

            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        wheels2.setData(list);
        wheels3.setData(list);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i + "号");
        }
    }
}
