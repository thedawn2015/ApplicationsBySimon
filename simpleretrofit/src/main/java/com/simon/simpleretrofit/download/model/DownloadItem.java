package com.simon.simpleretrofit.download.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xw on 2016/7/27.
 */
public class DownloadItem implements Parcelable {
    private float progress;
    private long currentFileSize;
    private long totalFileSize;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public long getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    public DownloadItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.progress);
        dest.writeLong(this.currentFileSize);
        dest.writeLong(this.totalFileSize);
    }

    protected DownloadItem(Parcel in) {
        this.progress = in.readFloat();
        this.currentFileSize = in.readLong();
        this.totalFileSize = in.readLong();
    }

    public static final Creator<DownloadItem> CREATOR = new Creator<DownloadItem>() {
        @Override
        public DownloadItem createFromParcel(Parcel source) {
            return new DownloadItem(source);
        }

        @Override
        public DownloadItem[] newArray(int size) {
            return new DownloadItem[size];
        }
    };
}
