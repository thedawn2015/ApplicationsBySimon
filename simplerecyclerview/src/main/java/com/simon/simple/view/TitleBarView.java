package com.simon.simple.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.simple.R;

/**
 * Created by TheDawn on 2016/7/15.
 */
public class TitleBarView implements View.OnClickListener {

    private Activity activity;
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvMenu;
    private OnTitleBarClickListener onTitleBarClickListener;

    public TitleBarView(Activity activity) {
        this.activity = activity;
        assignViews();
    }

    private void assignViews() {
        ivBack = (ImageView) activity.findViewById(R.id.fragment_title_bar_iv_back);
        tvTitle = (TextView) activity.findViewById(R.id.fragment_title_bar_tv_title);
        tvMenu = (TextView) activity.findViewById(R.id.fragment_title_bar_tv_menu);

        ivBack.setOnClickListener(TitleBarView.this);
        tvMenu.setOnClickListener(TitleBarView.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_title_bar_iv_back:
                activity.onBackPressed();
                break;
            case R.id.fragment_title_bar_tv_menu:
                if (onTitleBarClickListener != null) {
                    onTitleBarClickListener.onMenuClick();
                }
                break;
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setMenu(String menu) {
        tvMenu.setText(menu);
    }

    public void setOnTitleBarClickListener(OnTitleBarClickListener onTitleBarClickListener) {
        this.onTitleBarClickListener = onTitleBarClickListener;
    }

    public interface OnTitleBarClickListener {
        void onMenuClick();
    }
}
