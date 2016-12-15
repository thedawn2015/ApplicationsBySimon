package com.simon.sample.rx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simon.base.listener.OnRequestCompletedListener;
import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.LogUtil;
import com.simon.sample.R;
import com.simon.sample.rx.util.CreateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

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
    @BindView(R.id.rx_restart_maps)
    Button rxRestartMaps;
    @BindView(R.id.rx_binding)
    Button rxBinding;

    @BindView(R.id.rx_edit_name)
    EditText rxEditName;
    @BindView(R.id.rx_edit_password)
    EditText rxEditPassword;
    @BindView(R.id.rx_btn_login)
    Button rxBtnLogin;

    private Subscriber subscriber;


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RxActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);

        assignViews();
    }

    private void assignViews() {
        CreateUtil.combineLatestMethod(rxEditName, rxEditPassword, rxBtnLogin, new OnRequestCompletedListener<String>() {
            @Override
            public void onCompleted(String response, String msg) {
                LogUtil.i(TAG, "onCompleted: " + response);
            }
        });
    }

    @OnClick({R.id.rx_btn_create, R.id.rx_btn_just, R.id.rx_btn_from, R.id.rx_btn_action, R.id.rx_btn_map, R.id.rx_btn_maps,
            R.id.rx_restart_maps, R.id.rx_binding, R.id.rx_btn_login})
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
                subscriber = CreateUtil.observeOnMethod(new OnRequestCompletedListener<Integer>() {
                    @Override
                    public void onCompleted(Integer response, String msg) {
                        rxTextContent.setText("num=" + response);
                    }
                });
                break;
            case R.id.rx_restart_maps:
                if (subscriber != null && !subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                }
                subscriber = CreateUtil.observeOnMethod(new OnRequestCompletedListener<Integer>() {
                    @Override
                    public void onCompleted(Integer response, String msg) {
                        rxTextContent.setText("num=" + response);
                    }
                });
                break;
            case R.id.rx_binding:
                CreateUtil.rxBindingMethod(rxBinding, new OnRequestCompletedListener<Integer>() {
                    @Override
                    public void onCompleted(Integer response, String msg) {
                        rxTextContent.setText(msg);
                    }
                });
                break;
            case R.id.rx_btn_login:
                break;
        }
    }
}
