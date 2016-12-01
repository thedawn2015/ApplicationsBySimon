package com.simon.baseandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * desc: QQ 用户基本信息
 * author: xiao
 * time: 2016/12/1
 */
public class QQUserInfoModel implements Parcelable {

    /**
     * ret : 0
     * msg :
     * is_lost : 0
     * nickname : Carey❤凯莉
     * gender : 女
     * province : 四川
     * city : 成都
     * figureurl : http://qzapp.qlogo.cn/qzapp/1105858502/E921B55A9D234F78B686551204A22642/30
     * figureurl_1 : http://qzapp.qlogo.cn/qzapp/1105858502/E921B55A9D234F78B686551204A22642/50
     * figureurl_2 : http://qzapp.qlogo.cn/qzapp/1105858502/E921B55A9D234F78B686551204A22642/100
     * figureurl_qq_1 : http://q.qlogo.cn/qqapp/1105858502/E921B55A9D234F78B686551204A22642/40
     * figureurl_qq_2 : http://q.qlogo.cn/qqapp/1105858502/E921B55A9D234F78B686551204A22642/100
     * is_yellow_vip : 0
     * vip : 0
     * yellow_vip_level : 0
     * level : 0
     * is_yellow_year_vip : 0
     */

    //    private int ret;
    //    private String msg;
    //    private int is_lost;
    private String nickname;
    private String gender;
    private String province;
    private String city;
    //    private String figureurl;
    //    private String figureurl_1;
    //    private String figureurl_2;
    //    private String figureurl_qq_1;
    private String figureurl_qq_2;
    //    private String is_yellow_vip;
    //    private String vip;
    //    private String yellow_vip_level;
    //    private String level;
    //    private String is_yellow_year_vip;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public void setFigureurl_qq_2(String figureurl_qq_2) {
        this.figureurl_qq_2 = figureurl_qq_2;
    }

    public QQUserInfoModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeString(this.gender);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.figureurl_qq_2);
    }

    protected QQUserInfoModel(Parcel in) {
        this.nickname = in.readString();
        this.gender = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.figureurl_qq_2 = in.readString();
    }

    public static final Creator<QQUserInfoModel> CREATOR = new Creator<QQUserInfoModel>() {
        @Override
        public QQUserInfoModel createFromParcel(Parcel source) {
            return new QQUserInfoModel(source);
        }

        @Override
        public QQUserInfoModel[] newArray(int size) {
            return new QQUserInfoModel[size];
        }
    };
}
