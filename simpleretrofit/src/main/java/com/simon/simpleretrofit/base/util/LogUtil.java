package com.simon.simpleretrofit.base.util;

import android.util.Log;

/**
 * Log工具类
 * <p/>
 * Created by xw on 2016/7/29.
 */
public class LogUtil {

    private static boolean isPrintLog = true;

    /**
     * 打印info信息
     *
     * @param TAG
     * @param msg
     */
    public static void i(String TAG, String msg) {
        if (isPrintLog) {
            Log.i(TAG, msg);
        }
    }

    /**
     * 打印debug信息
     *
     * @param TAG
     * @param msg
     */
    public static void d(String TAG, String msg) {
        if (isPrintLog) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 打印error信息
     *
     * @param TAG
     * @param msg
     */
    public static void e(String TAG, String msg) {
        if (isPrintLog) {
            Log.e(TAG, msg);
        }
    }

    /**
     * set IsPrintLog
     *
     * @param isPrintLog
     * true:print
     * false:not print
     */
    public static void setIsPrintLog(boolean isPrintLog) {
        LogUtil.isPrintLog = isPrintLog;
    }
}
