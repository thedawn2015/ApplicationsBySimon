package com.simon.baseandroid.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * desc: 路径
 * author: xiao
 * time: 2016/12/6
 */
public class DirectoryUtil {

    private DirectoryUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**-------------------------与APP路径没有关系------------------------------**/
    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取cache路径
     *
     * @return
     */
    public static String getEnDownloadCachePath() {
        return Environment.getDownloadCacheDirectory() + File.separator;
    }

    /**
     * 获取data路径
     *
     * @return
     */
    public static String getDataPath() {
        return Environment.getDataDirectory() + File.separator;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath() + File.separator;
    }

    /**-------------------------与APP的路径有关------------------------------**/
    /**
     * 获取cache路径（）
     *
     * @return
     */
    public static String getCachePath(Context context) {
        return context.getCacheDir() + File.separator;
    }

    /**
     * 获取cache路径
     *
     * @return
     */
    public static String getExCachePath(Context context) {
        return context.getExternalCacheDir() + File.separator;
    }

    /**
     * 获取cache-download路径
     *
     * @param context
     * @return
     */
    public static String getExDownloadCachePath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator;
    }

    /**
     * 获取cache-picture路径
     *
     * @param context
     * @return
     */
    public static String getExPictureCachePath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator;
    }
}
