package com.simon.drawview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.simon.drawview.model.PieData;
import com.simon.drawview.util.DataCheckUtil;

import java.util.List;

/**
 * 简单的View，画简单的图
 * Created by xw on 2016/7/6
 */
public class SimpleView extends View {
    public static String TAG = SimpleView.class.getSimpleName();

    private Paint mPaint;
    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private List<PieData> pieDataList;
    // 宽高
    private int mWidth, mHeight;

    public SimpleView(Context context) {
        super(context, null);
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
        mPaint.setAntiAlias(true);
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

        rectF = new RectF(dp2px(10), dp2px(200), dp2px(210), dp2px(300));
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, dp2px(0), dp2px(90), false, mPaint);

        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(20f);

        // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 200, 100, mPaint);

        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 500, 100, mPaint);

        // 描边加填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        drawPie(canvas);

    }

    /**
     * 画饼
     *
     * @param canvas
     */
    private void drawPie(Canvas canvas) {
        if (DataCheckUtil.isListNull(pieDataList)) {
            return;
        }
        // 当前起始角度
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectf = new RectF(-r, -r, r, r);
        for (int i = 0; i < pieDataList.size(); i++) {
            PieData pieData = pieDataList.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectf, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();
        }
    }

    // 设置数据
    public void setData(List<PieData> pieDataList) {
        this.pieDataList = pieDataList;
        initDate(pieDataList);
        // 刷新
        invalidate();
    }

    /**
     * 初始化数据
     *
     * @param pieDataList
     */
    private void initDate(List<PieData> pieDataList) {
        float sumValue = 0;
        for (int i = 0; i < pieDataList.size(); i++) {
            PieData pieData = pieDataList.get(i);
            sumValue += pieData.getValue();
            int j = i % mColors.length;
            pieData.setColor(mColors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < pieDataList.size(); i++) {
            PieData pieData = pieDataList.get(i);
            float percentage = pieData.getValue() / sumValue;
            float angle = percentage * 360;
            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
            sumAngle += angle;
            Log.i(TAG, "initDate: angle=" + pieData.getAngle());
        }
    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
//        Log.i(TAG, "dp2px: density=" + density);
        return density * dp;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
