package com.simon.sample.db.model;

import io.realm.RealmObject;

/**
 * Created by xw on 2016/9/13.
 */
public class MyMessage extends RealmObject {

//    @PrimaryKey
    private int id;
//    @Required
    private String title;
    private boolean flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
