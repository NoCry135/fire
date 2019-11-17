package com.ca.fire.until;

import com.ca.fire.domain.bean.Item;
import com.ca.fire.domain.bean.ItemImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyUploadFile {
    private static final Logger logger = LoggerFactory.getLogger(MyUploadFile.class);

    public static List<ItemImage> uploadImage(MultipartFile[] files, Item item) {
        List<ItemImage> list_image = new ArrayList<ItemImage>();
        String imgPath = MyPropertiesUtil.getProperty("prop/filePath.properties", "img_path");
        String imgUrl = MyPropertiesUtil.getProperty("prop/filePath.properties", "img_url");

        for (int i = 0; i < files.length; i++) {
            if (!files[i].isEmpty()) {
                String originalFilename = files[i].getOriginalFilename();
                originalFilename = System.currentTimeMillis() + originalFilename;
                try {
                    files[i].transferTo(new File(imgPath + "\\" + originalFilename));
//                    files[i].transferTo(new File("H:\\workspace4OS\\fire\\src\\main\\webapp\\upload\\image"+ "\\" + originalFilename));
                } catch (Exception e) {
                    logger.error("图片保存失败", e);
                }
                ItemImage itemImage = new ItemImage();
                itemImage.setGoodsNo(item.getGoodsNo());
                itemImage.setImageName(originalFilename);
                itemImage.setUrl(imgUrl + "\\" + originalFilename);
                list_image.add(itemImage);
            }
        }

        return list_image;
    }

}
