package com.simon.simpleretrofit.base.listener;

/**
 * 访问完成的接口
 * <p/>
 * Created by xw on 2016/7/26.
 */
public interface OnResponseListener<T> {
    void getResult(T t, String msg);
}
