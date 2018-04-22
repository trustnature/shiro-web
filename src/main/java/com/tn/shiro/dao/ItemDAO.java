package com.tn.shiro.dao;

import com.tn.shiro.model.Item;

import java.util.List;
import java.util.Map;

/**
 * item 独有的操作
 */
public interface ItemDAO extends GenericDAO<Item, Long> {
    List<Item> findAll(boolean withBids);

    List<Item> findByName(String name, boolean fuzzy);

    public void SaveBatch(final String tableName, final String[] fields, final List<Map> datas);


}