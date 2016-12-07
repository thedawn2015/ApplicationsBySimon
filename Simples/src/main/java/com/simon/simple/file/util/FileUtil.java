package com.simon.simple.file.util;

import java.io.File;

/**
 * desc: 文件处理
 * author: xiao
 * time: 2016/12/7
 */
public class FileUtil {

    /**
     * 创建
     *
     * @param path
     * @return
     */
    public static boolean createFilePath(String path) {
        try {
            File filePath = new File(path);
            if (!filePath.exists() || !filePath.isDirectory()) {
                return filePath.mkdirs();
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 创建文件
     *
     * @param path
     * @param name
     * @return
     */
    public static boolean createNewFile(String path, String name) {
        createFilePath(path);
        try {
            File file = new File(path, name);
            if (file.exists()) {
                file.delete();
            }
            return file.createNewFile();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
