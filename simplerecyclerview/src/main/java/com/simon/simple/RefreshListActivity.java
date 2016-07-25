package com.simon.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.simon.simple.adapter.RecyclerViewAdapter;
import com.simon.simple.base.BaseActivity;
import com.simon.simple.view.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshListActivity extends BaseActivity {
    public static final String TAG = RefreshListActivity.class.getSimpleName();

    @BindView (R.id.activity_swipe_refresh_layout)
    SwipeRefreshLayout activitySwipeRefreshLayout;
    @BindView (R.id.activity_recycler_view)
    RecyclerView activityRecyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;
    private List<String> adapterList;
    private int intTag = 1;

    public static void launchForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, RefreshListActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RefreshListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list);
        ButterKnife.bind(this);

        initData();
        assignViews();
        refreshData();
    }

    private void initData() {
        adapterList = new ArrayList<>();
    }

    private void assignViews() {
        //        showProgressDialog(RefreshListActivity.this, "正在加载中...");

        initTitleBar(RefreshListActivity.this, "下拉刷新页面", "菜单", new TitleBarView.OnTitleBarClickListener() {
            @Override
            public void onMenuClick() {
                Toast.makeText(RefreshListActivity.this, "点击了菜单", Toast.LENGTH_SHORT).show();
            }
        });

        activitySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog("正在刷新数据...");
                refreshData();
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(RefreshListActivity.this);
        activityRecyclerView.setLayoutManager(manager);

        // FIXME: 2016/7/15 
        activityRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                recyclerView.getChildCount();
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(RefreshListActivity.this);
        activityRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private void refreshData() {
        adapterList.clear();
        recyclerViewAdapter.clear();
        for (int i = 0; i < 30; i++) {
            adapterList.add("这是第" + intTag + "轮，第" + i + "个");
        }
        intTag++;
        recyclerViewAdapter.addItems(adapterList);
        closeProgressDialog();
        if (activitySwipeRefreshLayout.isRefreshing()) {
            activitySwipeRefreshLayout.setRefreshing(false);
        }
    }
}
