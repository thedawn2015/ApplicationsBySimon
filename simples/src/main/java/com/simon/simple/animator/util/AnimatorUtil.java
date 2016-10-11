package com.simon.simple.animator.util;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * 属性动画
 * Created by xw on 2016/10/11.
 */
public class AnimatorUtil {
    public static String TAG = AnimatorUtil.class.getSimpleName();

    /**
     * 显示控件
     *
     * @param view
     */
    public static void showViewWithAnimator(final View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            animator.setDuration(600);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float alpha = (float) valueAnimator.getAnimatedValue();
                    //                    LogUtil.i(TAG, "onAnimationUpdate: alpha=" + alpha);
                    if (alpha == 1) {
                        view.setVisibility(View.VISIBLE);
                    }
                }
            });
            animator.start();
        }
    }

    /**
     * 隐藏控件
     *
     * @param view
     */
    public static void hideViewWithAnimator(final View view) {
        if (view.getVisibility() == View.VISIBLE) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            animator.setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float alpha = (float) valueAnimator.getAnimatedValue();
                    //                    LogUtil.i(TAG, "onAnimationUpdate: alpha=" + alpha);
                    if (alpha == 0) {
                        view.setVisibility(View.GONE);
                    }
                }
            });
            animator.start();
        }
    }

}
