package com.simon.simple.fragment.my;

import android.content.Context;
import android.view.ViewGroup;

import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.fragment.base.BaseAdapter;
import com.simon.simple.fragment.base.BaseViewHolder;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyAdapter extends BaseAdapter<String> {
    public static final String TAG = MyAdapter.class.getSimpleName();

    public static final int TYPE_TITLE = 101;
    public static final int TYPE_NAME = 102;

    public MyAdapter(Context context) {
        super(context);
        LogUtil.i(TAG, "MyAdapter: ");
    }

    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                LogUtil.i(TAG, "createViewHolder TYPE_TITLE: ");
                return new MyViewHolder2(context, parent);
            case TYPE_NAME:
                LogUtil.i(TAG, "createViewHolder TYPE_NAME: ");
                return new MyViewHolder(context, parent);
            default:
                LogUtil.i(TAG, "createViewHolder default: ");
                return new MyViewHolder(context, parent);
        }

    }
}
