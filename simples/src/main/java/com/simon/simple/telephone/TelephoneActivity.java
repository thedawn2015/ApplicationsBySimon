package com.simon.simple.telephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.simon.base.util.BaseActivity;
import com.simon.base.util.LogUtil;
import com.simon.simple.R;
import com.simon.simple.telephone.util.BuildUtil;
import com.simon.simple.telephone.util.TelephoneUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TelephoneActivity extends BaseActivity {
    public static String TAG = TelephoneActivity.class.getSimpleName();
    @BindView (R.id.text_phone_info)
    TextView textPhoneInfo;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TelephoneActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        ButterKnife.bind(this);

        String info = TelephoneUtil.getUserAgentInfo(this, "MyDemo");
        LogUtil.i(TAG, "onCreate: info=" + info);

        BuildUtil.isMIUI();
        BuildUtil.isEMUI();
        BuildUtil.isFlyme();
        String userAgent = System.getProperty("http.agent");
        LogUtil.i(TAG, "onCreate: userAgent=" + userAgent);
        String phoneInfo = getInfo();
        // Toast.makeText(this, phoneInfo, Toast.LENGTH_LONG).show();
        textPhoneInfo.setText(phoneInfo + "\nUser-Agent：" + userAgent);
    }

    @NonNull
    private String getInfo() {
        String phoneInfo = "Product: " + android.os.Build.PRODUCT;
        phoneInfo += "\nCPU_ABI: " + android.os.Build.CPU_ABI;
        phoneInfo += "\nTAGS: " + android.os.Build.TAGS;
        phoneInfo += "\nVERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += "\nMODEL: " + android.os.Build.MODEL;
        phoneInfo += "\nSDK: " + android.os.Build.VERSION.SDK;
        phoneInfo += "\nVERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += "\nDEVICE: " + android.os.Build.DEVICE;
        phoneInfo += "\nDISPLAY: " + android.os.Build.DISPLAY;
        phoneInfo += "\nBRAND: " + android.os.Build.BRAND;
        phoneInfo += "\nBOARD: " + android.os.Build.BOARD;
        phoneInfo += "\nFINGERPRINT: " + android.os.Build.FINGERPRINT;
        phoneInfo += "\nID: " + android.os.Build.ID;
        phoneInfo += "\nMANUFACTURER: " + android.os.Build.MANUFACTURER;
        phoneInfo += "\nUSER: " + android.os.Build.USER;
        return phoneInfo;
    }

}
