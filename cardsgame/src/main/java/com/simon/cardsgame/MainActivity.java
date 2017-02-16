package com.simon.cardsgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simon.baseandroid.BaseActivity;
import com.simon.cardsgame.adapter.CardTypeRecyclerViewAdapter;
import com.simon.cardsgame.datas.CardType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_recycler_view)
    RecyclerView mainRecyclerView;

    private List<CardType> cardTypeList;
    private CardTypeRecyclerViewAdapter cardTypeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDatas();
        assingViews();
    }

    private void initDatas() {
        cardTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CardType cardType = new CardType();
            cardType.setTypeName("name " + i);
            cardTypeList.add(cardType);
        }
    }

    private void assingViews() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        mainRecyclerView.setLayoutManager(layoutManager);

        cardTypeRecyclerViewAdapter = new CardTypeRecyclerViewAdapter(MainActivity.this);
        mainRecyclerView.setAdapter(cardTypeRecyclerViewAdapter);

        cardTypeRecyclerViewAdapter.addItems(cardTypeList);
        cardTypeRecyclerViewAdapter.setOnCardTypeItemClickListener(
                new CardTypeRecyclerViewAdapter.OnCardTypeItemClickListener() {
                    @Override
                    public void onItemClick() {

                    }
                });
    }
}
