package com.simon.cardsgame;

import android.os.Bundle;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.cardsgame.activity.GameCenterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc：首页
 * author：simon
 * date：2017/2/16
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_btn_to_center)
    Button mainBtnToCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        assignViews();
        bindData();
        refreshViews();
    }

    @Override
    public void assignViews() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void bindData() {

    }

    @Override
    public void refreshViews() {

    }

    @OnClick(R.id.main_btn_to_center)
    public void onClick() {
        GameCenterActivity.launchToActivity(this);
    }
}
