package com.simon.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simon.simple.download.DownloadActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView (R.id.simple_btn_to_draw)
    Button simpleBtnToDraw;
    @BindView (R.id.simple_btn_to_animator)
    Button simpleBtnToAnimator;
    @BindView (R.id.simple_btn_to_toolbar)
    Button simpleBtnToToolbar;
    @BindView (R.id.simple_btn_to_recycler_view)
    Button simpleBtnToRecyclerView;
    @BindView (R.id.simple_btn_to_retrofit)
    Button simpleBtnToRetrofit;
    @BindView (R.id.simple_btn_to_rx)
    Button simpleBtnToRx;
    @BindView (R.id.simple_btn_to_download)
    Button simpleBtnToDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick ({R.id.simple_btn_to_draw, R.id.simple_btn_to_animator, R.id.simple_btn_to_toolbar,
            R.id.simple_btn_to_recycler_view, R.id.simple_btn_to_retrofit, R.id.simple_btn_to_rx,
            R.id.simple_btn_to_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simple_btn_to_draw:
                break;
            case R.id.simple_btn_to_animator:
                break;
            case R.id.simple_btn_to_toolbar:
                break;
            case R.id.simple_btn_to_recycler_view:
                break;
            case R.id.simple_btn_to_retrofit:
                break;
            case R.id.simple_btn_to_rx:
                break;
            case R.id.simple_btn_to_download:
                DownloadActivity.launch(MainActivity.this);
                break;
        }
    }
}
