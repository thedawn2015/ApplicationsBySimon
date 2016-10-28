package com.simon.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Util
 * Created by xw on 2016/8/31.
 */
public class DateUtil {
    public static final String FORMAT_LONG = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT_LONG2 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SHORT = "yyyy/MM/dd";
    public static final String FORMAT_SHORT2 = "yyyy-MM-dd";
    public static final String FORMAT_SHORT3 = "yyyyMMdd";
    public static final String FORMAT_CN_LONG = "yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMAT_CN_SHORT = "yyyy年MM月dd日";

    /**
     * date --> yyyy/MM/dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2Long(Date date) {
        return date2String(date, FORMAT_LONG);
    }

    /**
     * date --> yyyy/MM/dd
     *
     * @param date
     * @return
     */
    public static String date2Short(Date date) {
        return date2String(date, FORMAT_SHORT);
    }

    /**
     * date --> "yyyy年MM月dd日 HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String date2LongCN(Date date) {
        return date2String(date, FORMAT_CN_LONG);
    }

    /**
     * date --> String
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    /**
     * date --> String
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date string2Date(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
