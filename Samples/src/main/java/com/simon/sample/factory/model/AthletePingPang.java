package com.simon.sample.factory.model;

import android.content.Context;

import com.simon.baseandroid.util.GsonUtil;

/**
 * 乒乓球运动员
 * Created by xw on 2016/8/17.
 */
public class AthletePingPang extends AthleteBase {
    private String country;
    private int isDMW;//是不是大魔王

    /**
     * 转换
     *
     * @param context
     * @param athleteUndefined
     * @return
     */
    public static AthletePingPang parseFrom(Context context, AthleteUndefined athleteUndefined) {
        if (context == null || athleteUndefined == null) {
            return null;
        }
        try {
            AthletePingPang athletePingPang = GsonUtil.toObj(athleteUndefined.getAthleteInfo(), AthletePingPang.class);
            return athletePingPang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIsDMW() {
        return isDMW;
    }

    public void setIsDMW(int isDMW) {
        this.isDMW = isDMW;
    }
}
