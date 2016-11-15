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
 * Right Fragment
 * Created by xw on 2016/8/5
 */
public class RightFragment extends Fragment {
    public static String TAG = RightFragment.class.getSimpleName();

    @BindView (R.id.fragment_right_text)
    TextView fragmentRightText;

    private static RightFragment instance = null;

    private RightFragment() {
    }

    public static RightFragment getInstance() {
        if (instance == null) {
            synchronized (RightFragment.class) {
                if (instance == null) {
                    instance = new RightFragment();
                }
            }
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreateView: Right");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        ButterKnife.bind(this, view);
        fragmentRightText.setText("right right");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate: Right");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG, "onActivityCreated: Right");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG, "onAttach: Right");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy: Right");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, "onPause: Right");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG, "onStop: Right");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume: Right");
    }

}
