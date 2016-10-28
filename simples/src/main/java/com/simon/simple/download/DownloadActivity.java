package com.simon.simple.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.simon.simple.R;
import com.simon.base.util.LogUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadActivity extends AppCompatActivity {
    public static String TAG = DownloadActivity.class.getSimpleName();

    private static String FILE_NAME;
    private final static String FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "simple";

    @BindView (R.id.download_progressBar)
    ProgressBar downloadProgressBar;
    @BindView (R.id.download_text_current)
    TextView downloadTextCurrent;
    @BindView (R.id.download_text_total)
    TextView downloadTextTotal;
    @BindView (R.id.download_btn_download)
    Button downloadBtnDownload;
    @BindView (R.id.download_btn_pause)
    Button downloadBtnPause;
    @BindView (R.id.download_btn_continue)
    Button downloadBtnContinue;

    String url = "http://p.gdown.baidu.com/7411f966a1f97a54b97258af68408d47555af78b73e95e0916097d3fbaf41af04ec36851ddf9491164e25a775501e6ebdeda6b3a604fff46925c9b01230fd0b157503f8f5e8bebc0fa96a3e860a70f091915f81dc08141206311e3a2747a3d4f0b68b44aeb93e13d8abd77b891e5cbf2386464e8631a48906d04d0d55dea9f2aba204c7ae78326ec5bbce8cfd8721dea42886ec744f3a9590d76c72d5d5287a2808e27f43388e7ab804a14cffb02ed27748d47c5f40729c6fd2a6045c9d4e2ca646bc8175679b29dcdbcbe0a6f19893d83ebf025ca4bf6a2c1250aa4f4faf971b93036c538c78b18140a419dde2db1adda72cfdf446dc6ee3b3c544e3f724ede7c49b4dcbc5c281098efa12e9d1de60e8325446168af956c4d4624ca148deea6";
    //    String url = "http://apk.hiapk.com/appdown/com.tencent.qqpimsecure?em=5&p=android&f_name=%E6%89%8B%E6%9C%BA%E7%AE%A1%E5%AE%B6";

    private File storeFile;
    //目前文件的大小
    private long fileBytes;
    //文件的当前下载量
    private long downloadedBytes;
    //完整文件的大小
    private long totalBytes;
    //通过时间来控制显示的速度
    long lastMilles, currentMilles;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DownloadActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        UUID uuid = UUID.randomUUID();
        FILE_NAME = uuid.toString().replace("-", "").substring(0, 16) + ".apk";

        lastMilles = System.currentTimeMillis();
    }

    @OnClick ({R.id.download_btn_download, R.id.download_btn_pause, R.id.download_btn_continue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download_btn_download:
                checkFile();
                DialogUtil.showUpdateDialog(DownloadActivity.this, "发现新版本", "1、修复了一些bug\n2、优化一些功能",
                        url, FILE_PATH, FILE_NAME, progressListener);
                //                startDownload();
                break;
            case R.id.download_btn_pause:
                //                pauseDownload();
                //                Toast.makeText(this, "下载暂停", Toast.LENGTH_SHORT).show();
                //                LogUtil.i(TAG, "onClick: pauseDownload downloadedBytes=" + storeFile.length());
                break;
            case R.id.download_btn_continue:
                //                continueDownload();
                break;
        }
    }

    /**
     * 下载前先检查文件大小
     */
    private void checkFile() {
        storeFile = new File(FILE_PATH, FILE_NAME);
        LogUtil.i(TAG, "checkFile: FILE_NAME=" + FILE_NAME);
        fileBytes = storeFile.length();
        LogUtil.i(TAG, "checkFile: startDownload fileBytes=" + fileBytes);
    }

    private ProgressDownloader.Builder downloadBuilder;

    /**
     * 开始下载
     */
    public void startDownload() {
        checkFile();
        downloadBuilder = new ProgressDownloader.Builder(DownloadActivity.this)
                .setStoragePath(FILE_PATH)
                .setStorageFileName(FILE_NAME)
                .setProgressListener(progressListener)
                .setUrl(url)
                .startTask();
    }

    /**
     * 暂停下载
     */
    public void pauseDownload() {
        downloadBuilder.pauseTask();
    }

    /**
     * 继续下载
     */
    public void continueDownload() {
        checkFile();
        downloadBuilder.cotinueTask();
    }

    /**
     * 进度监听
     */
    ProgressListener progressListener = new ProgressListener() {
        @Override
        public void update(long currentLength, long totalLength, boolean isDownloaded) {
            // 注意加上断点的长度
            downloadedBytes = currentLength + fileBytes;
            totalBytes = totalLength + fileBytes;

            currentMilles = System.currentTimeMillis();
            //每隔500ms，刷新一次页面
            if (currentMilles - lastMilles > 500 || downloadedBytes == totalLength) {
                lastMilles = currentMilles;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadProgressBar.setProgress(transfer2Percent(downloadedBytes, totalBytes));
                        downloadTextCurrent.setText("已下载：" + downloadedBytes);
                        downloadTextTotal.setText("总大小：" + totalBytes);
                    }
                });
            }
            //如果下载完成，自动调用安装
            if (isDownloaded) {
                smartInstall(DownloadActivity.this, FILE_PATH, FILE_NAME);
            }

        }
    };

    /**
     * 转换百分比
     *
     * @param currentLength
     * @param totalLength
     * @return
     */
    public int transfer2Percent(long currentLength, long totalLength) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String progress = decimalFormat.format(currentLength * 1.0 / totalLength);
        float progressFloat = Float.parseFloat(progress);
        return (int) (progressFloat * 100);
    }

    /**
     * 调用系统自动安装
     *
     * @param context
     * @param fileStorePath
     * @param apkName
     */
    public void smartInstall(Context context, String fileStorePath, String apkName) {
        if (TextUtils.isEmpty(fileStorePath) || TextUtils.isEmpty(apkName)) {
            Toast.makeText(context, "请选择安装包！", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = Uri.fromFile(new File(fileStorePath, apkName));
        Intent localIntent = new Intent(Intent.ACTION_VIEW);
        localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(localIntent);
    }
}
