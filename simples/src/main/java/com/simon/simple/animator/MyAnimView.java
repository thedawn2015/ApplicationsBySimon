package com.simon.simple.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 动画 View
 * Created by xw on 2016/8/24
 */
public class MyAnimView extends View {
    public static final float RADIUS = 30f;

    private Context context;
    private Point currentPoint;
    private String color;

    private Paint mPaint;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(dp2px(context, RADIUS), dp2px(context, RADIUS));
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, dp2px(context, RADIUS), mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(dp2px(context, RADIUS), dp2px(context, RADIUS));
        Point endPoint = new Point(getWidth() - dp2px(context, RADIUS), getHeight() - dp2px(context, RADIUS));

        ValueAnimator pointAnim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        pointAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator colorAnim = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                String color = (String) valueAnimator.getAnimatedValue();
                setColor(color);
            }
        });
        AnimatorSet animSet = new AnimatorSet();
        animSet
                .play(pointAnim)
                .with(colorAnim);
        animSet.setDuration(5000);
        animSet.start();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    private float px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale;
    }

    private float dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }
}
