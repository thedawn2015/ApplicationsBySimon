package com.simon.simple.factory.model;

import android.content.Context;

import com.simon.baseandroid.util.GsonUtil;

/**
 * 篮球运动员
 * Created by xw on 2016/8/17.
 */
public class AthleteBasketball extends AthleteBase {
    private String team;
    private boolean isMVP;

    /**
     * 转换
     *
     * @param context
     * @param athleteUndefined
     * @return
     */
    public static AthleteBasketball parseFrom(Context context, AthleteUndefined athleteUndefined) {
        if (context == null || athleteUndefined == null) {
            return null;
        }
        AthleteBasketball athleteBasketball;
        try {
            athleteBasketball = GsonUtil.toObj(athleteUndefined.getAthleteInfo(), AthleteBasketball.class);
            return athleteBasketball;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public boolean isMVP() {
        return isMVP;
    }

    public void setMVP(boolean MVP) {
        isMVP = MVP;
    }
}
