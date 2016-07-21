package com.simon.autoactionbar;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mainToolbar;
    View header;
    private ListView mainListView;
    private float mStartY = 0, mLastY = 0, mLastDeltaY;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        assignViews();
    }

    private void assignViews() {
//        mainToolbar = (Toolbar) findViewById(R.id.main_tool_bar);
        //        setSupportActionBar(mainToolbar);

        mainListView = (ListView) findViewById(R.id.main_list_view);

        header = LayoutInflater.from(this).inflate(R.layout.layout_header, mainListView,false);
        mainListView.addHeaderView(header);

        mainListView.setAdapter(adapter);

        /*mainListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final float y = event.getY();
                float translationY = mainToolbar.getTranslationY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "Down");
                        mStartY = y;
                        mLastY = mStartY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float mDeltaY = y - mLastY;

                        float newTansY = translationY + mDeltaY;
                        if (newTansY <= 0 && newTansY >= -mainToolbar.getHeight()) {
                            mainToolbar.setTranslationY(newTansY / 3);
                        }
                        mLastY = y;
                        mLastDeltaY = mDeltaY;
                        Log.i(TAG, "Move");
                        break;
                    case MotionEvent.ACTION_UP:
                        ObjectAnimator animator = null;
                        Log.d(TAG, "mLastDeltaY=" + mLastDeltaY);
                        if (mLastDeltaY < 0 && mainListView.getFirstVisiblePosition() > 1) {
                            Log.v(TAG, "listView.first=" + mainListView.getFirstVisiblePosition());
                            animator = ObjectAnimator.ofFloat(mainToolbar, "translationY", mainToolbar.getTranslationY(), -mainToolbar.getHeight());
                        } else {
                            animator = ObjectAnimator.ofFloat(mainToolbar, "translationY", mainToolbar.getTranslationY(), 0);
                        }
                        animator.setDuration(100);
                        animator.start();
                        animator.setInterpolator(AnimationUtils.loadInterpolator(MainActivity.this, android.R.interpolator.linear));
                        Log.i(TAG, "Up");
                        break;
                }
                return false;
            }
        });*/
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
