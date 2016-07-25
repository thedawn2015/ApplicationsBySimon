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
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int ITEM_TYPE_HEADER = 0;
    private final static int ITEM_TYPE_CONTENT = 1;
    private final static int ITEM_TYPE_BOTTOM = 2;

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> adapterList;

    private int mHeaderCount = 1;//头部View个数
    private int mBottomCount = 1;//底部View个数

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        adapterList = new ArrayList<>();
    }

    /**
     * -----------------------------数据处理-----------------------------
     **/
    public void addItems(List<String> items) {
        int startIndex = adapterList.size() + mHeaderCount;
        adapterList.addAll(items);
        notifyItemRangeChanged(startIndex, items.size());
    }

    public void clear() {
        int itemCount = adapterList.size();
        adapterList.clear();
        notifyItemRangeChanged(1, itemCount);
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_HEADER) {
            view = mLayoutInflater.inflate(R.layout.fragment_adapter_header, parent, false);
            return new HeaderViewHolder(view);
        } else if (viewType == ITEM_TYPE_CONTENT) {
            view = mLayoutInflater.inflate(R.layout.fragment_adapter_item, parent, false);
            return new ContentViewHolder(view);
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            view = mLayoutInflater.inflate(R.layout.fragment_adapter_footer, parent, false);
            return new BottomViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            viewHolder.bindViews(adapterList.get(position - mHeaderCount));
        } else if (holder instanceof BottomViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentCount() + mBottomCount;
    }

    private int getContentCount() {
        return adapterList.size();
    }

    /**
     * Content
     */
    class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ContentViewHolder(View itemView) {
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

    /**
     * Bottom
     */
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
