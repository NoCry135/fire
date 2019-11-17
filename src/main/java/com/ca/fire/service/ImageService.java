package com.ca.fire.service;

import com.ca.fire.domain.bean.ItemImage;

import java.util.List;

public interface ImageService {

    Integer saveImage(List<ItemImage> imageList);

    List<ItemImage> getImageList(ItemImage itemImage);
}
