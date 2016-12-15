package com.simon.sample.recyclerview.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.simon.baseandroid.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter 基类
 * 适用于只有单个Item的RecyclerView.
 * Created by xw on 2016/8/24.
 */
public abstract class BaseAdapter<ITEM> extends RecyclerView.Adapter<BaseViewHolder>
        implements ListOperation<ITEM> {
    public static String TAG = BaseAdapter.class.getSimpleName();

    /**
     * 装载了每个Item的Value的列表
     */
    private List<ITEM> valueList;
    /**
     * 接口，通过回调分发点击事件
     */
    private OnItemClickListener<ITEM> onItemClickListener;

    /**
     * 初始化list
     */
    public BaseAdapter() {
        valueList = new ArrayList<>();
    }

    /**
     * 生成ViewHolder
     *
     * @param context
     * @param parent
     * @return
     */
    protected abstract BaseViewHolder createViewHolder(Context context, ViewGroup parent);

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.i(TAG, "onCreateViewHolder: ");
        return createViewHolder(parent.getContext(), parent);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        LogUtil.i(TAG, "onBindViewHolder: ");
        holder.setData(valueList.get(position), position, onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener<ITEM> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }


    /**----------------------------------数据操作------------------------------**/
    /**
     * 添加
     *
     * @param items
     */
    @Override
    public void addItems(List<ITEM> items) {
        int index = valueList.size();
        valueList.addAll(items);
        notifyItemRangeInserted(index, items.size());
    }

    @Override
    public void addItem(ITEM item) {
        int index = valueList.size();
        valueList.add(item);
        notifyItemInserted(index);
    }

    @Override
    public void removeAll() {
        int index = valueList.size();
        valueList.clear();
        notifyItemRangeRemoved(0, index);
    }

    @Override
    public void removeItem(ITEM item) {
        int index = valueList.indexOf(item);
        valueList.remove(item);
        notifyItemRemoved(index);
    }
}
