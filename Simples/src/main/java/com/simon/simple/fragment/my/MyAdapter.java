package com.simon.simple.fragment.my;

import android.content.Context;
import android.view.ViewGroup;

import com.simon.simple.fragment.base.BaseAdapter;
import com.simon.simple.fragment.base.BaseViewHolder;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyAdapter extends BaseAdapter<String> {

    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new MyViewHolder(context, parent, viewType);
    }
}
