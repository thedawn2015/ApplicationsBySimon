package com.simon.baseandroid.listener;

import com.simon.baseandroid.util.LogUtil;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * desc: 创建一个新的接口
 * author: xiao
 * time: 2016/12/1
 */
public class BaseUiListener implements IUiListener {
    public static String TAG = BaseUiListener.class.getSimpleName();

    @Override
    public void onComplete(Object o) {
        LogUtil.i(TAG, "onComplete: ");
    }

    @Override
    public void onError(UiError uiError) {
        LogUtil.i(TAG, "onError: ");

    }

    @Override
    public void onCancel() {
        LogUtil.i(TAG, "onCancel: ");

    }
}
