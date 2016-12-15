package com.simon.sample.factory.model;

import android.content.Context;

import com.simon.baseandroid.util.GsonUtil;

/**
 * 足球运动员
 * Created by xw on 2016/8/17.
 */
public class AthleteFootball extends AthleteBase {
    private String team;
    private int goals;

    /**
     * 转换
     *
     * @param context
     * @param athleteUndefined
     * @return
     */
    public static AthleteFootball parseFrom(Context context, AthleteUndefined athleteUndefined) {
        if (context == null || athleteUndefined == null) {
            return null;
        }
        try {
            AthleteFootball athleteFootball = GsonUtil.toObj(athleteUndefined.getAthleteInfo(), AthleteFootball.class);
            return athleteFootball;
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

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
}
