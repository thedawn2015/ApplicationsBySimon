package com.simon.baseandroid.listener;

import android.app.ProgressDialog;
import android.content.Context;

import com.simon.baseandroid.model.QQLoginModel;
import com.simon.baseandroid.model.QQUserInfoModel;
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

    //登录
    private static final int TYPE_REQUEST_QQ_LOGIN_COMPLETED = 101;
    //用户信息
    private static final int TYPE_REQUEST_QQ_USER_INFO_COMPLETED = 102;

    private Context context;
    private Tencent tencent;

    //用户登录之后的回调
    private IUserInfoListener iUserInfoListener;

    //请求类型
    private int requestType;

    private ProgressDialog progressDialog;

    public BaseUiListener(Context context, Tencent tencent, IUserInfoListener iUserInfoListener) {
        this.context = context;
        this.tencent = tencent;
        this.iUserInfoListener = iUserInfoListener;
        setRequestTypeLogin();
    }

    private void setRequestTypeLogin() {
        requestType = TYPE_REQUEST_QQ_LOGIN_COMPLETED;
    }

    private void setRequestTypeInfo() {
        requestType = TYPE_REQUEST_QQ_USER_INFO_COMPLETED;
    }

    @Override
    public void onComplete(Object o) {
        LogUtil.i(TAG, "onComplete: ");
        switch (requestType) {
            //登录，获取openId
            case TYPE_REQUEST_QQ_LOGIN_COMPLETED: {
                try {
                    if (o == null) {
                        sendErrorResponse();
                        return;
                    }
                    //获取openId和token
                    QQLoginModel qqLoginModel = GsonUtil.toObj(o.toString(), QQLoginModel.class);
                    tencent.setOpenId(qqLoginModel.getOpenid());
                    tencent.setAccessToken(qqLoginModel.getAccess_token(), String.valueOf(qqLoginModel.getExpires_in()));
                    //获取用户信息
                    setRequestTypeInfo();
                    QQToken qqToken = tencent.getQQToken();
                    UserInfo userInfo = new UserInfo(context.getApplicationContext(), qqToken);
                    userInfo.getUserInfo(this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    sendErrorResponse();
                }
            }
            break;
            //获取openId之后，获取QQ用户信息
            case TYPE_REQUEST_QQ_USER_INFO_COMPLETED: {
                try {
                    if (o == null) {
                        sendErrorResponse();
                        return;
                    }
                    //获取openId和token
                    QQUserInfoModel qqUserInfoModel = GsonUtil.toObj(o.toString(), QQUserInfoModel.class);
                    iUserInfoListener.onUserInfoResponse(qqUserInfoModel, "登录成功");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    sendErrorResponse();
                }
            }
            break;
        }

    }

    @Override
    public void onError(UiError uiError) {
        LogUtil.i(TAG, "onError: ");
        sendErrorResponse();
    }

    @Override
    public void onCancel() {
        LogUtil.i(TAG, "onCancel: ");
        //        sendResponse("取消登录");
    }

    private void sendErrorResponse() {
        sendResponse("登录失败");
    }

    private void sendResponse(String msg) {
        iUserInfoListener.onUserInfoResponse(null, msg);
    }

    /**
     * 显示对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage("正在登录，请稍等...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * 关闭对话框
     */
    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
