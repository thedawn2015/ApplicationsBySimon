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
public class TitleBarThreeContainer {
    public static String TAG = TitleBarThreeContainer.class.getSimpleName();

    FrameLayout titlebarThreeFlBack;
    RadioButton titlebarThreeRbLeft;
    RadioButton titlebarThreeRbCenter;
    RadioButton titlebarThreeRbRight;
    RadioGroup titlebarThreeRgTitle;
    TextView titlebarThreeTvMenu;
    LinearLayout titlebarThreeLlMenu;

    private Activity activity;
    private OnTitlebarThreeListener onTitlebarThreeListener;

    public TitleBarThreeContainer(Activity activity) {
        this.activity = activity;
        assignViews(activity);
    }

    private void assignViews(Activity activity) {
        titlebarThreeFlBack = (FrameLayout) activity.findViewById(R.id.titlebar_three_fl_back);
        titlebarThreeRgTitle = (RadioGroup) activity.findViewById(R.id.titlebar_three_rg_title);
        titlebarThreeRbLeft = (RadioButton) activity.findViewById(R.id.titlebar_three_rb_left);
        titlebarThreeRbCenter = (RadioButton) activity.findViewById(R.id.titlebar_three_rb_center);
        titlebarThreeRbRight = (RadioButton) activity.findViewById(R.id.titlebar_three_rb_right);
        titlebarThreeTvMenu = (TextView) activity.findViewById(R.id.titlebar_three_tv_menu);
        titlebarThreeLlMenu = (LinearLayout) activity.findViewById(R.id.titlebar_three_ll_menu);

        titlebarThreeFlBack.setOnClickListener(onClickListener);
        titlebarThreeLlMenu.setOnClickListener(onClickListener);
        titlebarThreeRgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int buttonId = radioGroup.getCheckedRadioButtonId();
                switch (buttonId) {
                    case R.id.titlebar_three_rb_left:
                        if (onTitlebarThreeListener != null) {
                            onTitlebarThreeListener.onLeftButtonClick();
                        }
                        break;
                    case R.id.titlebar_three_rb_center:
                        if (onTitlebarThreeListener != null) {
                            onTitlebarThreeListener.onCenterButtonClick();
                        }
                        break;
                    case R.id.titlebar_three_rb_right:
                        if (onTitlebarThreeListener != null) {
                            onTitlebarThreeListener.onRightButtonClick();
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
     * @param centerTitle
     * @param rightTitle
     * @param menu
     * @param onTitlebarThreeListener
     */
    public void setTitleAndMenu(String leftTitle, String centerTitle, String rightTitle, String menu, OnTitlebarThreeListener onTitlebarThreeListener) {
        titlebarThreeRbLeft.setText(leftTitle);
        titlebarThreeRbCenter.setText(centerTitle);
        titlebarThreeRbRight.setText(rightTitle);
        this.onTitlebarThreeListener = onTitlebarThreeListener;
        if (!TextUtils.isEmpty(menu)) {
            titlebarThreeLlMenu.setVisibility(View.VISIBLE);
            titlebarThreeTvMenu.setText(menu);
        }
    }

    /**
     * listener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_three_fl_back:
                    activity.onBackPressed();
                    break;
                case R.id.titlebar_three_ll_menu:
                    if (onTitlebarThreeListener != null) {
                        onTitlebarThreeListener.onMenuClick();
                    }
                    break;
            }
        }
    };

    public interface OnTitlebarThreeListener {
        void onLeftButtonClick();

        void onCenterButtonClick();

        void onRightButtonClick();

        void onMenuClick();
    }

}
