package com.simon.cardsgame.datas;

/**
 * Created by TheDawn on 2016/7/5.
 */
public class CardType {
    private String typeName;
    private boolean isFront;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }
}
