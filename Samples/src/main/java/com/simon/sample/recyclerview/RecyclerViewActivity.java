package com.simon.sample.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.ToastUtil;
import com.simon.sample.R;
import com.simon.sample.recyclerview.base.OnItemClickListener;
import com.simon.sample.recyclerview.customer.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc: RecyclerView
 * author: xw
 * time: 2016/12/14
 */
public class RecyclerViewActivity extends BaseActivity {
    public static String TAG = RecyclerViewActivity.class.getSimpleName();
    @BindView(R.id.recycler_view_name)
    RecyclerView recyclerViewName;
    @BindView(R.id.recycler_view_pager)
    ViewPager recyclerViewPager;

    private List<String> strList;
    private MyAdapter adapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RecyclerViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        init();
        assignView();
    }

    private void assignView() {
        adapter = new MyAdapter();
        recyclerViewName.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        recyclerViewName.setAdapter(adapter);

        adapter.addItems(strList);
        adapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(String itemValue, int viewID, int position) {
                ToastUtil.showShort(RecyclerViewActivity.this, itemValue);
            }

            @Override
            public void onItemLongClick(String itemValue, int viewID, int position) {

            }
        });
    }

    private void init() {
        strList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            strList.add("name+" + i);
        }
    }
}
