package com.simon.simpleretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simon.simpleretrofit.base.listener.OnResponseListener;
import com.simon.simpleretrofit.rest.util.LoginServiceUtil;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    Button request_http;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        request_http = (Button) findViewById(R.id.request_http);

        request_http.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginServiceUtil.getResult(username.getText().toString(), password.getText().toString(), new OnResponseListener() {
                    @Override
                    public void getResult(Object o, String msg) {
                        Log.i(TAG, "getResult: ");
                    }
                });
            }
        });
    }
}
