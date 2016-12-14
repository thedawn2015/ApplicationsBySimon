package com.simon.simple.fragment.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simon.simple.recyclerview.base.OnItemClickListener;

import butterknife.ButterKnife;

/**
 * desc: Base ViewHolder
 * author: xw
 * time: 2016/12/14
 */
public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {

    public BaseViewHolder(Context context, ViewGroup parent, int layout) {
        super(LayoutInflater.from(context).inflate(layout, parent, false));
        ButterKnife.bind(this, itemView);
    }

    /**
     * 方便其子类进行一些需要Context的操作.
     *
     * @return 调用者的Context
     */
    public Context getContext() {
        return itemView.getContext();
    }

    protected abstract void bind(ITEM item, int position, OnItemClickListener listener);

}
