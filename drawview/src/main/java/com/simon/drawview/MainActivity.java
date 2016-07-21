package com.simon.drawview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.drawview.model.PieData;
import com.simon.drawview.widget.SimpleViewDraw;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_simple_view)
    SimpleViewDraw mainSimpleView;

    private List<PieData> pieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPieData();
        mainSimpleView.setData(pieDataList);
        mainSimpleView.setStartAngle(10);
    }

    private void initPieData() {
        pieDataList = new ArrayList<>();
        PieData pieData1 = new PieData("i1", 20);
        PieData pieData2 = new PieData("i2", 5);
        PieData pieData3 = new PieData("i3", 35);
        PieData pieData4 = new PieData("i4", 10);
        PieData pieData5 = new PieData("i4", 30);
        pieDataList.add(pieData1);
        pieDataList.add(pieData2);
        pieDataList.add(pieData3);
        pieDataList.add(pieData4);
        pieDataList.add(pieData5);
    }
}
