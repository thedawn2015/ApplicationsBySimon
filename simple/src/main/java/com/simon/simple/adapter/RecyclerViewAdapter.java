package com.simon.simple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simon.simple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheDawn on 2016/7/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private final static int ITEM_TYPE_HEAD = 0;
    private final static int ITEM_TYPE_CONTENT = 1;
    private final static int ITEM_TYPE_BOTTOM = 2;

    private Context context;
    private List<String> adapterList;

    private int headCount = 1;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        adapterList = new ArrayList<>();
    }

    public void addItems(List<String> items) {
        int startIndex = adapterList.size();
        adapterList.addAll(items);
        notifyItemRangeChanged(startIndex, adapterList.size());
    }

    public void clear() {
        adapterList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
//        int itemCount = getItemCount();
        if (headCount != 0 && position < headCount) {
            return ITEM_TYPE_HEAD;
        }
//        else if (mBottomCount != 0 && position >= (headCount + dataItemCount)) {
//            //底部View
//            return ITEM_TYPE_BOTTOM;
//        }
        else {
            return ITEM_TYPE_CONTENT;
        }
//        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_HEAD) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.fragment_simple_condition, parent, false);
            return new HeaderViewHolder(view);
        } else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_recycler_view_adapter, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.bindViews(adapterList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return headCount + adapterList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }

        public void bindViews(String item) {
            textView.setText(item);
        }
    }

    /**
     * Header
     */
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
