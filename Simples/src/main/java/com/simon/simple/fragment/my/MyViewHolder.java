package com.simon.simple.fragment.my;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.R;
import com.simon.simple.fragment.base.BaseViewHolder;
import com.simon.simple.recyclerview.base.OnItemClickListener;

import butterknife.BindView;

/**
 * desc: My
 * author: xw
 * time: 2016/12/14
 */
public class MyViewHolder extends BaseViewHolder<String> {
    public static final String TAG = MyViewHolder.class.getSimpleName();

    @BindView(R.id.text_name)
    TextView textName;

    public MyViewHolder(Context context, ViewGroup parent) {
        super(context, parent, R.layout.adapter_holder_name);
        LogUtil.i(TAG, "MyViewHolder: ");
    }

    @Override
    protected void bind(final String itemValue, final int position, final OnItemClickListener listener) {
        LogUtil.i(TAG, "bindData: ");

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
