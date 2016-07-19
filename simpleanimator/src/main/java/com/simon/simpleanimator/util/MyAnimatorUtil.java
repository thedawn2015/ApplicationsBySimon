package com.simon.simpleanimator.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

/**
 * Created by xw on 2016/7/19.
 */
public class MyAnimatorUtil {
    public static String TAG = MyAnimatorUtil.class.getSimpleName();

    /**
     * 旋转
     *
     * @param view
     */
    public static void rotateAnimRun(final View view) {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(view, "rotationX", 0.0F, 360.0F)
                .setDuration(1000);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cValaue = (Float) animation.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: cValaue=" + cValaue);
                //                view.setAlpha(cValaue);
                //                view.setScaleX(cValaue);
                //                view.setScaleY(cValaue);
            }
        });
    }

    /**
     * 移动
     *
     * @param view
     */
    public static void transferAnimRun(final View view) {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(view, "y", view.getHeight(), view.getHeight() * 2)
                .setDuration(500);
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
    }

}
