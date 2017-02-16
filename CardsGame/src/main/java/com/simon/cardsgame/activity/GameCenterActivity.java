package com.simon.cardsgame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simon.baseandroid.BaseActivity;
import com.simon.cardsgame.R;
import com.simon.cardsgame.adapter.CardTypeRecyclerViewAdapter;
import com.simon.cardsgame.model.CardType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc：游戏中心
 * author：simon
 * date：2017/2/16
 */
public class GameCenterActivity extends BaseActivity {
    public static final String TAG = GameCenterActivity.class.getSimpleName();

    @BindView(R.id.game_recycler_view)
    RecyclerView gameRecyclerView;

    private List<CardType> cardTypeList;
    private CardTypeRecyclerViewAdapter cardTypeRecyclerViewAdapter;

    /**
     * 启动Activity
     *
     * @param context
     */
    public static void launchToActivity(Context context) {
        Intent intent = new Intent(context, GameCenterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_center);
        ButterKnife.bind(this);

        initData();
        assignViews();
        bindData();
        refreshViews();
    }

    @Override
    public void initData() {
        cardTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CardType cardType = new CardType();
            cardType.setTypeName("name " + i);
            cardTypeList.add(cardType);
        }
    }

    @Override
    public void assignViews() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        gameRecyclerView.setLayoutManager(layoutManager);

        cardTypeRecyclerViewAdapter = new CardTypeRecyclerViewAdapter(this);
        gameRecyclerView.setAdapter(cardTypeRecyclerViewAdapter);

        cardTypeRecyclerViewAdapter.addItems(cardTypeList);
        cardTypeRecyclerViewAdapter.setOnCardTypeItemClickListener(
                new CardTypeRecyclerViewAdapter.OnCardTypeItemClickListener() {
                    @Override
                    public void onItemClick() {

                    }
                });
    }

    @Override
    public void bindData() {

    }

    @Override
    public void refreshViews() {

    }
}
