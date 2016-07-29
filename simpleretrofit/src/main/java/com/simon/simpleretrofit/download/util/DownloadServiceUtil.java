package com.simon.simpleretrofit.download.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.simon.simpleretrofit.download.broadcastreceiver.DownloadProgressReceiver;
import com.simon.simpleretrofit.download.model.DownloadItem;
import com.simon.simpleretrofit.download.service.ServiceProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 下载管理类（下载、文件处理）
 * Created by xw on 2016/7/27.
 */
public class DownloadServiceUtil {
    public static String TAG = DownloadServiceUtil.class.getSimpleName();

    //路径
    public static final String FILE_STORE_PATH = Environment.getExternalStorageDirectory() + File.separator + "simple";
    //
    public static final String APK_NAME = "download_file.apk";

    //缓存大小
    private static final int READ_MAX_SIZE = 1024 * 4;
    private DownloadItem downloadItem;

    private static DownloadServiceUtil instance = null;

    private DownloadServiceUtil() {
    }

    public static DownloadServiceUtil getInstance() {
        if (instance == null) {
            synchronized (DownloadServiceUtil.class) {
                if (instance == null) {
                    instance = new DownloadServiceUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 下载apk文件
     *
     * @param url
     */
    public void downloadApk(final Context context, String url) {
        //        subscribeOn()主要改变的是订阅者的线程，即call()执行的线程
        //        ObserveOn()主要改变的是发送的线程，即onNext()执行的线程
        ServiceProvider.getInstance().getRetrofitService()
                .getDownloadService()
                .download(url)
                //订阅者的线程，因为是网络请求，耗时操作，放在子线程中
                .subscribeOn(Schedulers.io())
                //                .subscribeOn(AndroidSchedulers.mainThread())
                //观察的线程，因为写文件，耗时操作，放在子线程中
                .observeOn(Schedulers.io())
                //                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i(TAG, "onNext: ");
                        //写文件
                        boolean writtenToDisk = writeResponseBodyToDisk(context, responseBody);
                        if (writtenToDisk) {
                            onSmartInstall(context, FILE_STORE_PATH, APK_NAME);
                        }
                    }
                });
    }

    /**
     * 写文件
     *
     * @param context
     * @param body
     * @return
     */
    private boolean writeResponseBodyToDisk(Context context, ResponseBody body) {
        try {
            initFilePath(FILE_STORE_PATH);
            File outputFile = new File(FILE_STORE_PATH, APK_NAME);

            Log.i(TAG, "writeResponseBodyToDisk: outputFile.length()=" + outputFile.length());

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[READ_MAX_SIZE];

                long fileSize = body.contentLength();

                //本地下载的文件和读取的文件大小一致，则直接进行安装，不用再下载了
                if (fileSize == outputFile.length()) {
                    Log.i(TAG, "writeResponseBodyToDisk: 已经下载完毕，直接进行安装吧~~~");
                    return true;
                }

                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(outputFile);
                //断点续传可能用到的方法
                //                long downloaded = futureStudioIconFile.length();
                //                inputStream.skip(downloaded);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;

                    updateDownloadItem(fileSize, fileSizeDownloaded);
                    //Modified By xw at 2016/7/28 Explain：因为是在io线程里面做操作，所以不能直接用listener回调改变控件，而是用广播的方式
                    sendIntent(context, downloadItem);
                    Thread.sleep(1);
                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 调用系统自动安装
     *
     * @param context
     * @param fileStorePath
     * @param apkName
     */
    public void onSmartInstall(Context context, String fileStorePath, String apkName) {
        if (TextUtils.isEmpty(fileStorePath) || TextUtils.isEmpty(apkName)) {
            Toast.makeText(context, "请选择安装包！", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = Uri.fromFile(new File(fileStorePath, apkName));
        Intent localIntent = new Intent(Intent.ACTION_VIEW);
        localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(localIntent);
    }

    private void updateDownloadItem(long fileSize, long fileSizeDownloaded) {
        if (downloadItem == null) {
            downloadItem = new DownloadItem();
        }
        downloadItem.setCurrentFileSize(fileSizeDownloaded);
        downloadItem.setTotalFileSize(fileSize);
        downloadItem.setProgress(transferProgress(fileSize, fileSizeDownloaded));
    }

    private float transferProgress(long fileSize, long fileSizeDownloaded) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String progress = decimalFormat.format(fileSizeDownloaded * 1.0 / fileSize);
        return Float.parseFloat(progress);
    }

    /**
     * 如果文件夹不存在，则创建
     */
    private void initFilePath(String path) {
        File fileDirectory = new File(path);
        if (!fileDirectory.exists() || !fileDirectory.isDirectory()) {
            fileDirectory.mkdirs();
        }
    }

    /**
     * -------------------------通过广播将下载的进度传给UI------------------------------------
     **/
    private LocalBroadcastManager localBroadcastManager;
    private Intent intent;

    private void sendIntent(Context context, DownloadItem downloadItem) {
        initIntent(context);
        //        使用LocalBroadcastManager有如下好处：
        //        1、发送的广播只会在自己App内传播，不会泄露给其他App，确保隐私数据不会泄露
        //        2、其他App也无法向你的App发送该广播，不用担心其他App会来搞破坏
        //        3、比系统全局广播更加高效

        //        和系统广播使用方式类似：
        //        先通过LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this); 获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
        intent.putExtra(DownloadProgressReceiver.DOWNLOAD_ITEM_EXTRA, downloadItem);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void initIntent(Context context) {
        if (localBroadcastManager == null) {
            localBroadcastManager = LocalBroadcastManager.getInstance(context);
        }
        if (intent == null) {
            intent = new Intent();
            intent.setAction(DownloadProgressReceiver.DOWNLOAD_PROGRESS_ACTION);
        }
    }

}
