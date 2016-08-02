package com.simon.simple.base.util;

/**
 * 自定义Log
 * <p/>
 * Created by xw on 2016/8/2.
 */
public class Log {
    private static boolean isPrintLog = true;

    /**
     * 打印info信息
     *
     * @param TAG
     * @param msg
     */
    public static void i(String TAG, String msg) {
        if (isPrintLog) {
            android.util.Log.i(TAG, msg);
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
            android.util.Log.d(TAG, msg);
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
            android.util.Log.e(TAG, msg);
        }
    }

    /**
     * set IsPrintLog
     *
     * @param isPrintLog true:print
     *                   false:not print
     */
    public static void setIsPrintLog(boolean isPrintLog) {
        Log.isPrintLog = isPrintLog;
    }
}
