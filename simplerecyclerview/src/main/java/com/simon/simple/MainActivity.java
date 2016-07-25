package com.simon.simple;

import android.os.Bundle;
import android.view.View;

import com.simon.simple.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump(View view) {
        RefreshListActivity.launch(MainActivity.this);
    }
}
