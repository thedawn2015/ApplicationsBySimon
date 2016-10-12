package com.simon.simple.telephone.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * 获取User-Agent信息
 * Created by xw on 2016/10/12.
 */
public class TelephoneUtil {

    /**
     * 获取User-Agent信息
     *
     * @param context
     * @param productName
     * @return
     */
    public static String getUserAgentInfo(Context context, String productName) {
        //
        String platformType = "MOBILE";
        String platformName = "|Android";
        String platformVersion = "|" + Build.VERSION.RELEASE;
        String productVersion = "|";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            productVersion += packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String platformInfo = "|" + System.getProperty("http.agent");
        //        String platformInfo = "|" + Build.BRAND + " " + Build.MODEL;
        String size = "|" + getSize((Activity) context);
        return platformType + platformName + platformVersion + "|" + productName + productVersion + platformInfo + size;
    }

    private static String getSize(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.y + "*" + point.x;
    }

}
