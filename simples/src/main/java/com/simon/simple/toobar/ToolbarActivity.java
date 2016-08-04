package com.simon.simple.toobar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.simple.R;
import com.simon.simple.toobar.viewContainer.ToolbarOneContainer;

public class ToolbarActivity extends AppCompatActivity {
    public static String TAG = ToolbarActivity.class.getSimpleName();

    ToolbarOneContainer toolbarOneContainer;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ToolbarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        initToolbarOne();
    }

    private void initToolbarOne() {
        toolbarOneContainer = new ToolbarOneContainer(this);
        toolbarOneContainer.setTitle("这是一个标题的ToolBar");
        toolbarOneContainer.setMenu("菜单", null);
    }
}
