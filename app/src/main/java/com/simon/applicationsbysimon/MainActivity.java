package com.simon.applicationsbysimon;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.simon.applicationsbysimon.base.BaseActivity;
import com.simon.applicationsbysimon.model.Terminal;
import com.simon.applicationsbysimon.widget.CustomerDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    TextView spannaleText1;
    TextView spannaleText2;

    private String[] typeStrings;
    private CharSequence[] typeChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spannaleText1 = (TextView) findViewById(R.id.spannale_text1);
        spannaleText2 = (TextView) findViewById(R.id.spannale_text2);
        initData();
    }

    private void initData() {
        List<Terminal> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                Terminal terminal = new Terminal();
                terminal.setId(1245841259);
                terminal.setName("暂无掉电");
                list.add(terminal);
            } else {
                Terminal terminal = new Terminal();
                terminal.setId(124584259);
                terminal.setName("最后掉电时间 2016年7月12日 18:29:52");
                list.add(terminal);
            }
        }
        Terminal terminal;
        typeStrings = new String[list.size()];
        typeChars = new CharSequence[list.size()];

        SpannableString signalSourcePS;
        for (int i = 0; i < list.size(); i++) {
            terminal = list.get(i);
            typeStrings[i] = "设备：" + terminal.getId() + "\n" + terminal.getName();
            signalSourcePS = new SpannableString(typeStrings[i]);
            int breakIndex = typeStrings[i].indexOf("\n");
            signalSourcePS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, breakIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            signalSourcePS.setSpan(new RelativeSizeSpan(1.2f), 0, breakIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            signalSourcePS.setSpan(new RelativeSizeSpan(1f), breakIndex, typeStrings[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (typeStrings[i].endsWith("暂无掉电")) {
                signalSourcePS.setSpan(new ForegroundColorSpan(Color.GRAY), breakIndex, typeStrings[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                signalSourcePS.setSpan(new ForegroundColorSpan(Color.RED), breakIndex, typeStrings[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            typeChars[i] = signalSourcePS;
            if (i == 0) {
                spannaleText1.setText(signalSourcePS);
            }
            if (i == 1) {
                spannaleText2.setText(signalSourcePS);
            }
        }
    }

    public void getDialog(View view) {
        CustomerDialog.showSingleChoiceDialog(MainActivity.this, typeChars);
    }
}
