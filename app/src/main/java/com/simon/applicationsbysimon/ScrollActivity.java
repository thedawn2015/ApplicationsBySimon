package com.simon.applicationsbysimon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.applicationsbysimon.base.BaseActivity;
import com.simon.applicationsbysimon.widget.ScrollLinearLayout;

public class ScrollActivity extends BaseActivity {

    ScrollLinearLayout scrollLinearLayout;
    Button hideView;
    Button showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        scrollLinearLayout = (ScrollLinearLayout) findViewById(R.id.my_scroll_view);
        hideView = (Button) findViewById(R.id.hide_view);
        showView = (Button) findViewById(R.id.show_view);

        hideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollLinearLayout.hideView();
            }
        });
        showView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollLinearLayout.showView();
            }
        });
    }

}
