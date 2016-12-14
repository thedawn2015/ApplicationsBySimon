package com.simon.simple.recyclerview.base;

import java.util.List;

/**
 * desc: List Operation
 * author: xw
 * time: 2016/12/14
 */
public interface ListOperation<ITEM> {
    void addItems(List<ITEM> items);

    void addItem(ITEM item);

    void removeAll();

    void removeItem(ITEM item);
}
