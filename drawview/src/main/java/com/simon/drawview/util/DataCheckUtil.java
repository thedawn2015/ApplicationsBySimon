package com.simon.drawview.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xw on 2016/7/7.
 */
public class DataCheckUtil {

    /**
     * 判断list是否是空或者空数据
     *
     * @param list
     * @return
     */
    public static boolean isNullOrEmpty(List list) {
        if (list == null || list.size() == 0) {
            //            list = new ArrayList();
            return true;
        }
        return false;
    }
}
