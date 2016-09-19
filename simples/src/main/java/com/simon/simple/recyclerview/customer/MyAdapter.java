package com.simon.simple.recyclerview.customer;

import android.content.Context;
import android.view.ViewGroup;

import com.simon.simple.base.util.LogUtil;
import com.simon.simple.recyclerview.base.BaseAdapter;
import com.simon.simple.recyclerview.base.BaseViewHolder;

/**
 * 自定义的Adapter
 * Created by xw on 2016/9/19.
 */
public class MyAdapter extends BaseAdapter<String> {
    public static String TAG = MyAdapter.class.getSimpleName();

    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        LogUtil.i(TAG, "createViewHolder: ");
        return new MyViewHolder(context, parent);
    }
}
