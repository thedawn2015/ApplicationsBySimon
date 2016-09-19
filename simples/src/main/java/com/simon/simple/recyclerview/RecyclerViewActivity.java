package com.simon.simple.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simon.simple.R;
import com.simon.simple.base.util.ToastUtil;
import com.simon.simple.recyclerview.base.OnItemClickListener;
import com.simon.simple.recyclerview.customer.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {
    public static String TAG = RecyclerViewActivity.class.getSimpleName();
    @BindView (R.id.recycler_view_name)
    RecyclerView recyclerViewName;

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
        adapter.setmOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(String itemValue, int viewID, int position) {
                ToastUtil.showShort(RecyclerViewActivity.this, itemValue);
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
