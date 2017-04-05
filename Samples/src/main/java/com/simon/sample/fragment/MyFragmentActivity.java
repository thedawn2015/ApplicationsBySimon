package com.simon.sample.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

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
    @BindView(R.id.tab_horizontal_scroll)
    HorizontalScrollView tabHorizontalScroll;
    @BindView(R.id.tab_view_processing)
    View tabViewProcessing;
    @BindView(R.id.tab_linear_processing)
    LinearLayout tabLinearProcessing;
    @BindView(R.id.tab_view_exception)
    View tabViewException;
    @BindView(R.id.tab_linear_exception)
    LinearLayout tabLinearException;
    @BindView(R.id.tab_view_unaudited)
    View tabViewUnaudited;
    @BindView(R.id.tab_linear_unaudited)
    LinearLayout tabLinearUnaudited;
    @BindView(R.id.tab_view_validation)
    View tabViewValidation;
    @BindView(R.id.tab_linear_validation)
    LinearLayout tabLinearValidation;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;
    private int checkdedPosition;

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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: ");
                checkdedPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: position=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged: state=" + state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    setCheckedTab(checkdedPosition);
                }
            }
        });
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new AFragment());
        fragmentList.add(new BFragment());
        fragmentList.add(new CFragment());
        fragmentList.add(new DFragment());
    }

    private void setCheckedTab(int index) {
        initTab();
        switch (index) {
            case 0:
                tabViewProcessing.setVisibility(View.VISIBLE);
                break;
            case 1:
                tabViewException.setVisibility(View.VISIBLE);
                break;
            case 2:
                tabViewUnaudited.setVisibility(View.VISIBLE);
                break;
            case 3:
                tabViewValidation.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initTab() {
        tabViewProcessing.setVisibility(View.GONE);
        tabViewException.setVisibility(View.GONE);
        tabViewUnaudited.setVisibility(View.GONE);
        tabViewValidation.setVisibility(View.GONE);
    }
}
