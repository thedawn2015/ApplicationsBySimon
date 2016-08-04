package com.simon.simple.toobar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.simple.R;
import com.simon.simple.base.util.LogUtil;
import com.simon.simple.toobar.viewContainer.TitleBarOneContainer;
import com.simon.simple.toobar.viewContainer.TitleBarTwoContainer;

public class TitleBarActivity extends AppCompatActivity {
    public static String TAG = TitleBarActivity.class.getSimpleName();

    TitleBarOneContainer titlebarOneContainer;
    TitleBarTwoContainer titleBarTwoContainer;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TitleBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlebar);

        initTitlebarOne();
        initTitlebarTwo();
    }

    private void initTitlebarTwo() {
        titleBarTwoContainer = new TitleBarTwoContainer(this);
        titleBarTwoContainer.setTitleAndMenu("左边标题", "右边标题", "菜单", new TitleBarTwoContainer.OnTitlebarTwoListener() {
            @Override
            public void onLeftButtonClick() {
                LogUtil.i(TAG, "onLeftButtonClick: 2");
            }

            @Override
            public void onRightButtonClick() {
                LogUtil.i(TAG, "onRightButtonClick: 2");
            }

            @Override
            public void onMenuClick() {
                LogUtil.i(TAG, "onMenuClick: 2");
            }
        });
    }

    private void initTitlebarOne() {
        titlebarOneContainer = new TitleBarOneContainer(this);
        titlebarOneContainer.setTitleAndMenu("这是一个标题的ToolBar", "菜单", new TitleBarOneContainer.OnToolbarOneListener() {
            @Override
            public void onMenuClick() {
                LogUtil.i(TAG, "onMenuClick: 1");
            }
        });
    }
}
