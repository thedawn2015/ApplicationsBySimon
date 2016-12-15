package com.simon.sample.titlebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.LogUtil;
import com.simon.sample.R;
import com.simon.sample.titlebar.fragment.CenterFragment;
import com.simon.sample.titlebar.fragment.LeftFragment;
import com.simon.sample.titlebar.fragment.RightFragment;
import com.simon.sample.titlebar.viewContainer.TitleBarOneContainer;
import com.simon.sample.titlebar.viewContainer.TitleBarThreeContainer;
import com.simon.sample.titlebar.viewContainer.TitleBarTwoContainer;

public class TitleBarActivity extends BaseActivity {
    public static String TAG = TitleBarActivity.class.getSimpleName();

    TitleBarOneContainer titlebarOneContainer;
    TitleBarTwoContainer titleBarTwoContainer;
    TitleBarThreeContainer titleBarThreeContainer;

    FrameLayout content;

    //    LeftFragment leftFragment;
    //    CenterFragment centerFragment;
    //    RightFragment rightFragment;

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
        initTitlebarThree();

        assignViews();
        initFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, LeftFragment.getInstance(), LeftFragment.TAG)
                .commit();
    }

    private void initFragment() {
        //        leftFragment = new LeftFragment();
        //        centerFragment = new CenterFragment();
        //        rightFragment = new RightFragment();
    }

    private void assignViews() {
        content = (FrameLayout) findViewById(R.id.content);
    }

    private void initTitlebarThree() {
        titleBarThreeContainer = new TitleBarThreeContainer(this);
        titleBarThreeContainer.setTitleAndMenu("左边标题", "中间标题", "右边标题", "菜单", new TitleBarThreeContainer.OnTitlebarThreeListener() {
            @Override
            public void onLeftButtonClick() {
                LogUtil.i(TAG, "onLeftButtonClick: 3");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, LeftFragment.getInstance(), LeftFragment.TAG)
                        .commit();
            }

            @Override
            public void onCenterButtonClick() {
                LogUtil.i(TAG, "onCenterButtonClick: 3 = " + 3);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, CenterFragment.getInstance(), CenterFragment.TAG)
                        .commit();
            }

            @Override
            public void onRightButtonClick() {
                LogUtil.i(TAG, "onRightButtonClick: 3 = " + 3);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, RightFragment.getInstance(), RightFragment.TAG)
                        .commit();
            }

            @Override
            public void onMenuClick() {
                LogUtil.i(TAG, "onMenuClick: 3 = " + 3);
            }
        });
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
