package com.simon.simple.recyclerview.customer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simon.simple.R;
import com.simon.simple.recyclerview.base.BaseViewHolder;
import com.simon.simple.recyclerview.base.OnItemClickListener;

import butterknife.BindView;

/**
 * 自定义的ViewHolder
 * Created by xw on 2016/9/19.
 */
public class MyViewHolder extends BaseViewHolder<String> {

    @BindView (R.id.text_name)
    TextView textName;

    public MyViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.holder_name);
    }

    @Override
    protected void bindData(final String itemValue, final int position, final OnItemClickListener listener) {
        if (itemValue != null) {
            textName.setText(itemValue);
        }
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(itemValue, view.getId(), position);
                }
            }
        });
    }
}
