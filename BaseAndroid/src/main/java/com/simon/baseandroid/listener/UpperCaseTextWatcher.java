package com.simon.baseandroid.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * desc: 自动转换大小写的text watcher
 * author: xw
 * time: 2017/3/20
 */
public class UpperCaseTextWatcher implements TextWatcher {
    private EditText editText;

    public UpperCaseTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editText.removeTextChangedListener(this);//先取消监听
        int index = editText.getSelectionStart();//取得光标位置
        editText.setText(s.toString().toUpperCase());//转换成大写
        editText.setSelection(index);//重新设置光标位置
        editText.addTextChangedListener(this);//绑定监听
    }
}
