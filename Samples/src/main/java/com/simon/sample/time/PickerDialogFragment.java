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
import android.widget.TimePicker;

import com.simon.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc:
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

        setWindowSize(0.8, 0.6);

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
    }

    private void setWindowSize(double width, double height) {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        params.width = (int) (metrics.widthPixels * width);
        params.height = (int) (metrics.heightPixels * height);
        window.setAttributes(params);
    }
}
