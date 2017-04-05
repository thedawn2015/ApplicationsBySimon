package com.simon.sample.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.simon.baseandroid.widget.PagerSlidingTabStrip;
import com.simon.sample.R;
import com.simon.sample.fragment.AFragment;
import com.simon.sample.fragment.BFragment;
import com.simon.sample.fragment.CFragment;
import com.simon.sample.fragment.DFragment;
import com.simon.sample.fragment.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerTabActivity extends AppCompatActivity {
    public static final String TAG = PagerTabActivity.class.getSimpleName();

    @BindView(R.id.pager_tab_title)
    PagerSlidingTabStrip pagerTabTitle;
    @BindView(R.id.pager_tab_view_pager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PagerTabActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab);
        ButterKnife.bind(this);

        initData();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        pagerTabTitle.setViewPager(viewPager);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new AFragment());
        fragmentList.add(new BFragment());
        fragmentList.add(new CFragment());
        fragmentList.add(new DFragment());

        titleList = new ArrayList<>();
        titleList.add("待处理");
        titleList.add("近7天设备异常");
        titleList.add("近7天资料未审核");
        titleList.add("上线验证统计");
    }
}
