package com.simon.simpleretrofit.download.util;

import android.os.Environment;
import android.util.Log;

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
    public void downloadApk(String url) {

        ServiceProvider.getInstance().getRetrofitService()
                .getDownloadService()
                .download(url)
                //子线程中进行下载
                .observeOn(Schedulers.computation())
                //                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
                        boolean writtenToDisk = writeResponseBodyToDisk(responseBody);
                    }
                });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS), "file.apk");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

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
}
