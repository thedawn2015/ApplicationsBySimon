package com.simon.simple.fragment.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.fragment.my.MyAdapter;
import com.simon.simple.recyclerview.base.ListOperation;
import com.simon.simple.recyclerview.base.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * desc: BaseAdapter
 * author: xw
 * time: 2016/12/14
 */
public abstract class BaseAdapter<ITEM> extends RecyclerView.Adapter<BaseViewHolder> implements ListOperation<ITEM> {
    public static final String TAG = BaseAdapter.class.getSimpleName();

    private Context context;
    private List<ITEM> valueList;

    /**
     * 接口，通过回调分发点击事件
     */
    private OnItemClickListener<ITEM> onItemClickListener;

    public BaseAdapter(Context context) {
        LogUtil.i(TAG, "BaseAdapter: ");
        this.context = context;
        if (valueList == null) {
            valueList = new ArrayList<>();
        }
    }

    protected abstract BaseViewHolder createViewHolder(Context context, ViewGroup parent, int viewType);

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.i(TAG, "onCreateViewHolder: ");
        return createViewHolder(parent.getContext(), parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return MyAdapter.TYPE_TITLE;
        }else{
            return MyAdapter.TYPE_NAME;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        LogUtil.i(TAG, "onBindViewHolder: ");
        holder.bind(valueList.get(position), position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

    public void setOnItemClickListener(OnItemClickListener<ITEM> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

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
