package com.simon.simple.toobar.viewContainer;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simon.simple.R;

/**
 * 自定义的TitleBar
 * 只有一个title，无点击响应
 * Created by xw on 2016/8/4.
 */
public class TitleBarOneContainer {
    public static String TAG = TitleBarOneContainer.class.getSimpleName();

    private Activity activity;
    private OnToolbarOneListener onToolbarOneListener;
    FrameLayout titlebarOneFlBack;
    TextView titlebarOneTvTitle;
    LinearLayout titlebarOneLlMenu;
    TextView titlebarOneTvMenu;

    public TitleBarOneContainer(Activity activity) {
        this.activity = activity;
        assignViews(activity);
    }

    private void assignViews(Activity activity) {
        titlebarOneFlBack = (FrameLayout) activity.findViewById(R.id.titlebar_one_fl_back);
        titlebarOneTvTitle = (TextView) activity.findViewById(R.id.titlebar_one_tv_title);
        titlebarOneLlMenu = (LinearLayout) activity.findViewById(R.id.titlebar_one_ll_menu);
        titlebarOneTvMenu = (TextView) activity.findViewById(R.id.titlebar_one_tv_menu);

        titlebarOneFlBack.setOnClickListener(onClickListener);
        titlebarOneLlMenu.setOnClickListener(onClickListener);
    }

    /**
     * set title and menu
     *
     * @param title
     * @param menu
     * @param onToolbarOneListener
     */
    public void setTitleAndMenu(String title, String menu, OnToolbarOneListener onToolbarOneListener) {
        titlebarOneTvTitle.setText(title);
        this.onToolbarOneListener = onToolbarOneListener;
        if (!TextUtils.isEmpty(menu)) {
            titlebarOneLlMenu.setVisibility(View.VISIBLE);
            titlebarOneTvMenu.setText(menu);
        }
    }

    /**
     * listener 实例
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_one_fl_back:
                    activity.onBackPressed();
                    break;
                case R.id.titlebar_one_ll_menu:
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
