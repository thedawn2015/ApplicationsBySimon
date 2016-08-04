package com.simon.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simon.simple.download.DownloadActivity;
import com.simon.simple.toobar.ToolbarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();

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

        test();
    }

    EditText edit_text;

    private void test() {
        /*SharedPreferencesUtil.put(this, "tes", "hehe");
        Map<String, Object> map = (Map<String, Object>) SharedPreferencesUtil.getAll(this);
        LogUtil.i(TAG, "test: map = " + map.get("tes"));
        String s = (String) SharedPreferencesUtil.get(this, "tes", "no");
        LogUtil.i(TAG, "test: s = " + s);
        SharedPreferencesUtil.remove(this, "tes");*/

        /*shot = (ImageView) findViewById(R.id.shot);
        Bitmap bitmap = ScreenUtil.snapShotWithoutStatusBar(MainActivity.this);
        shot.setImageBitmap(bitmap);*/

        /*String path = SDCardUtil.getRootDirectoryPath();
        LogUtil.i(TAG, "test: path = " + path);
        String cPath = SDCardUtil.getSDCardPath();
        LogUtil.i(TAG, "test: cpath = " + cPath);

        boolean en = SDCardUtil.isSDCardEnable();
        LogUtil.i(TAG, "test: en = " + en);

        long all = SDCardUtil.getSDCardAllSize();
        LogUtil.i(TAG, "test: all = " + all);

        String s = Environment.getExternalStorageState() + File.separator + "simple";
        long free = SDCardUtil.getFreeBytes(s);
        LogUtil.i(TAG, "test: free = " + free);*/

        /*String name = AppUtil.getAppName(this);
        LogUtil.i(TAG, "test: name = " + name);

        String vName = AppUtil.getVersionName(this);
        LogUtil.i(TAG, "test: vName = " + vName);*/

        /*int dp2px = DensityUtil.dp2px(this, 100);
        LogUtil.i(TAG, "test: dp2px=" + dp2px);
        int sp2px = DensityUtil.sp2px(this, 100);
        LogUtil.i(TAG, "test: sp2px=" + sp2px);
        int px2dp = DensityUtil.px2dp(this, 100);
        LogUtil.i(TAG, "test: px2dp=" + px2dp);
        int px2sp = DensityUtil.px2sp(this, 100);
        LogUtil.i(TAG, "test: px2sp=" + px2sp);

        boolean isNetConnected = NetUtil.isNetConnected(this);
        LogUtil.i(TAG, "test: isNetConnected = " + isNetConnected);
        boolean isNetWifi = NetUtil.isNetWifi(this);
        LogUtil.i(TAG, "test: isNetWifi = " + isNetWifi);
        //        NetUtil.openSetting(this);
        edit_text = (EditText) findViewById(R.id.edit_text);*/

        /*new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                KeyboardUtil.openKeybord(edit_text, MainActivity.this);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                KeyboardUtil.closeKeybord(edit_text, MainActivity.this);
            }
        }.start();*/
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
                ToolbarActivity.launch(MainActivity.this);
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
