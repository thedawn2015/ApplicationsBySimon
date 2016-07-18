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

    private Context context;
    private List<String> adapterList;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_view_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindViews(adapterList.get(position));
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
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
}
