package com.simon.simple.toobar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.simple.R;
import com.simon.simple.toobar.viewContainer.TitleBarOneContainer;

public class TitleBarActivity extends AppCompatActivity {
    public static String TAG = TitleBarActivity.class.getSimpleName();

    TitleBarOneContainer toolbarOneContainer;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TitleBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlebar);

        initToolbarOne();
    }

    private void initToolbarOne() {
        toolbarOneContainer = new TitleBarOneContainer(this);
        toolbarOneContainer.setTitle("这是一个标题的ToolBar");
        toolbarOneContainer.setMenu("菜单", null);
    }
}
