package com.simon.simple.titlebar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simon.simple.R;
import com.simon.baseandroid.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 左边的Fragment
 * Created by xw on 2016/8/5
 */
public class LeftFragment extends Fragment {
    public static String TAG = LeftFragment.class.getSimpleName();

    @BindView (R.id.fragment_left_text)
    TextView fragmentLeftText;

    private static LeftFragment instance = null;

    private LeftFragment() {
    }

    public static LeftFragment getInstance() {
        if (instance == null) {
            synchronized (LeftFragment.class) {
                if (instance == null) {
                    instance = new LeftFragment();
                }
            }
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreateView: Left");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left, container, false);

        ButterKnife.bind(this, view);
        fragmentLeftText.setText("left Left left");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate: Left");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG, "onActivityCreated: Left");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG, "onAttach: Left");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy: Left");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, "onPause: Left");
    }


    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG, "onStop: Left");
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume: Left");
    }
}
