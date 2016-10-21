package com.simon.simple.keyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simon.simple.R;
import com.simon.simple.base.util.KeyboardUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeyBoardActivity extends AppCompatActivity {
    public static String TAG = KeyBoardActivity.class.getSimpleName();
    @BindView (R.id.key_button_show)
    Button keyButtonShow;
    @BindView (R.id.key_button_hide)
    Button keyButtonHide;
    @BindView (R.id.key_edit)
    EditText keyEdit;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, KeyBoardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        ButterKnife.bind(this);


    }

    @OnClick ({R.id.key_button_show, R.id.key_button_hide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_button_show:
                KeyboardUtil.openKeybord(keyEdit, getApplicationContext());
                break;
            case R.id.key_button_hide:
                KeyboardUtil.closeKeybord(keyEdit, getApplicationContext());
                break;
        }
    }
}
