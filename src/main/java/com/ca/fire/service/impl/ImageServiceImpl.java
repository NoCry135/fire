package com.ca.fire.service.impl;

import com.ca.fire.dao.ImageDao;
import com.ca.fire.domain.bean.ItemImage;
import com.ca.fire.service.ImageService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageDao imageDao;

    @Override
    public Integer saveImage(List<ItemImage> imageList) {
        if (CollectionUtils.isNotEmpty(imageList)) {
            return imageDao.batchInsert(imageList);
        }
        return 0;
    }

    @Override
    public List<ItemImage> getImageList(ItemImage itemImage) {
        return imageDao.selectList(itemImage);
    }
}
