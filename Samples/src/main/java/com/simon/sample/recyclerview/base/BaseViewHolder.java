package com.simon.sample.recyclerview.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * ViewHolder基类
 * <p/>
 * Created by lypeer on 16-5-27.
 */
public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
    public static String TAG = BaseViewHolder.class.getSimpleName();

    public BaseViewHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));

        //这里使用了ButterKnife来进行控件的绑定
        ButterKnife.bind(this, itemView);
//        LogUtil.i(TAG, "BaseViewHolder: ");
    }

    /**
     * 方便其子类进行一些需要Context的操作.
     *
     * @return 调用者的Context
     */
    public Context getContext() {
        return itemView.getContext();
    }

    /**
     * 抽象方法，绑定数据.
     * 让子类自行对数据和view进行绑定
     *
     * @param itemValue Item的数据
     * @param position  当前item的position
     * @param listener  点击事件监听者
     */
    protected abstract void bindData(ITEM itemValue, int position, OnItemClickListener listener);

    /**
     * 用于传递数据和信息
     *
     * @param itemValue
     * @param position
     * @param listener
     */
    public void setData(ITEM itemValue, int position, OnItemClickListener listener) {
        bindData(itemValue, position, listener);
    }
}