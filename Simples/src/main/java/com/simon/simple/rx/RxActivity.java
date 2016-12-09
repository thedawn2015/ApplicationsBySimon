package com.simon.simple.rx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simon.base.listener.OnRequestCompletedListener;
import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;
import com.simon.simple.rx.util.CreateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxActivity extends BaseActivity {
    public static String TAG = RxActivity.class.getSimpleName();

    @BindView(R.id.rx_btn_create)
    Button rxBtnCreate;
    @BindView(R.id.rx_btn_just)
    Button rxBtnJust;
    @BindView(R.id.rx_btn_from)
    Button rxBtnFrom;
    @BindView(R.id.rx_text_content)
    TextView rxTextContent;
    @BindView(R.id.rx_btn_action)
    Button rxBtnAction;
    @BindView(R.id.rx_btn_map)
    Button rxBtnMap;
    @BindView(R.id.rx_btn_maps)
    Button rxBtnMaps;

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

    @OnClick({R.id.rx_btn_create, R.id.rx_btn_just, R.id.rx_btn_from, R.id.rx_btn_action, R.id.rx_btn_map, R.id.rx_btn_maps})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rx_btn_create:
                CreateUtil.createMethod(new OnRequestCompletedListener<String>() {
                    @Override
                    public void onCompleted(String response, String msg) {
                        rxTextContent.setText(response);
                    }
                });
                break;
            case R.id.rx_btn_just:
                CreateUtil.justMethod(new OnRequestCompletedListener<String>() {
                    @Override
                    public void onCompleted(String response, String msg) {
                        rxTextContent.setText(response);
                    }
                });
                break;
            case R.id.rx_btn_from:
                CreateUtil.fromMethod(new OnRequestCompletedListener<String>() {
                    @Override
                    public void onCompleted(String response, String msg) {
                        rxTextContent.setText(response);
                    }
                });
                break;
            case R.id.rx_btn_action:
                CreateUtil.actionMethod(new OnRequestCompletedListener<String>() {
                    @Override
                    public void onCompleted(String response, String msg) {
                        rxTextContent.setText(response);
                    }
                });
                break;
            case R.id.rx_btn_map:
                CreateUtil.mapMethod(new OnRequestCompletedListener<Integer>() {
                    @Override
                    public void onCompleted(Integer response, String msg) {
                        rxTextContent.setText("num=" + response);
                    }
                });
                break;
            case R.id.rx_btn_maps:
                CreateUtil.observeOnMethod(new OnRequestCompletedListener<Integer>() {
                    @Override
                    public void onCompleted(Integer response, String msg) {
                        rxTextContent.setText("num=" + response);
                    }
                });
                break;
        }
    }
}
