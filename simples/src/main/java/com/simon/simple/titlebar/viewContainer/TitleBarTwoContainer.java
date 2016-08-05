package com.simon.simple.titlebar.viewContainer;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simon.simple.R;

/**
 * 自定义的TitleBar
 * 有两个title可以切换
 * Created by xw on 2016/8/4.
 */
public class TitleBarTwoContainer {
    public static String TAG = TitleBarTwoContainer.class.getSimpleName();

    FrameLayout titlebarTwoFlBack;
    RadioButton titlebarTwoRbLeft;
    RadioButton titlebarTwoRbRight;
    RadioGroup titlebarTwoRgTitle;
    TextView titlebarTwoTvMenu;
    LinearLayout titlebarTwoLlMenu;

    private Activity activity;
    private OnTitlebarTwoListener onTitlebarTwoListener;

    public TitleBarTwoContainer(Activity activity) {
        this.activity = activity;
        assignViews(activity);
    }

    private void assignViews(Activity activity) {
        titlebarTwoFlBack = (FrameLayout) activity.findViewById(R.id.titlebar_two_fl_back);
        titlebarTwoRgTitle = (RadioGroup) activity.findViewById(R.id.titlebar_two_rg_title);
        titlebarTwoRbLeft = (RadioButton) activity.findViewById(R.id.titlebar_two_rb_left);
        titlebarTwoRbRight = (RadioButton) activity.findViewById(R.id.titlebar_two_rb_right);
        titlebarTwoTvMenu = (TextView) activity.findViewById(R.id.titlebar_two_tv_menu);
        titlebarTwoLlMenu = (LinearLayout) activity.findViewById(R.id.titlebar_two_ll_menu);

        titlebarTwoFlBack.setOnClickListener(onClickListener);
        titlebarTwoLlMenu.setOnClickListener(onClickListener);
        titlebarTwoRgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int buttonId = radioGroup.getCheckedRadioButtonId();
                switch (buttonId) {
                    case R.id.titlebar_two_rb_left:
                        if (onTitlebarTwoListener != null) {
                            onTitlebarTwoListener.onLeftButtonClick();
                        }
                        break;
                    case R.id.titlebar_two_rb_right:
                        if (onTitlebarTwoListener != null) {
                            onTitlebarTwoListener.onRightButtonClick();
                        }
                        break;
                }
            }
        });
    }

    /**
     * set title and menu
     *
     * @param leftTitle
     * @param rightTitle
     */
    public void setTitleAndMenu(String leftTitle, String rightTitle, String menu, OnTitlebarTwoListener onTitlebarTwoListener) {
        titlebarTwoRbLeft.setText(leftTitle);
        titlebarTwoRbRight.setText(rightTitle);
        this.onTitlebarTwoListener = onTitlebarTwoListener;
        if (!TextUtils.isEmpty(menu)) {
            titlebarTwoLlMenu.setVisibility(View.VISIBLE);
            titlebarTwoTvMenu.setText(menu);
        }
    }

    /**
     * listener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_two_fl_back:
                    activity.onBackPressed();
                    break;
                case R.id.titlebar_two_ll_menu:
                    if (onTitlebarTwoListener != null) {
                        onTitlebarTwoListener.onMenuClick();
                    }
                    break;
            }
        }
    };

    public interface OnTitlebarTwoListener {
        void onLeftButtonClick();

        void onRightButtonClick();

        void onMenuClick();
    }

}
