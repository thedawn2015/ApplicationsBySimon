package com.simon.baseandroid.util;

import android.content.Context;

import com.simon.baseandroid.constant.Constant;
import com.tencent.tauth.Tencent;

/**
 * desc: Tencent 管理
 * author: xiao
 * time: 2016/12/1
 */
public class TencentUtil {

    /**
     * 初始化Tencent
     *
     * @param context
     * @return
     */
    public static Tencent initTencent(Context context) {
        //Tencent是SDK的功能入口，所有的接口调用都得通过Tencent进行调用。因此，调用SDK，首先需要创建一个Tencent实例
        Tencent tencent = Tencent.createInstance(Constant.QQ_APP_ID, context.getApplicationContext());
        return tencent;
    }
}
