package com.simon.simple.db.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by xw on 2016/9/12.
 */
public class DBConstant {
    public static final String DATABASE_PATH = Environment.getExternalStorageDirectory() + File.separator + "aa" + File.separator;
    public static final String DATABASE_NAME = "mydb.db";
    public static final int DATABASE_VERSION = 10;
    public static final String TABLE_NAME = "test";
    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + "id INTEGER,"
            + "nid VARCHAR(11),"
            + "sid CHAR(1),"
            + "type INTEGER,"
            + "stime DATETIME,"
            + "locate_main VARCHAR(45),"
            + "locate_detail VARCHAR(45),"
            + "state INTEGER"
            + ")";
}
