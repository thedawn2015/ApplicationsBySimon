package com.simon.simple.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;
import com.simon.simple.dialog.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {
    public static String TAG = DialogActivity.class.getSimpleName();
    @BindView (R.id.normal_btn_dialog)
    Button normalBtnDialog;
    @BindView (R.id.customer_btn_dialog)
    Button customerBtnDialog;
    @BindView (R.id.customer_btn_popup)
    Button customerBtnPopup;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DialogActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick ({R.id.normal_btn_dialog, R.id.customer_btn_dialog, R.id.customer_btn_popup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_btn_dialog:
                DialogUtil.showNormalDialog(DialogActivity.this);
                break;
            case R.id.customer_btn_dialog:
                DialogUtil.showCustomerDialog(DialogActivity.this);
                break;
            case R.id.customer_btn_popup:
                break;
        }
    }
}
