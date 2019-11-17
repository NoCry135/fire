package com.ca.fire.dao;

import com.ca.fire.domain.bean.ItemImage;

import java.util.List;

public interface ImageDao {

    Integer batchInsert(List<ItemImage> imageList);

    List<ItemImage> selectList(ItemImage itemImage);

}
