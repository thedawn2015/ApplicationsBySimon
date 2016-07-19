package com.simon.applicationsbysimon.widget;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by TheDawn on 2016/7/18.
 */
public class ScrollLinearLayout extends LinearLayout {
    public static final String TAG = ScrollLinearLayout.class.getSimpleName();

    int width;
    int height;
    LayoutParams layoutParams;
    int perPx = 20;
    int currentHeight;
    boolean isShowing = true;

    public ScrollLinearLayout(Context context) {
        super(context, null);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        Log.i(TAG, "onMeasure: height=" + height);
        //Modified By xw at 2016/7/19 Explain：请求的是父类的布局
        layoutParams = (LayoutParams) getLayoutParams();
//        layoutParams.topMargin = height;
//        setLayoutParams(layoutParams);
    }

    /**
     * 隐藏的Task
     */
    class HideTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            while (currentHeight > -height) {
                currentHeight = currentHeight - perPx;
                Log.i(TAG, "doInBackground: currentHeight=" + currentHeight);
                if (currentHeight < -height) {
                    currentHeight = -height;
                }
                layoutParams.topMargin = currentHeight;
                publishProgress(currentHeight);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isShowing = false;
            return "执行完毕";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            layoutParams.topMargin = height;
            setLayoutParams(layoutParams);
        }
    }

    /**
     * 显示的Task
     */
    class ShowTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            while (currentHeight < 0) {
                currentHeight = currentHeight + perPx;
                Log.i(TAG, "doInBackground: currentHeight=" + currentHeight);
                if (currentHeight > 0) {
                    currentHeight = 0;
                }
                layoutParams.topMargin = currentHeight;
                publishProgress(currentHeight);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isShowing = true;
            return "执行完毕";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            layoutParams.topMargin = height;
            setLayoutParams(layoutParams);
        }
    }

    public void hideView() {
        if (isShowing) {
            currentHeight = 0;
            HideTask hideTask = new HideTask();
            hideTask.execute(20);
        }
    }

    public void showView() {
        if (!isShowing) {
            currentHeight = -height;
            ShowTask showTask = new ShowTask();
            showTask.execute(20);
        }
    }
}
