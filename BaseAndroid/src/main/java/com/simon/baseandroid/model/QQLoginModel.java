package com.simon.baseandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * desc: QQ 登录
 * author: xiao
 * time: 2016/12/1
 */
public class QQLoginModel implements Parcelable {

    /**
     * ret : 0
     * openid : AD6A44F89814234DF61D6F0A6B3A0D49
     * access_token : 979EE446A19ED88DF876CE4D9DDDD73B
     * pay_token : 66DE7AEDBCBE1E533316892B3B91AD3B
     * expires_in : 7776000
     * pf : desktop_m_qq-10000144-android-2002-
     * pfkey : 719f3c55a52f56b1ab49812d6627201c
     * msg :
     * login_cost : 2836
     * query_authority_cost : 365
     * authority_cost : 0
     */

    //    private int ret;
    private String openid;
    private String access_token;
    //        private String pay_token;
    private int expires_in;
    //    private String pf;
    //    private String pfkey;
    //    private String msg;
    //    private int login_cost;
    //    private int query_authority_cost;
    //    private int authority_cost;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openid);
        dest.writeString(this.access_token);
        dest.writeInt(this.expires_in);
    }

    public QQLoginModel() {
    }

    protected QQLoginModel(Parcel in) {
        this.openid = in.readString();
        this.access_token = in.readString();
        this.expires_in = in.readInt();
    }

    public static final Parcelable.Creator<QQLoginModel> CREATOR = new Parcelable.Creator<QQLoginModel>() {
        @Override
        public QQLoginModel createFromParcel(Parcel source) {
            return new QQLoginModel(source);
        }

        @Override
        public QQLoginModel[] newArray(int size) {
            return new QQLoginModel[size];
        }
    };
}
