package com.simon.sample.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simon.baseandroid.BaseActivity;
import com.simon.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc: Fragment 手动addView
 * author: xw
 * time: 2016/12/14
 */
public class MyFragmentActivity extends BaseActivity {
    public static final String TAG = MyFragmentActivity.class.getSimpleName();
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;

    private List<Fragment> fragmentList;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MyFragmentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        ButterKnife.bind(this);

        initData();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new AFragment());
        fragmentList.add(new BFragment());
        fragmentList.add(new CFragment());
    }
}
