package com.simon.simpleretrofit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simon.simpleretrofit.base.listener.OnResponseListener;
import com.simon.simpleretrofit.download.util.DownloadServiceUtil;
import com.simon.simpleretrofit.rest.util.LoginServiceUtil;
import com.simon.simpleretrofit.rx.SimpleRxJavaUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = MainActivity.class.getSimpleName();
    public static final String LOCAL_ACTION = "simple.receive_intent";

    Button request_http;
    EditText username;
    EditText password;
    TextView downloaded_size;
    Button download;
    Button simple_rxjava;

    //    String url = "http://download.fir.im/v2/app/install/572eec6fe75e2d7a05000008?download_token=572bcb03dad2eed7c758670fd23b5ac4";
    String url = "http://p.gdown.baidu.com/7411f966a1f97a54b97258af68408d47555af78b73e95e0916097d3fbaf41af04ec36851ddf9491164e25a775501e6ebdeda6b3a604fff46925c9b01230fd0b157503f8f5e8bebc0fa96a3e860a70f091915f81dc08141206311e3a2747a3d4f0b68b44aeb93e13d8abd77b891e5cbf2386464e8631a48906d04d0d55dea9f2aba204c7ae78326ec5bbce8cfd8721dea42886ec744f3a9590d76c72d5d5287a2808e27f43388e7ab804a14cffb02ed27748d47c5f40729c6fd2a6045c9d4e2ca646bc8175679b29dcdbcbe0a6f19893d83ebf025ca4bf6a2c1250aa4f4faf971b93036c538c78b18140a419dde2db1adda72cfdf446dc6ee3b3c544e3f724ede7c49b4dcbc5c281098efa12e9d1de60e8325446168af956c4d4624ca148deea6";

    MyBroadcastReceiver myBroadcastReceiver;
    LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
        registerMyReceiver();
    }

    private void registerMyReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCAL_ACTION);
        localBroadcastManager.registerReceiver(myBroadcastReceiver, intentFilter);
    }

    private void assignViews() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        request_http = (Button) findViewById(R.id.request_http);
        download = (Button) findViewById(R.id.download);
        downloaded_size = (TextView) findViewById(R.id.downloaded_size);
        simple_rxjava = (Button) findViewById(R.id.simple_rxjava);

        request_http.setOnClickListener(MainActivity.this);
        download.setOnClickListener(MainActivity.this);
        simple_rxjava.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request_http:
//                                LoginServiceUtil.getResult(username.getText().toString(), password.getText().toString(), new OnResponseListener() {
//                                    @Override
//                                    public void getResult(Object o, String msg) {
//                                        Log.i(TAG, "getResult: ");
//                                    }
//                                });
                LoginServiceUtil.loginWithRetrofit(username.getText().toString(), password.getText().toString(), new OnResponseListener() {
                    @Override
                    public void getResult(Object o, String msg) {
                        Log.i(TAG, "getResult: ");
                    }
                });
                break;
            case R.id.download:
                DownloadServiceUtil.getInstance().downloadApk(MainActivity.this, url);
                break;
            case R.id.simple_rxjava:
                SimpleRxJavaUtil.simpleMethod();
                SimpleRxJavaUtil.nestMethod();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myBroadcastReceiver);
    }

    /**
     * 接收监听进度的广播
     */
    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long size = intent.getLongExtra("current_size", -1);
            downloaded_size.setText("当前下载量为：" + size);
            Log.i(TAG, "onReceive: size=" + size);
        }
    }
}
