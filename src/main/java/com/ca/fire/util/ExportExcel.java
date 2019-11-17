//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ca.fire.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExportExcel {
    private static final Logger log = LogManager.getLogger(ExportExcel.class);
    private static String PARAM_TITLE = "文件标题不能为空";
    private static String PARAM_FIELDNAMES = "列表列名不能为空";
    private static String PARAM_LIST = "列表不能为空";
    private static String MAX_NUM_INFO = "数据量太大，不能导出";
    private static int MAX_NUM = 1000000;
    private static int PAGE_NUM = '썐';
    public static String EXPORT_TYPE_EXCEL = "1";
    public static String EXPORT_TYPE_CSV = "2";
    public static int EXPORT_MAX_NUM = '썐';
    public static int EXPORT_PAGE_SIZE = 10000;

    public ExportExcel() {
    }

    public static <T> HSSFWorkbook exportExcel(String fileName, String[] fieldNames) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);
        sheet.setDefaultColumnWidth(20);
        createColumHeader(workbook, sheet, fieldNames);
        return workbook;
    }

    public static void exportFile(HttpServletResponse response, String fileName, String[] titles, String[] properties, List<T> list, String type) throws Exception {
        HSSFWorkbook workbook;
        if (type.contains(EXPORT_TYPE_EXCEL)) {
            setResponseParam(response, fileName);
            workbook = null;
            if (list.size() == 0) {
                workbook = exportExcel(fileName, titles);
            } else {
                workbook = exportExcel(fileName, titles, properties, list);
            }

            workbook.write(response.getOutputStream());
        } else {
            setResponseParam(response, fileName);
            workbook = null;
            if (list.size() == 0) {
                workbook = exportExcel(fileName, titles);
            } else {
                workbook = exportExcel(fileName, titles, properties, list);
            }

            workbook.write(response.getOutputStream());
        }

    }

    public static void exportFileFormat(HttpServletResponse response, String fileName, String[] titles, String[] properties, List<T> list, String type, Set<String> formatProperties) throws Exception {
        HSSFWorkbook workbook;
        if (type.contains(EXPORT_TYPE_EXCEL)) {
            setResponseParam(response, fileName);
            workbook = null;
            if (list.size() == 0) {
                workbook = exportExcel(fileName, titles);
            } else {
                workbook = exportExcel(fileName, titles, properties, list);
            }

            workbook.write(response.getOutputStream());
        } else if (type.contains(EXPORT_TYPE_CSV)) {
            if (formatProperties == null) {
                exportFile(response, fileName, titles, properties, list, EXPORT_TYPE_CSV);
            }
        } else {
            setResponseParam(response, fileName);
            workbook = null;
            if (list.size() == 0) {
                workbook = exportExcel(fileName, titles);
            } else {
                workbook = exportExcel(fileName, titles, properties, list);
            }

            workbook.write(response.getOutputStream());
        }

    }

    public static <T> HSSFWorkbook exportExcel(String fileName, String[] fieldNames, String[] fieldPreNames, List<T> list) throws Exception {
        if (null != fileName && !fileName.equals("")) {
            if (null != fieldNames && fieldNames.length != 0) {
                if (null != list && list.size() != 0) {
                    if (list.size() > MAX_NUM) {
                        throw new Exception(MAX_NUM_INFO);
                    } else {
                        HSSFWorkbook workbook = new HSSFWorkbook();
                        int size = (list.size() - 1) / PAGE_NUM + 1;

                        for (int i = 0; i < size; ++i) {
                            List<T> subList = list.subList(PAGE_NUM * i, PAGE_NUM * (i + 1) > list.size() ? list.size() : PAGE_NUM * (i + 1));
                            if (i >= 1) {
                                fileName = fileName + i;
                            }

                            HSSFSheet sheet = workbook.createSheet(fileName);
                            sheet.setDefaultColumnWidth(20);
                            createColumHeader(workbook, sheet, fieldNames);

                            try {
                                Field[] columnFields = createColumnFileds(fieldPreNames, subList.get(0).getClass());
                                int rowCount = 1;
                                HSSFCellStyle style = workbook.createCellStyle();
                                HSSFFont font = workbook.createFont();
                                font.setBoldweight((short) 700);
                                style.setFont(font);
                                style.setBorderBottom((short) 1);
                                style.setBorderTop((short) 1);
                                style.setBorderLeft((short) 1);
                                style.setBorderRight((short) 1);
                                HSSFRow row = null;

                                for (Iterator i$ = subList.iterator(); i$.hasNext(); ++rowCount) {
                                    T t = (T) i$.next();
                                    row = sheet.createRow(rowCount);
                                    createCell(row, style, columnFields, t);
                                }
                            } catch (Exception var16) {
                                var16.printStackTrace();
                                log.error("导出excel创建单元格是出现异常", var16);
                            }
                        }

                        return workbook;
                    }
                } else {
                    throw new Exception(PARAM_LIST);
                }
            } else {
                throw new Exception(PARAM_FIELDNAMES);
            }
        } else {
            throw new Exception(PARAM_TITLE);
        }
    }

    private static void createColumHeader(HSSFWorkbook workbook, HSSFSheet sheet, String[] fieldNames) {
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 400);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment((short) 2);
        cellStyle.setVerticalAlignment((short) 1);
        cellStyle.setWrapText(true);
        HSSFFont font = workbook.createFont();
        font.setBoldweight((short) 700);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBottomBorderColor((short) 8);
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setLeftBorderColor((short) 8);
        cellStyle.setBorderRight((short) 1);
        cellStyle.setRightBorderColor((short) 8);
        cellStyle.setBorderTop((short) 1);
        cellStyle.setTopBorderColor((short) 8);
        HSSFCell cell = null;

        for (int i = 0; i < fieldNames.length; ++i) {
            cell = row.createCell(i);
            cell.setCellType(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(fieldNames[i]));
        }

    }

    private static <T> Field[] createColumnFileds(String[] fieldNames, Class<T> fieldClass) throws NoSuchFieldException {
        Field[] fields = new Field[fieldNames.length];

        for (int i = 0; i < fieldNames.length; ++i) {
            Field field = fieldClass.getDeclaredField(fieldNames[i]);
            field.setAccessible(true);
            fields[i] = field;
        }

        return fields;
    }

    private static <T> void createCell(HSSFRow row, HSSFCellStyle style, Field[] columnFields, T t) {
        PropertyDescriptor pd = null;
        Method getMethod = null;
        HSSFCell cell = null;

        for (int i = 0; i < columnFields.length; ++i) {
            cell = row.createCell(i);

            try {
                pd = new PropertyDescriptor(columnFields[i].getName(), t.getClass());
                getMethod = pd.getReadMethod();
                Object field = getMethod.invoke(t, new Object[0]);
                cell.setCellType(1);
                if (null == field) {
                    cell.setCellValue("");
                } else if (field instanceof Date) {
                    cell.setCellValue((Date) field);
                } else {
                    cell.setCellValue(field + "");
                }

                cell.setCellStyle(style);
            } catch (Exception var10) {
                log.error("导出excel创建单元格是出现异常", var10);
            }
        }

    }

    public static void setResponseParam(HttpServletResponse response, String fileName) {
        response.setContentType("application/msexcel;charset=GBK");
        fileName = fileName + ".xls";

        try {
            response.setHeader("Content-Disposition", "attachment;filename=".concat(new String(fileName.getBytes("GBK"), "iso8859-1")));
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/vnd.ms-excel");
    }

    public static void main(String[] args) {

        System.out.println(PAGE_NUM);
        System.out.println(199999 / PAGE_NUM + 1);
    }
}
