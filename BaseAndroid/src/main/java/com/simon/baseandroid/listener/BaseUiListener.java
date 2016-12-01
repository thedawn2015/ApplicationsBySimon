package com.simon.baseandroid.listener;

import android.content.Context;

import com.simon.baseandroid.model.QQLoginModel;
import com.simon.baseandroid.util.GsonUtil;
import com.simon.baseandroid.util.LogUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * desc: 创建一个新的接口
 * author: xiao
 * time: 2016/12/1
 */
public class BaseUiListener implements IUiListener {
    public static String TAG = BaseUiListener.class.getSimpleName();

    private Tencent tencent;
    private Context context;

    public BaseUiListener(Context context, Tencent tencent) {
        this.context = context;
        this.tencent = tencent;
    }

    @Override
    public void onComplete(Object o) {
        LogUtil.i(TAG, "onComplete: ");
        QQLoginModel qqLoginModel = GsonUtil.toObj(o.toString(), QQLoginModel.class);
        tencent.setOpenId(qqLoginModel.getOpenid());
        tencent.setAccessToken(qqLoginModel.getAccess_token(), String.valueOf(qqLoginModel.getExpires_in()));
        QQToken qqToken = tencent.getQQToken();
        UserInfo userInfo = new UserInfo(context.getApplicationContext(), qqToken);
        userInfo.getUserInfo(new IUiListener() {
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
        });
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
