package com.simon.drawview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 简单的View，画简单的图
 * Created by xw on 2016/7/6
 */
public class SimpleView extends View {
    public static String TAG = SimpleView.class.getSimpleName();

    private Paint mPaint;

    public SimpleView(Context context) {
        super(context);
        initPaint();
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        //填充
        mPaint.setStyle(Paint.Style.FILL);
        //画笔宽度
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(dp2px(10), dp2px(10), dp2px(210), dp2px(10), mPaint);

        RectF rectF = new RectF(dp2px(10), dp2px(20), dp2px(210), dp2px(40));
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF, mPaint);

        rectF = new RectF(dp2px(dp2px(10)), dp2px(50), dp2px(210), dp2px(70));
        mPaint.setColor(Color.CYAN);
        canvas.drawRoundRect(rectF, dp2px(10), dp2px(20), mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(dp2px(110), dp2px(100), dp2px(20), mPaint);

        rectF = new RectF(dp2px(10), dp2px(140), dp2px(210), dp2px(180));
        mPaint.setColor(Color.RED);
        canvas.drawOval(rectF, mPaint);

        rectF = new RectF(dp2px(10), dp2px(200), dp2px(210), dp2px(230));
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, dp2px(0), dp2px(90), false, mPaint);
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        Log.i(TAG, "dp2px: density=" + density);
        return density * dp;
    }
}
