package com.simon.baseandroid.listener;

import com.simon.baseandroid.model.QQUserInfoModel;

/**
 * desc: QQ用户信息
 * author: xiao
 * time: 2016/12/1
 */
public interface IUserInfoListener {
    void onUserInfoResponse(QQUserInfoModel qqUserInfoModel, String msg);
}
