package com.simon.sample.telephone.util;

import android.os.Build;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 判断系统类型
 * Created by xw on 2016/10/12
 */
public class BuildUtil {
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    /**
     * 是否MIUI
     *
     * @return
     */
    public static boolean isMIUI() {
        try {
            final BuildProperties properties = BuildProperties.newInstance();
            return properties.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || properties.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || properties.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";

    /**
     * 是否EMUI
     *
     * @return
     */
    public static boolean isEMUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_EMUI_VERSION_CODE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 是否Flyme
     *
     * @return
     */
    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }
}
