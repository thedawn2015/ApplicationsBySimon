package com.simon.sample.time;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;

import com.simon.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc: 选择对话框
 * author: xw
 * time: 2017/2/22
 */
public class NumberPickerDialogFragment extends DialogFragment {
    public static final String TAG = NumberPickerDialogFragment.class.getSimpleName();

    @BindView(R.id.number_picker)
    NumberPicker numberPicker;
    @BindView(R.id.number_picker2)
    NumberPicker numberPicker2;
    @BindView(R.id.number_picker3)
    NumberPicker numberPicker3;
    @BindView(R.id.picker_btn_sure)
    Button pickerBtnSure;


    private String[] strings;

    public static NumberPickerDialogFragment newInstance() {
        Bundle args = new Bundle();
        NumberPickerDialogFragment fragment = new NumberPickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1 通过样式定义
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Dialog_Time);
        //2代码设置 无标题 无边框
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        initData();
    }

    private void initData() {
        strings = new String[7];
        strings[0] = "星期1";
        strings[1] = "星期2";
        strings[2] = "星期3";
        strings[3] = "星期4";
        strings[4] = "星期5";
        strings[5] = "星期6";
        strings[6] = "星期7";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_number_picker, container, false);
        ButterKnife.bind(this, view);


        assignViews();
        return view;
    }

    /*@NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.create();
    }*/

    private void assignViews() {
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setDisplayedValues(strings);
        numberPicker.setMaxValue(6);
        numberPicker.setMinValue(0);
        numberPicker.setValue(3);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.i(TAG, "onValueChange: i=" + i + "; i1=" + i1);
            }
        });
        numberPicker2.setMaxValue(23);
        numberPicker2.setMinValue(0);
        numberPicker2.setValue(15);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.i(TAG, "onValueChange: i=" + i + "; i1=" + i1);
            }
        });
        numberPicker3.setMaxValue(59);
        numberPicker3.setMinValue(0);
        numberPicker3.setValue(15);
        numberPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.i(TAG, "onValueChange: i=" + i + "; i1=" + i1);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setWindowSize(0.9, 0.8);
    }

    private void setWindowSize(double width, double height) {
        getDialog().setCancelable(false);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        params.width = (int) (metrics.widthPixels * width);
        params.height = (int) (metrics.heightPixels * height);
        window.setAttributes(params);
    }

    @OnClick(R.id.picker_btn_sure)
    public void onClick() {
        dismiss();
    }
}
