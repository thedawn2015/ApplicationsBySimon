package com.simon.simple.download;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.simon.base.util.NetUtil;
import com.simon.base.util.ToastUtil;

/**
 * 对话框的工具类
 * Created by xw on 2016/8/2.
 */
public class DialogUtil {
    public static String TAG = DialogUtil.class.getSimpleName();

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
    private static void startUpdate(final Context context, final String FILE_PATH, final String FILE_NAME, final ProgressListener progressListener, final String url) {
        if (!NetUtil.isNetConnected(context)) {
            ToastUtil.showShort(context, "网络没有连接");
            //            openNetSettingDialog(context);
            return;
        }
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case -1:
                        downloadIt(context, FILE_PATH, FILE_NAME, progressListener, url);
                        break;
                    case -2:
                        dialogInterface.dismiss();
                        break;
                    case -3:
                        dialogInterface.dismiss();
                        break;
                }
            }
        };
        if (!NetUtil.isNetWifi(context)) {
            notWifiDialog(context, onClickListener);
        } else {
            downloadIt(context, FILE_PATH, FILE_NAME, progressListener, url);
        }
    }

    /**
     * 下载
     *
     * @param context
     * @param FILE_PATH
     * @param FILE_NAME
     * @param progressListener
     * @param url
     */
    private static void downloadIt(Context context, String FILE_PATH, String FILE_NAME, ProgressListener progressListener, String url) {
        new ProgressDownloader.Builder(context)
                .setStoragePath(FILE_PATH)
                .setStorageFileName(FILE_NAME)
                .setProgressListener(progressListener)
                .setUrl(url)
                .startTask();
    }

    /**
     *
     */

    /**
     * 是否去设置网络
     *
     * @param context
     * @param onClickListener
     */
    private static void notWifiDialog(final Context context, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setTitle("温馨提示")
                .setMessage("当前网络不是Wifi，是否确定下载")
                .setCancelable(false)
                .setPositiveButton("有钱任性", onClickListener)
                .setNegativeButton("还是算了", onClickListener)
                .create()
                .show();
    }

    /**
     * 是否去设置网络
     *
     * @param context
     */
    private static void openNetSettingDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle("去设置网络")
                .setMessage("去设置网络")
                .setCancelable(false)
                .setPositiveButton("去吧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NetUtil.openNetSetting((Activity) context);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("算了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

}
