package com.simon.simple.recyclerview.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter 基类
 * 适用于只有单个Item的RecyclerView.
 * Created by xw on 2016/8/24.
 */
public abstract class BaseAdapter<ITEM> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ListOperate<ITEM> {

    /**
     * 装载了每个Item的Value的列表
     */
    private List<ITEM> mValueList;
    /**
     * 接口，通过回调分发点击事件
     */
    private OnItemClickListener<ITEM> mOnItemClickListener;

    /**
     * 初始化list
     */
    public BaseAdapter() {
        mValueList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(parent.getContext(), parent);
    }

    @SuppressWarnings ("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(mValueList.get(position), position, mOnItemClickListener);
    }

    public void setmOnItemClickListener(OnItemClickListener<ITEM> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mValueList.size();
    }

    /**
     * 生成ViewHolder
     *
     * @param context
     * @param parent
     * @return
     */
    protected abstract BaseViewHolder createViewHolder(Context context, ViewGroup parent);

    /**----------------------------------数据操作------------------------------**/
    /**
     * 添加
     *
     * @param items
     */
    @Override
    public void addItems(List<ITEM> items) {
        int index = mValueList.size();
        mValueList.addAll(items);
        notifyItemRangeInserted(index, items.size());
    }

    @Override
    public void addItem(ITEM item) {
        int index = mValueList.size();
        mValueList.add(item);
        notifyItemInserted(index);
    }

    @Override
    public void clear() {
        int index = mValueList.size();
        mValueList.clear();
        notifyItemRangeRemoved(0, index);
    }

    @Override
    public void removeItem(ITEM item) {
        int index = mValueList.indexOf(item);
        mValueList.remove(item);
        notifyItemRemoved(index);
    }
}
