package com.simon.baseandroid.listener;

/**
 * desc: View的基本操作 Listener
 * author: xw
 * time: 2016/12/14
 */
public interface IViewListener {

    /**
     * 绑定控件
     */
    void assignViews();

    /**
     * 获取初始数据
     */
    void initData();

    /**
     * 绑定数据
     */
    void bindData();

    /**
     * 刷新数据
     */
    void refreshViews();
}
