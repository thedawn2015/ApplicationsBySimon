package com.simon.simple.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络类型和连接状态的判断
 * Created by xw on 2016/8/2.
 */
public class NetworkUtil {
    private static final int NET_TYPE_WIFI = 0x01;
    private static final int NET_TYPE_CMWAP = 0x02;
    private static final int NET_TYPE_CMNET = 0x03;
    private static final int NET_TYPE_ETHERNET = 0x09;

    /**
     * 是否正在使用wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        if (getNetworkType(context) == NET_TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络类型
     *
     * @return
     */
    private static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!extraInfo.isEmpty()) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NET_TYPE_CMNET;
                } else {
                    netType = NET_TYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NET_TYPE_WIFI;
        } else if (nType == ConnectivityManager.TYPE_ETHERNET) {
            netType = NET_TYPE_ETHERNET;
        }
        return netType;
    }

    /**
     * 检查网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetwordConnected(Context context) {
        // 创建并初始化连接对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 判断初始化是否成功并作出相应处理
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

}
