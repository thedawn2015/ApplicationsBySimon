package com.simon.simple.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.simon.base.util.LogUtil;
import com.simon.simple.db.constant.DBConstant;

/**
 * 创建数据库文件，创建表
 * Created by xw on 2016/9/12
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static String TAG = SQLiteHelper.class.getSimpleName();

    public SQLiteHelper(Context context) {
        super(context, DBConstant.DATABASE_PATH + DBConstant.DATABASE_NAME, null, DBConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstant.CREATE_TABLE);
        LogUtil.i(TAG, "onCreate: 创建数据库表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        LogUtil.i(TAG, "onUpgrade: 更新数据库表");
        String sql = "DROP TABLE IF EXISTS " + DBConstant.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}
