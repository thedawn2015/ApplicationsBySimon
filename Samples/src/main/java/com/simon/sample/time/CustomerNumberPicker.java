package com.simon.sample.time;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.simon.baseandroid.util.DensityUtil;
import com.simon.sample.R;

import java.lang.reflect.Field;

/**
 * desc:
 * author: xw
 * time: 2017/2/23
 */
public class CustomerNumberPicker extends NumberPicker {

    private int textColor;
    private float textSize;
    private int dividerColor;
    private int backgroundColor;

    public CustomerNumberPicker(Context context) {
        super(context);
    }

    public CustomerNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerNumberPicker);
        textColor = typedArray.getColor(R.styleable.CustomerNumberPicker_textColor, ContextCompat.getColor(context, R.color.white));
        textSize = typedArray.getDimension(R.styleable.CustomerNumberPicker_textSize, DensityUtil.px2dp(context, 12));
        dividerColor = typedArray.getColor(R.styleable.CustomerNumberPicker_dividerColor, ContextCompat.getColor(context, R.color.white));
        backgroundColor = typedArray.getColor(R.styleable.CustomerNumberPicker_textColor, ContextCompat.getColor(context, R.color.gray));

        typedArray.recycle();

        setNumberPickerDividerColor();
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    /**
     * 设置属性
     *
     * @param child
     */
    private void updateView(View child) {
        if (child instanceof EditText) {
            //这里修改字体的属性
            ((EditText) child).setTextColor(textColor);
            ((EditText) child).setTextSize(textSize);
        }
    }

    /**
     * 设置分割线的颜色
     */
    private void setNumberPickerDividerColor() {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pickerField : pickerFields) {
            if (pickerField.getName().equals("mSelectionDivider")) {
                pickerField.setAccessible(true);
                try {
                    pickerField.set(CustomerNumberPicker.this, dividerColor);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void setDescendantFocusability(int focusability) {
        //默认不能编辑
        super.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//        super.setDescendantFocusability(focusability);
    }
}
