package com.ca.fire.controller;

import com.ca.fire.domain.BaseResult;
import com.ca.fire.domain.bean.Item;
import com.ca.fire.service.ItemService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("excelController")
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Resource
    private ItemService itemService;

    @RequestMapping(value = "/importGoodsDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object importGoodsDetail(MultipartHttpServletRequest request) {
        BaseResult baseResult = new BaseResult();
        try {
            List<MultipartFile> uploadFileList = request.getFiles("excelfiles");
            if (uploadFileList.size() == 0) {
                return new BaseResult(false, "上传文件不能为空！");
            }
            MultipartFile templateFile = uploadFileList.get(0);
            String fileName = templateFile.getOriginalFilename();
            //检查文件格式是否正确
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
                Map<String, String> errorDataMap = new HashMap<String, String>();
                //解析Excel文件，获取合格的数据

                List<Item> itemList = analyzeExcel(templateFile, errorDataMap);
                if (itemList.size() > 0) {
                    try {
                        Integer messageMap = itemService.importGoodsDetail(itemList);
                        List<String> infoList = new LinkedList<String>();

                        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
                        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                            for (StackTraceElement stackTraceElement : entry.getValue()) {
                                String className = stackTraceElement.getClassName();
                                if (className.startsWith("com.ca.fire")) {
                                    infoList.add(0, stackTraceElement.toString());
                                }
                            }
                        }
                        Thread th = Thread.currentThread();
                        StackTraceElement[] stackTrace = th.getStackTrace();
                        ThreadGroup threadGroup = th.getThreadGroup();
                        String name = th.getName();
                        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = th.getUncaughtExceptionHandler();
                        logger.error("线程对象#######", stackTrace + "," + threadGroup + "," + name + "," + uncaughtExceptionHandler);

                        String traceInfo = org.apache.commons.lang.StringUtils.join(infoList, "=>>");
                        logger.error("线程对象#######", th.getId());
                        logger.error("线程对象#######", traceInfo);

                    } catch (Exception e) {
                        logger.error("保存商品异常#######", e);
                        baseResult = new BaseResult(false, "保存商品异常" + e.getMessage());
                    }
                } else {
                    return new BaseResult(false, "文件内没有合格的数据,请导入正确的申请单明细信息！");
                }
            } else {
                return new BaseResult(false, "文件格式错误,请导入excel文件！");
            }
        } catch (Exception e) {
            logger.error("导入数据,出现异常", e);
            baseResult = new BaseResult(false, "导入数据,出现异常" + e.getMessage());
        }
        return baseResult;
    }


    private List<Item> analyzeExcel(MultipartFile file, Map<String, String> errorDatas) throws Exception {

        List<Item> itemList = Lists.newArrayList();

        Workbook excelFileObj = new XSSFWorkbook(file.getInputStream());
        if (file.getOriginalFilename().endsWith("xls")) {
            excelFileObj = new HSSFWorkbook(file.getInputStream());
        }
        //第一个工作表
        Sheet sheet = excelFileObj.getSheetAt(0);
        //获取行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //总行数=最后一行的index+1；因为从0开始
        int totalColumn = sheet.getLastRowNum();
        logger.error(file.getOriginalFilename() + "共有" + totalRows + "行! " + totalColumn + "列");
        if (totalRows > 0 && totalColumn > 0) {
            for (int rowNum = 1; rowNum <= totalColumn; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    //申请单号

                    Cell rowCell = row.getCell(0);
                    if (rowCell == null) {
                        errorDatas.put("第" + (String.valueOf(rowNum)) + "行", "商品名称不能为空!");
                        continue;
                    }
                    //数字会自动加.0,补齐即可
                    rowCell.setCellType(CellType.STRING);
                    String goodsNo = rowCell.toString().trim();
                    if (StringUtils.isBlank(goodsNo)) {
                        errorDatas.put("第" + (String.valueOf(rowNum)) + "行", "商品编号不能为空!");
                        continue;
                    }

                    if (row.getCell(1) == null) {
                        errorDatas.put("第" + (String.valueOf(rowNum)) + "行", "商品价格不能为空!");
                        continue;
                    }
                    String price = row.getCell(1).toString().trim();
                    if (StringUtils.isBlank(price)) {
                        errorDatas.put("第" + (String.valueOf(rowNum)) + "行", "商品价格不能为空!");
                        continue;
                    }
                    Cell cell = row.getCell(2);

                    if (cell == null) {
                        errorDatas.put("第" + (String.valueOf(rowNum)) + "行", "商品数量不能为空!");
                        continue;
                    }
                    cell.setCellType(CellType.STRING);
                    String stock = cell.toString().trim();
                    if (StringUtils.isBlank(stock)) {
                        errorDatas.put("第" + (String.valueOf(rowNum)) + "行", "商品数量不能为空!");
                        continue;
                    }
                    Item item = new Item();
                    item.setGoodsNo(goodsNo);
                    item.setPrice(new BigDecimal(price));
                    item.setStock(Integer.valueOf(stock));
                    item.setCreateUser("sys");
                    item.setUpdateUser("sys");
                    itemList.add(item);
                }
            }
        }


        return itemList;
    }
}
