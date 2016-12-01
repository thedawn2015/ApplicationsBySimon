package com.simon.simple.third_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.baseandroid.listener.BaseUiListener;
import com.simon.baseandroid.listener.IUserInfoListener;
import com.simon.baseandroid.model.QQUserInfoModel;
import com.simon.baseandroid.util.LogUtil;
import com.simon.baseandroid.util.TencentUtil;
import com.simon.simple.R;
import com.squareup.picasso.Picasso;
import com.tencent.tauth.Tencent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc: 第三方登录
 * author: xiao
 * time: 2016/12/1
 */
public class ThirdLoginActivity extends AppCompatActivity {
    public static String TAG = ThirdLoginActivity.class.getSimpleName();
    @BindView (R.id.login_btn_qq)
    Button loginBtnQq;
    @BindView (R.id.login_image_header)
    ImageView loginImageHeader;
    @BindView (R.id.login_text_nick_name)
    TextView loginTextNickName;
    @BindView (R.id.login_text_gender)
    TextView loginTextGender;

    private Tencent tencent;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ThirdLoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_login);
        ButterKnife.bind(this);

    }

    @OnClick (R.id.login_btn_qq)
    public void onClick() {
        login();
    }

    /**
     * QQ登录
     */
    private void login() {
        if (tencent == null) {
            tencent = TencentUtil.getTencentInstance(ThirdLoginActivity.this);
        }
        tencent.login(ThirdLoginActivity.this, "all", new BaseUiListener(this, tencent, iUserInfoListener));
    }

    private IUserInfoListener iUserInfoListener = new IUserInfoListener() {
        @Override
        public void onUserInfoResponse(QQUserInfoModel qqUserInfoModel, String msg) {
            LogUtil.i(TAG, "onUserInfoResponse: msg=" + msg);
            LogUtil.i(TAG, "onUserInfoResponse: nickName=" + qqUserInfoModel.getNickname());
            LogUtil.i(TAG, "onUserInfoResponse: gender=" + qqUserInfoModel.getGender());
            LogUtil.i(TAG, "onUserInfoResponse: header=" + qqUserInfoModel.getFigureurl_qq_2());

            refreshViews();
        }
    };

    /**
     * 刷新
     */
    private void refreshViews() {
//        Picasso.with(this)
//                .load()
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener(this, tencent, iUserInfoListener));
        super.onActivityResult(requestCode, resultCode, data);
    }
}
