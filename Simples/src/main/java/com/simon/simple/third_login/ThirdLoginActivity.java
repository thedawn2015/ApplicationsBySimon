package com.simon.simple.third_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.simon.baseandroid.listener.BaseUiListener;
import com.simon.baseandroid.util.TencentUtil;
import com.simon.simple.R;
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

        tencent = TencentUtil.initTencent(ThirdLoginActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick (R.id.login_btn_qq)
    public void onClick() {
        login();
    }

    /**
     * QQ登录
     */
    private void login() {
        tencent.login(ThirdLoginActivity.this, "all", new BaseUiListener());
    }
}
