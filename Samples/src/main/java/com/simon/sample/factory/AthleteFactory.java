package com.simon.sample.factory;

import android.content.Context;

import com.simon.sample.factory.model.AthleteBase;
import com.simon.sample.factory.model.AthleteBasketball;
import com.simon.sample.factory.model.AthleteFootball;
import com.simon.sample.factory.model.AthletePingPang;
import com.simon.sample.factory.model.AthleteUndefined;

/**
 * 运动员工厂类
 * Created by xw on 2016/8/17
 */
public class AthleteFactory {

    /**
     * 创建不同的运动员
     *
     * @param context
     * @param athleteUndefined
     * @return
     */
    public static AthleteBase create(Context context, AthleteUndefined athleteUndefined) {
        try {
            AthleteBase athleteBase = null;
            switch (athleteUndefined.getType()) {
                case AthleteBase.ATHLETE_TYPE_BASKETBALL:
                    athleteBase = AthleteBasketball.parseFrom(context, athleteUndefined);
                    break;
                case AthleteBase.ATHLETE_TYPE_FOOTBALL:
                    athleteBase = AthleteFootball.parseFrom(context, athleteUndefined);
                    break;
                case AthleteBase.ATHLETE_TYPE_PINGPANG:
                    athleteBase = AthletePingPang.parseFrom(context, athleteUndefined);
                    break;
            }
            return athleteBase;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
