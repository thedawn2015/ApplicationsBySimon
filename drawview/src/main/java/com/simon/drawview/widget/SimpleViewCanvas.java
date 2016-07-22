package com.simon.drawview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.simon.drawview.R;

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
        //填充
        mPaint.setStyle(Paint.Style.STROKE);
        //画笔宽度
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        transAndScale(canvas);
        //        scale(canvas);
        drawMyPic(canvas);
    }

    private void drawMyPic(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mypic);
        Rect rectSrc = new Rect(0, 0, 800, 800);
        Rect rectDst = new Rect(0, 0, 400, 800);
        canvas.drawBitmap(bitmap, rectSrc, rectDst, null);
    }

    private void scale(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rectF = new RectF(-400, -400, 400, 400);
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, mPaint);
        }
    }

    private void transAndScale(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.translate(50, 50);
        canvas.drawCircle(50, 50, 50, mPaint);

        mPaint.setColor(Color.RED);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rectF = new RectF(0, 0, 400, 400);
        canvas.drawRect(rectF, mPaint);

        // 保存画布状态
        canvas.save();
        mPaint.setColor(Color.MAGENTA);
        canvas.scale(0.5f, 0.5f, -200, 0);
        canvas.drawRect(rectF, mPaint);
        // 画布状态回滚
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.GRAY);
        canvas.scale(-0.5f, 0.5f, 200, 0);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.YELLOW);
        canvas.scale(0.5f, -0.5f, -200, 0);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.BLUE);
        canvas.scale(-0.5f, -0.5f, 200, 0);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();
    }
}
