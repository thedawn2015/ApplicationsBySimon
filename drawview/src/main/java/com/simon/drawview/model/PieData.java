package com.simon.drawview.model;

import android.support.annotation.NonNull;

/**
 * Created by xw on 2016/7/7.
 */
public class PieData {
    // 用户关心数据
    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    // 非用户关心数据
    private int color = 0;      // 颜色
    private float angle = 0;    // 角度

    public PieData(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
    }
}
