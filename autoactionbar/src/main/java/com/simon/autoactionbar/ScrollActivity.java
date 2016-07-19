package com.simon.autoactionbar;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ScrollActivity extends AppCompatActivity {
    public static String TAG = ScrollActivity.class.getSimpleName();

    LinearLayout scrollLinearLayout;
    ListView scrollListView;
    private ArrayAdapter<String> adapter;

    LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        initData();
        assignViews();

    }

    float lastY;

    private void assignViews() {
        scrollLinearLayout = (LinearLayout) findViewById(R.id.scroll_linear_layout);
        scrollListView = (ListView) findViewById(R.id.scroll_list_view);

        layoutParams = (LinearLayout.LayoutParams) scrollLinearLayout.getLayoutParams();

        scrollListView.setAdapter(adapter);
        scrollListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float newY = motionEvent.getY();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = newY;
                        Log.i(TAG, "onTouch: lastY=" + lastY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float py = newY - lastY;
                        //                        if (py < 0) {
                        //                            layoutParams.topMargin = (int) py;
                        //                            scrollLinearLayout.setLayoutParams(layoutParams);
                        //                        }
                        hideTools();
                        break;
                    case MotionEvent.ACTION_UP:
                        //                        hideTools();
                        break;
                }
                return false;
            }
        });
    }

    boolean isToolsHide;

    /**
     * 显示工具栏
     */
    private void showTools() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(scrollLinearLayout, "y", (scrollLinearLayout.getY()) / 3,
                (scrollLinearLayout.getY() - scrollLinearLayout.getHeight()) / 3);
        anim.setDuration(100);
        anim.start();

        isToolsHide = false;
    }

    /**
     * 隐藏工具栏
     */
    private void hideTools() {

        Log.i(TAG, "hideTools: (scrollLinearLayout.getY())=" + (scrollLinearLayout.getY()) );
        Log.i(TAG, "hideTools: (scrollLinearLayout.getY() + scrollLinearLayout.getHeight()) =" + (scrollLinearLayout.getY() + scrollLinearLayout.getHeight()) );

        ObjectAnimator anim = ObjectAnimator.ofFloat(scrollLinearLayout, "y", (scrollLinearLayout.getY()),
                (scrollLinearLayout.getY() + scrollLinearLayout.getHeight()) );
        anim.setDuration(100);
        anim.start();

        isToolsHide = true;

    }

    private void initData() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter.add("第1条");
        adapter.add("第2条");
        adapter.add("第3条");
        adapter.add("第4条");
        adapter.add("第5条");
        adapter.add("第6条");
        adapter.add("第1条");
        adapter.add("第2条");
        adapter.add("第3条");
        adapter.add("第4条");
        adapter.add("第5条");
        adapter.add("第6条");
        adapter.add("第1条");
        adapter.add("第2条");
        adapter.add("第3条");
        adapter.add("第4条");
        adapter.add("第5条");
        adapter.add("第6条");
        adapter.add("第1条");
        adapter.add("第2条");
        adapter.add("第3条");
        adapter.add("第4条");
        adapter.add("第5条");
        adapter.add("第6条");
        adapter.add("第1条");
        adapter.add("第2条");
        adapter.add("第3条");
        adapter.add("第4条");
        adapter.add("第5条");
        adapter.add("第6条");
    }
}
