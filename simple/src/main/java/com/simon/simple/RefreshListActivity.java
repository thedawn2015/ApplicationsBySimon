package com.simon.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.simon.simple.base.BaseActivity;
import com.simon.simple.view.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshListActivity extends BaseActivity {
    public static final String TAG = RefreshListActivity.class.getSimpleName();

    @BindView(R.id.activity_swipe_refresh_layout)
    SwipeRefreshLayout activitySwipeRefreshLayout;
    @BindView(R.id.activity_recycler_view)
    RecyclerView activityRecyclerView;

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

        assignViews();
        refreshData();
    }

    private void assignViews() {
        showProgressDialog(RefreshListActivity.this, "正在加载中...");

        initTitleBar(RefreshListActivity.this, "下拉刷新页面", "菜单", new TitleBarView.OnTitleBarClickListener() {
            @Override
            public void onMenuClick() {
                Toast.makeText(RefreshListActivity.this, "点击了菜单", Toast.LENGTH_SHORT).show();
            }
        });

        activitySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void refreshData() {

    }
}
