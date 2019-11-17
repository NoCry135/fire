package com.ca.fire.manager.impl;

import com.ca.fire.dao.ItemDao;
import com.ca.fire.domain.bean.Item;
import com.ca.fire.manager.ItemManager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ItemManagerImpl implements ItemManager {

    @Resource
    private ItemDao itemDao;

    @Override
    public Boolean add(Item item) {
        itemDao.insert(item);
//        int i = 10;
//        int i1 = i / 0;
        return true;
    }
}
