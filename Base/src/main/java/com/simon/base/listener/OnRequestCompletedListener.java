package com.simon.base.listener;

/**
 * desc: 请求完成 监听器
 * author: xiao
 * time: 2016/11/28
 */
public interface OnRequestCompletedListener<T> {
    /**
     * 请求完成
     *
     * @param response
     * @param msg
     */
    void onCompleted(T response, String msg);
}
