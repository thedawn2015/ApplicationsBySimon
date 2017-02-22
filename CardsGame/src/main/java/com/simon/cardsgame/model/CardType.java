package com.simon.cardsgame.model;

/**
 * desc：卡片类型
 * author：simon
 * date：2017/2/16
 */
public class CardType {
    private String typeName;
    private boolean isBack;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }
}
