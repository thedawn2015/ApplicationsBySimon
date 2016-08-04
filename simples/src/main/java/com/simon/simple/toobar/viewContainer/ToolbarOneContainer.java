package com.simon.simple.toobar.viewContainer;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.simon.simple.R;

/**
 * 自定义的ToolBar
 * 只有一个title
 * Created by xw on 2016/8/4.
 */
public class ToolbarOneContainer {
    public static String TAG = ToolbarOneContainer.class.getSimpleName();

    private Activity activity;
    private OnToolbarOneListener onToolbarOneListener;
    FrameLayout toolbarOneFlBack;
    TextView toolbarOneTvTitle;
    TextView toolbarOneTvMenu;

    public ToolbarOneContainer(Activity activity) {
        this.activity = activity;
        assignViews(activity);
    }

    private void assignViews(Activity activity) {
        toolbarOneFlBack = (FrameLayout) activity.findViewById(R.id.toolbar_one_fl_back);
        toolbarOneTvTitle = (TextView) activity.findViewById(R.id.toolbar_one_tv_title);
        toolbarOneTvMenu = (TextView) activity.findViewById(R.id.toolbar_one_tv_menu);

        toolbarOneFlBack.setOnClickListener(onClickListener);
        toolbarOneTvMenu.setOnClickListener(onClickListener);
    }

    /**
     * set title
     *
     * @param title
     */
    public void setTitle(String title) {
        toolbarOneTvTitle.setText(title);
    }

    /**
     * set menu
     *
     * @param menu
     */
    public void setMenu(String menu, OnToolbarOneListener onToolbarOneListener) {
        this.onToolbarOneListener = onToolbarOneListener;
        toolbarOneTvMenu.setVisibility(View.VISIBLE);
        toolbarOneTvMenu.setText(menu);
    }

    /**
     * listener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.toolbar_one_fl_back:
                    activity.onBackPressed();
                    break;
                case R.id.toolbar_one_tv_menu:
                    if (onToolbarOneListener != null) {
                        onToolbarOneListener.onMenuClick();
                    }
                    break;
            }
        }
    };

    public interface OnToolbarOneListener {
        void onMenuClick();
    }

}
