package com.simon.sample.factory.model;

/**
 * 运动员 基类
 * Created by xw on 2016/8/17.
 */
public class AthleteBase {
    public static final int ATHLETE_TYPE_BASKETBALL = 1001;//篮球运动员
    public static final int ATHLETE_TYPE_FOOTBALL = 1002;//足球运动员
    public static final int ATHLETE_TYPE_PINGPANG = 1003;//乒乓球运动员

    private int id;
    private String name;
    private String sex;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
