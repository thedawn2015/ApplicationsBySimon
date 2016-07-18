package com.simon.applicationsbysimon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.applicationsbysimon.base.BaseActivity;
import com.simon.applicationsbysimon.widget.MyScrollView;

public class ScrollActivity extends BaseActivity {

    MyScrollView myScrollView;
    Button hideView;
    Button showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        myScrollView = (MyScrollView) findViewById(R.id.my_scroll_view);
        hideView = (Button) findViewById(R.id.hide_view);
        showView = (Button) findViewById(R.id.show_view);

        hideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myScrollView.hideView();
            }
        });
        showView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myScrollView.showView();
            }
        });
    }

}
