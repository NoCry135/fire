package com.ca.fire.service.impl;

import com.ca.fire.annotations.MyPropAnnotation;
import com.ca.fire.dao.ItemDao;
import com.ca.fire.domain.bean.Item;
import com.ca.fire.service.ItemService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemDao itemDao;

    @Override
    public Item getItem(Item item) {
        return itemDao.selectByItem(item);
    }

    @Override
    @MyPropAnnotation
    public Integer importGoodsDetail(List<Item> itemList) {
        if (CollectionUtils.isNotEmpty(itemList)) {
            return itemDao.batchInsert(itemList);
        }
        return 0;
    }
}
