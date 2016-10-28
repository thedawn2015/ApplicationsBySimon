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
import com.simon.base.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Center Fragment
 * Created by xw on 2016/8/5
 */
public class CenterFragment extends Fragment {
    public static String TAG = CenterFragment.class.getSimpleName();

    @BindView (R.id.fragment_center_text)
    TextView fragmentCenterText;

    private static CenterFragment instance = null;

    private CenterFragment() {
    }

    public static CenterFragment getInstance() {
        if (instance == null) {
            synchronized (CenterFragment.class) {
                if (instance == null) {
                    instance = new CenterFragment();
                }
            }
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreateView: Center");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        ButterKnife.bind(this, view);
        fragmentCenterText.setText("center center");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate: Center");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG, "onActivityCreated: Center");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG, "onAttach: Center");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy: Center");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, "onPause: Center");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG, "onStop: Center");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume: Center");
    }

}
