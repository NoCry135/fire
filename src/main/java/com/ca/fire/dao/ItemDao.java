package com.ca.fire.dao;

import com.ca.fire.domain.bean.Item;

import java.util.List;

public interface ItemDao {

    Integer insert(Item item);

    Integer batchInsert(List<Item> item);

    Integer updateById(Long id);

    Item selectById(Long id);

    Item selectByItem(Item item);

    Integer deleteById(Long id);

    List<Item> queryItemList(Item item);

}
