package com.simon.simple.fragment.my;

import android.content.Context;
import android.view.ViewGroup;

import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.R;
import com.simon.simple.fragment.base.BaseViewHolder;
import com.simon.simple.recyclerview.base.OnItemClickListener;

/**
 * desc: My
 * author: xw
 * time: 2016/12/14
 */
public class MyViewHolder2 extends BaseViewHolder<String> {
    public static final String TAG = MyViewHolder2.class.getSimpleName();


    public MyViewHolder2(Context context, ViewGroup parent) {
        super(context, parent, R.layout.adapter_holder_title);
        LogUtil.i(TAG, "MyViewHolder2: ");
    }

    @Override
    protected void bind(final String itemValue, final int position, final OnItemClickListener listener) {
        LogUtil.i(TAG, "bindData: ");
    }
}
