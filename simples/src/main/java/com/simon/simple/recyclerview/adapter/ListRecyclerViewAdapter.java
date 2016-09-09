package com.simon.simple.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xw on 2016/8/24.
 */
public abstract class ListRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, ITEM> extends RecyclerView.Adapter<VH>
        implements ListOperate<ITEM> {

    public List<ITEM> itemList;

    public ListRecyclerViewAdapter() {
        itemList = new ArrayList<>();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void addItem(ITEM item) {
        int size = itemList.size();
        itemList.add(item);
        notifyItemRangeInserted(size, 1);
    }

}
