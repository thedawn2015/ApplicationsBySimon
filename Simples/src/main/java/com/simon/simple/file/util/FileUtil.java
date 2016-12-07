package com.simon.simple.file.util;

import java.io.File;

/**
 * desc: 文件处理
 * author: xiao
 * time: 2016/12/7
 */
public class FileUtil {

    public static boolean createFilePath(String path) {
        try {
            File filePath = new File(path);
            if (!filePath.exists()) {
                return filePath.mkdirs();
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean createNewFile(String path, String name) {
        try {
            File file = new File(path, name);
            if (!file.exists()) {
                return file.createNewFile();
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
