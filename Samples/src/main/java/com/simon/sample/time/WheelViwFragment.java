package com.simon.sample.time;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.simon.baseandroid.widget.WheelView;
import com.simon.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc: 选择对话框
 * author: xw
 * time: 2017/2/22
 */
public class WheelViwFragment extends Fragment {
    public static final String TAG = WheelViwFragment.class.getSimpleName();

    @BindView(R.id.number_picker)
    WheelView numberPicker;
    @BindView(R.id.number_picker2)
    WheelView numberPicker2;
    @BindView(R.id.number_picker3)
    WheelView numberPicker3;
    @BindView(R.id.picker_btn_sure)
    Button pickerBtnSure;


    private List<String> strings;

    public static WheelViwFragment newInstance() {
        Bundle args = new Bundle();
        WheelViwFragment fragment = new WheelViwFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        strings = new ArrayList<>();
        strings.add("星期1");
        strings.add("星期2");
        strings.add("星期3");
        strings.add("星期4");
        strings.add("星期5");
        strings.add("星期6");
        strings.add("星期7");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_number_picker, container, false);
        ButterKnife.bind(this, view);


        assignViews();
        return view;
    }


    private void assignViews() {
        numberPicker.setItems(strings);
        numberPicker.setOffset(1);
        numberPicker.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.i(TAG, "onSelected: selectedIndex=" + selectedIndex + ": " + item);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setWindowSize(0.9, 0.8);
    }

    private void setWindowSize(double width, double height) {
        /*getDialog().setCancelable(false);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        params.width = (int) (metrics.widthPixels * width);
        params.height = (int) (metrics.heightPixels * height);
        window.setAttributes(params);*/
    }

    @OnClick(R.id.picker_btn_sure)
    public void onClick() {
//        dismiss();
    }
}
