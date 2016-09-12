package com.simon.simple.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simon.simple.base.util.LogUtil;
import com.simon.simple.db.constant.DBConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表操作
 * Created by xw on 2016/9/12
 */
public class SQLiteOperator {
    public static String TAG = SQLiteOperator.class.getSimpleName();

    //数据库操作
    private SQLiteDatabase db = null;

    public SQLiteOperator(SQLiteDatabase db) {
        this.db = db;
    }

    //插入重载操作
    public void insert(int id, int state) {
        String sql = "INSERT INTO " + DBConstant.TABLE_NAME + " (id,state)" + " VALUES(?,?)";
        Object args[] = new Object[]{id, state};
        db.execSQL(sql, args);
        //        db.close();
    }

    //更新操作
    public void update(int id, int state) {
        String sql = "UPDATE " + DBConstant.TABLE_NAME + " SET state=? WHERE id=?";
        Object args[] = new Object[]{state, id};
        this.db.execSQL(sql, args);
        //        this.db.close();
    }

    //删除操作,删除
    public void delete(int id) {
        String sql = "DELETE FROM " + DBConstant.TABLE_NAME + " WHERE id=?";
        Object args[] = new Object[]{id};
        this.db.execSQL(sql, args);
        //        this.db.close();
    }

    //查询操作,查询表中所有的记录返回列表
    public List<String> find() {
        List<String> all = new ArrayList<>(); //此时只是String
        String sql = "SELECT * FROM " + DBConstant.TABLE_NAME;
        Cursor result = db.rawQuery(sql, null);    //执行查询语句
        //采用循环的方式查询数据
        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            all.add(result.getInt(0) + "," + result.getString(1) + "," + result.getString(2) + ","
                    + result.getInt(3) + "," + result.getString(4) + "," + result.getString(5) + ","
                    + result.getString(6) + "," + result.getString(7));
        }
        result.close();
        //        close(result);
        return all;
    }

    //查询操作重载函数，返回指定ID的列表
    public int getstatebyID(int id) {
        int num = -1;//错误状态-1
        List<String> all = new ArrayList<String>(); //此时只是String
        String sql = "SELECT state FROM " + DBConstant.TABLE_NAME + " where id=?";
        String args[] = new String[]{String.valueOf(id)};
        Cursor result = db.rawQuery(sql, args);
        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            num = result.getInt(0);
        }

        LogUtil.i(TAG, "图片状态state" + String.valueOf(num));
        result.close();
        //        close(result);
        return num;
    }

    //判断插入数据的ID是否已经存在数据库中。
    public boolean check_same(int id) {
        String sql = "SELECT id from " + DBConstant.TABLE_NAME + " where id = ?";
        String args[] = new String[]{String.valueOf(id)};
        Cursor result = db.rawQuery(sql, args);
        LogUtil.i(TAG, "the sql has been excuate");

        LogUtil.i(TAG, "the hang count" + String.valueOf(result.getCount()));

        //判断得到的返回数据是否为空
        if (result.getCount() == 0) {
            LogUtil.i(TAG, "return false and not exist the same result" + String.valueOf(result.getCount()));
            result.close();
            //            close(result);
            return false;
        } else {
            LogUtil.i(TAG, "return true and exist the same result" + String.valueOf(result.getCount()));
            result.close();
            //            db.close();
            return true;
        }
    }

    /**
     * 关闭db
     */
    public void close() {
        if (db != null) {
            db.close();
        }
    }

}
