package com.simon.sample.toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.simon.baseandroid.BaseActivity;
import com.simon.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc: toolbar
 * author: xiao
 * time: 2016/11/16
 */
public class ToolbarActivity extends BaseActivity {
    public static String TAG = ToolbarActivity.class.getSimpleName();

    @BindView (R.id.toolbar_drawer_layout)
    DrawerLayout toolbarDrawerLayout;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ToolbarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.bind(this);

        setToolbar1();
    }

    private void setToolbar1() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //设置导航栏图标
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        //设置app logo
        toolbar.setLogo(null);
        //设置主标题
        toolbar.setTitle("");
        //设置子标题
        toolbar.setSubtitle("");

        /*toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(ToolbarActivity.this, "第一个", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(ToolbarActivity.this, "第二个", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(ToolbarActivity.this, "第三个", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(ToolbarActivity.this, "第四个", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });*/
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.action_search) {
            Toast.makeText(ToolbarActivity.this, "第一个", Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.action_notification) {
            Toast.makeText(ToolbarActivity.this, "第二个", Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.action_item1) {
            Toast.makeText(ToolbarActivity.this, "第三个", Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.action_item2) {
            Toast.makeText(ToolbarActivity.this, "第四个", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
