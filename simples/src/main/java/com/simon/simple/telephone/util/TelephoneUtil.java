package com.simon.simple.telephone.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.simon.baseandroid.util.LogUtil;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 获取User-Agent信息
 * Created by xw on 2016/10/12.
 */
public class TelephoneUtil {
    public static final String TAG = TelephoneUtil.class.getSimpleName();

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
        String szImei = "|" + getUniqueIdentifier((Activity) context);
        getSize2((Activity) context);
        getSize3((Activity) context);
        return platformType + platformName + platformVersion + "|" + productName + productVersion + platformInfo + size + szImei;
    }

    public static String getSize(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        String size = point.x + "*" + point.y;
        LogUtil.i(TAG, "getSize: size=" + size);
        return size;
    }

    public static String getSize2(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        String size = width + "*" + height;
        LogUtil.i(TAG, "getSize2: size=" + size);
        return size;
    }

    public static String getSize3(Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        String size = width + "*" + height;
        LogUtil.i(TAG, "getSize3: size=" + size);
        return size;
    }

    private static String getUniqueIdentifier(Activity activity) {
        TelephonyManager TelephonyMgr = (TelephonyManager) activity.getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

}
