package com.simon.simple.download;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.simon.simple.network.NetworkUtil;

/**
 * 对话框的工具类
 * Created by xw on 2016/8/2.
 */
public class DialogUtil {

    /**
     * 显示更新对话框
     *
     * @param context
     * @param title
     * @param content
     */
    public static void showUpdateDialog(final Context context, String title, String content, final String url, final String FILE_PATH,
                                        final String FILE_NAME, final ProgressListener progressListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startUpdate(context, FILE_PATH, FILE_NAME, progressListener, url);
                    }
                })
                .setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

    /**
     * 开始更新
     *
     * @param context
     * @param FILE_PATH
     * @param FILE_NAME
     * @param progressListener
     * @param url
     */
    private static void startUpdate(Context context, String FILE_PATH, String FILE_NAME, ProgressListener progressListener, String url) {
        if (NetworkUtil.isNetwordConnected(context)) {

        }


        new ProgressDownloader.Builder(context)
                .setStoragePath(FILE_PATH)
                .setStorageFileName(FILE_NAME)
                .setProgressListener(progressListener)
                .setUrl(url)
                .startTask();
    }

}
