package com.simon.simple.recyclerview.base;

import java.util.List;

/**
 *
 * Created by xw on 2016/8/24.
 */
public interface ListOperate<ITEM> {
    void addItems(List<ITEM> items);

    void addItem(ITEM item);

    void clear();

    void removeItem(ITEM item);
}
