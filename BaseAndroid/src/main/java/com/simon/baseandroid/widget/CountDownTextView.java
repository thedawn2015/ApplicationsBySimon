package com.simon.baseandroid.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.simon.baseandroid.R;

/**
 * desc:自定义倒计时TextView
 * author: xiao
 * time: 2016/12/6
 */
public class CountDownTextView extends TextView {
    public static String TAG = CountDownTextView.class.getSimpleName();

    //倒计时的时间
    private static final int COUNT_SECONDS = 60;

    private Context context;
    private IClickListener iClickListener;

    public CountDownTextView(Context context, IClickListener iClickListener) {
        super(context);
        this.context = context;
        this.iClickListener = iClickListener;
        initView();
    }

    private void initView() {
        setText("获取验证码");
        setTextColor(ContextCompat.getColor(context, R.color.paleturquoise));
        setTextSize(R.dimen.dp14);
    }

    public void startCountDown() {

    }

    public interface IClickListener {
        void onClick();
    }
}
