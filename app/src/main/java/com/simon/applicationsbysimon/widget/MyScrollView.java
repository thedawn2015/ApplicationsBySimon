package com.simon.applicationsbysimon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by xw on 2016/7/18.
 */
public class MyScrollView extends LinearLayout {
    public static String TAG = MyScrollView.class.getSimpleName();

    private int width;
    private int height;

    MarginLayoutParams headerLayoutParams;

    public MyScrollView(Context context) {
        super(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);   //获取ViewGroup宽度
        height = MeasureSpec.getSize(heightMeasureSpec);  //获取ViewGroup高度
        Log.i(TAG, "onMeasure: width="+width);
        Log.i(TAG, "onMeasure: height="+height);
        setMeasuredDimension(width, height);    //设置ViewGroup的宽高
        headerLayoutParams = (MarginLayoutParams) getLayoutParams();
    }

    public void scrollHide() {
        headerLayoutParams.topMargin = -height;
        setLayoutParams(headerLayoutParams);
    }

    public void scrollShow() {
        headerLayoutParams.topMargin = 0;
        setLayoutParams(headerLayoutParams);
    }
}
