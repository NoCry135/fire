package com.ca.fire.controller;

import com.ca.fire.domain.BaseResult;
import com.ca.fire.domain.bean.Item;
import com.ca.fire.domain.bean.ItemImage;
import com.ca.fire.service.ImageService;
import com.ca.fire.service.ItemService;
import com.ca.fire.until.MyUploadFile;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("/imageFileController")
public class ImageFileController {

    @Resource
    private ImageService imageService;

    @Resource
    private ItemService itemService;


    private static final Logger logger = LoggerFactory.getLogger(ImageFileController.class);

    @RequestMapping(value = "/save_spu", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveImageFile(HttpServletRequest request, @RequestParam("files") MultipartFile[] files, Item item) {

        logger.error("saveImageFile");
        BaseResult baseResult = new BaseResult();
        if (files != null && files.length > 0) {
            // // 图片上传
            List<ItemImage> imageList = MyUploadFile.uploadImage(files, item);
            // 保存商品信息的业务
            Integer imageCount = imageService.saveImage(imageList);
            if (imageCount == imageList.size()) {
                HttpSession session = request.getSession();
                session.setAttribute("goodsNo", item.getGoodsNo());
                return "redirect:/showImage.do";
            }

        }


        return "goods/imageError";
    }

    @RequestMapping(value = "/showImage", method = {RequestMethod.POST, RequestMethod.GET})
    public String gotoImageView(HttpServletRequest request, Model mode) {
        HttpSession session = request.getSession();
        String goodsNo = (String) session.getAttribute("goodsNo");
        if (StringUtils.isNotBlank(goodsNo)) {
            Item item = new Item();
            ItemImage itemImage = new ItemImage();
            item.setGoodsNo(goodsNo);
            itemImage.setGoodsNo(goodsNo);
            item = itemService.getItem(item);
            List<ItemImage> itemImages = imageService.getImageList(itemImage);
            mode.addAttribute("item", item);
            mode.addAttribute("itemImages", itemImages);
            logger.error("goodsNo:");
        }
        return "goods/goodsDetail";
    }

}
