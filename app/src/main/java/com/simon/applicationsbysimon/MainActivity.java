package com.simon.applicationsbysimon;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.simon.applicationsbysimon.base.BaseActivity;
import com.simon.applicationsbysimon.model.Terminal;
import com.simon.applicationsbysimon.widget.CustomerDialog;
import com.simon.applicationsbysimon.widget.MyScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    public static String TAG = MainActivity.class.getSimpleName();

//    TextView spannaleText1;
//    TextView spannaleText2;
//    EditText mainEditText;
    ListView listView;
    MyScrollView my_scroll_layout;

    private String[] typeStrings;
    private CharSequence[] typeChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        spannaleText1 = (TextView) findViewById(R.id.spannale_text1);
//        spannaleText2 = (TextView) findViewById(R.id.spannale_text2);
//        mainEditText = (EditText) findViewById(R.id.main_edit_text);
        my_scroll_layout = (MyScrollView) findViewById(R.id.my_scroll_layout);



        listView = (ListView) findViewById(R.id.list_view);

        //        mainEditText.setFocusable(true);
        //        mainEditText.setFocusableInTouchMode(true);
        //        mainEditText.requestFocus();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1);
        adapter.add("string1");
        adapter.add("haha");
        adapter.add("heihei");
        adapter.add("string1");
        adapter.add("haha");
        adapter.add("heihei");
        adapter.add("string1");
        adapter.add("haha");
        adapter.add("heihei");
        listView.setAdapter(adapter);

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputMethodManager.showSoftInput(mainEditText, InputMethodManager.SHOW_FORCED);
//            }
//        }, 800);
//        initData();
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
//            if (i == 0) {
//                spannaleText1.setText(signalSourcePS);
//            }
//            if (i == 1) {
//                spannaleText2.setText(signalSourcePS);
//            }
        }
    }


    boolean isShowing = true;
    public void getDialog(View view) {
        if (isShowing) {
            my_scroll_layout.scrollHide();
            isShowing = false;
        }else{
            my_scroll_layout.scrollShow();
            isShowing = true;
        }
//        CustomerDialog.showSingleChoiceDialog(MainActivity.this, typeChars);
    }
}
