package com.simon.simpleretrofit.download.util;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.simon.simpleretrofit.MainActivity;
import com.simon.simpleretrofit.download.service.ServiceProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by xw on 2016/7/27.
 */
public class DownloadServiceUtil {
    public static String TAG = DownloadServiceUtil.class.getSimpleName();

    private static final int READ_MAX_SIZE = 1024 * 4;

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
                    }
                });
    }

    private boolean writeResponseBodyToDisk(Context context, ResponseBody body) {
        try {
            File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS), "file.apk");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[READ_MAX_SIZE];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;

                    //Modified By xw at 2016/7/28 Explain：因为是在io线程里面做操作，所以不能直接用listener回调改变控件，而是用广播的方式
                    sendIntent(context, fileSizeDownloaded);

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
            return false;
        }
    }

    private void sendIntent(Context context, long fileSizeDownloaded) {
        //        使用LocalBroadcastManager有如下好处：
        //        1、发送的广播只会在自己App内传播，不会泄露给其他App，确保隐私数据不会泄露
        //        2、其他App也无法向你的App发送该广播，不用担心其他App会来搞破坏
        //        3、比系统全局广播更加高效

        //        和系统广播使用方式类似：
        //        先通过LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this); 获取实例
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(MainActivity.LOCAL_ACTION);
        intent.putExtra("current_size", fileSizeDownloaded);
        localBroadcastManager.sendBroadcast(intent);
    }
}
