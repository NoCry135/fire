package com.ca.fire.service;

import com.ca.fire.domain.bean.Item;

import java.util.List;

public interface ItemService {
    Item getItem(Item item);

    Integer importGoodsDetail(List<Item> itemList);


}
