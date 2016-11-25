package com.simon.simple.rx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;
import com.simon.simple.rx.util.CreateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxActivity extends BaseActivity {
    public static String TAG = RxActivity.class.getSimpleName();
    @BindView (R.id.rx_btn_create)
    Button rxBtnCreate;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RxActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.rx_btn_create)
    public void onClick() {
        CreateUtil.createMethod();
    }
}
