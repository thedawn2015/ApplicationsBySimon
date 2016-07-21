package com.simon.drawview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 简单的View，canvas
 * Created by xw on 2016/7/21.
 */
public class SimpleViewCanvas extends View {

    private Paint mPaint;

    public SimpleViewCanvas(Context context) {
        super(context, null);
    }

    public SimpleViewCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLACK);
        canvas.translate(50, 50);
        canvas.drawCircle(50, 50, 50, mPaint);

        mPaint.setColor(Color.RED);
        canvas.translate(getWidth() / 2 - 50, getHeight() / 2 - 50);
        RectF rectF = new RectF(0, -400, 400, 0);
        canvas.drawRect(rectF, mPaint);

        mPaint.setColor(Color.MAGENTA);
        canvas.scale(-0.5f, -0.5f, 200, 0);
        canvas.drawRect(rectF, mPaint);
    }
}
