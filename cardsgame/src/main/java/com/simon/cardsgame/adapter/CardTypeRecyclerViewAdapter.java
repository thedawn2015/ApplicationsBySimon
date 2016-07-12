package com.simon.cardsgame.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.simon.cardsgame.R;
import com.simon.cardsgame.datas.CardType;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡片类型适配器
 * <p/>
 * Created by Simon on 2016/7/5.
 */
public class CardTypeRecyclerViewAdapter extends RecyclerView.Adapter<CardTypeRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<CardType> cardTypeList;
    private OnCardTypeItemClickListener onCardTypeItemClickListener;

    public CardTypeRecyclerViewAdapter(Context context) {
        this.context = context;
        if (cardTypeList == null) {
            cardTypeList = new ArrayList<>();
        }
    }

    /**--------------数据处理----------------------*/
    /**
     * 添加List
     *
     * @param items
     */
    public void addItems(List<CardType> items) {
        int index = cardTypeList.size();
        cardTypeList.addAll(index, items);
        notifyItemChanged(index);
    }

    /**
     * 添加一个
     *
     * @param item
     */
    public void addItem(CardType item) {
        cardTypeList.add(item);
        notifyItemInserted(cardTypeList.size());
    }

    /**
     * 清除
     */
    public void clear() {
        cardTypeList.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindViews(cardTypeList.get(position));
    }

    @Override
    public int getItemCount() {
        return cardTypeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private AnimatorSet mRightOutSet; // 右出动画
        private AnimatorSet mLeftInSet; // 左入动画

        private boolean mIsShowBack;

        View itemView;
        FrameLayout item_card_type_fl;
        FrameLayout fragment_fl_card_back;
        FrameLayout fragment_fl_card_front;
        TextView card_front_tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.item_card_type_fl = (FrameLayout) itemView.findViewById(R.id.item_card_type_fl);
            this.fragment_fl_card_back = (FrameLayout) itemView.findViewById(R.id.fragment_fl_card_back);
            this.fragment_fl_card_front = (FrameLayout) itemView.findViewById(R.id.fragment_fl_card_front);
            this.card_front_tv_content = (TextView) itemView.findViewById(R.id.card_front_tv_content);

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (onCardTypeItemClickListener != null) {
                        onCardTypeItemClickListener.onItemClick();
                    }*/
                    flipCard(fragment_fl_card_front, fragment_fl_card_back);
                }
            });

            // 设置动画
            setAnimators(itemView);
            // 设置镜头距离
//            setCameraDistance(fragment_fl_card_back, fragment_fl_card_front);
        }

        public void bindViews(CardType cardType) {
            this.card_front_tv_content.setText(cardType.getTypeName());
        }

        // 翻转卡片
        public void flipCard(View front, View back) {
            // 正面朝上
            if (!mIsShowBack) {
                mRightOutSet.setTarget(front);
                mLeftInSet.setTarget(back);
                mRightOutSet.start();
                mLeftInSet.start();
                mIsShowBack = true;
            } else { // 背面朝上
                mRightOutSet.setTarget(back);
                mLeftInSet.setTarget(front);
                mRightOutSet.start();
                mLeftInSet.start();
                mIsShowBack = false;
            }
        }

        // 设置动画
        private void setAnimators(final View view) {
            mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.anim_out);
            mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.anim_in);

            // 设置点击事件
            mRightOutSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    view.setClickable(false);
                }
            });
            mLeftInSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
//                  itemView.setClickable(true);
                }
            });
        }

        // 改变视角距离, 贴近屏幕
        private void setCameraDistance(View front, View back) {
            int distance = 16000;
            float scale = context.getResources().getDisplayMetrics().density * distance;
            front.setCameraDistance(scale);
            back.setCameraDistance(scale);
        }
    }

    public interface OnCardTypeItemClickListener {
        void onItemClick();
    }

    public void setOnCardTypeItemClickListener(OnCardTypeItemClickListener onCardTypeItemClickListener) {
        this.onCardTypeItemClickListener = onCardTypeItemClickListener;
    }
}
