package com.simon.baseandroid.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * desc: 电话功能
 * author: xw
 * time: 2017/2/10
 */
public class PhoneCallUtil {

    /**
     * Perform a call,this method requires android.permission.CALL_PHONE permission.
     */
    public static void toPhone(Context context, String phone) {
        try {
            String url = "tel:" + phone;
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtil.showShort(context, "没有电话应用");
        }
    }

    /**
     * Dial a number.
     */
    public static void toDial(Context context, String phone) {
        try {
            String url = "tel:" + phone;
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtil.showShort(context, "没有电话应用");
        }
    }
}
