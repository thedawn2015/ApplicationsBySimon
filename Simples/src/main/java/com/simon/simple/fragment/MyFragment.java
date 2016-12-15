package com.simon.simple.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.simon.baseandroid.listener.IViewListener;
import com.simon.simple.R;
import com.simon.simple.fragment.my.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements IViewListener {
    public static final String TAG = MyFragment.class.getSimpleName();

    @BindView(R.id.my_edit_title)
    EditText myEditTitle;
    @BindView(R.id.my_recycler_view_content)
    RecyclerView myRecyclerViewContent;

    private MyAdapter adapter;
    private List<String> stringList;

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
        adapter = new MyAdapter(getContext());
        myRecyclerViewContent.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerViewContent.setAdapter(adapter);
        refreshData(3);
        adapter.addItems(stringList);

        myEditTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int num = Integer.parseInt(myEditTitle.getText().toString());
                refreshData(num % 5 + 3);
                adapter.removeAll();
                adapter.addItems(stringList);
            }
        });
    }

    @Override
    public void initData() {
        stringList = new ArrayList<>();
    }

    @Override
    public void bindData() {

    }

    @Override
    public void refreshViews() {

    }

    private void refreshData(int nums) {
        stringList.clear();
        for (int i = 0; i < nums; i++) {
            stringList.add("num=" + i);
        }
    }
}
