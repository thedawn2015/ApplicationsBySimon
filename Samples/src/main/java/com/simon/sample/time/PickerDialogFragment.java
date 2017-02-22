package com.simon.sample.time;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;

import com.simon.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc: 选择对话框
 * author: xw
 * time: 2017/2/22
 */
public class PickerDialogFragment extends DialogFragment {
    public static final String TAG = PickerDialogFragment.class.getSimpleName();

    @BindView(R.id.picker_view)
    PickerView pickerView;
    @BindView(R.id.picker_timer_picker)
    TimePicker pickerTimerPicker;

    private List<String> list;

    public static PickerDialogFragment newInstance() {
        Bundle args = new Bundle();
        PickerDialogFragment fragment = new PickerDialogFragment();
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
        list = new ArrayList<>();
        list.add("星期1");
        list.add("星期2");
        list.add("星期3");
        list.add("星期4");
        list.add("星期5");
        list.add("星期6");
        list.add("星期7");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_picker_view, container, false);
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
        pickerView.setData(list);
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                Log.i(TAG, "onSelect: text=" + text);
            }
        });

        pickerTimerPicker.setIs24HourView(true);
        pickerTimerPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeChanged: hour=" + hour + "; minute=" + minute);
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
}
