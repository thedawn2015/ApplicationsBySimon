package com.simon.sample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simon.baseandroid.listener.IViewListener;
import com.simon.sample.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements IViewListener {
    public static final String TAG = MyFragment.class.getSimpleName();


    private View mView;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        mView = view;
        ButterKnife.bind(this, view);

        initData();
        assignViews();
        return view;
    }

    @Override
    public void assignViews() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void bindData() {

    }

    @Override
    public void refreshViews() {

    }

}
