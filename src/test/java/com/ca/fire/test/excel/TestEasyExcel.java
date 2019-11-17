package com.ca.fire.test.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ca.fire.dao.UserDao;
import com.ca.fire.domain.bean.User;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestEasyExcel {

    @Resource
    private UserDao userDao;

    @Test
    public void writeExcelMoreSheetMoreWrite() throws IOException {

        // 生成EXCEL并指定输出路径

        OutputStream out = new FileOutputStream("E:\\temp\\withoutHead3.xlsx");

        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

        // 设置SHEET名称

        String sheetName = "测试SHEET";

        // 设置标题

        Table table = new Table(1);

        List<List<String>> titles = new ArrayList<List<String>>();

        titles.add(Arrays.asList("用户ID"));

        titles.add(Arrays.asList("名称"));

        titles.add(Arrays.asList("年龄"));

        titles.add(Arrays.asList("生日"));

        table.setHead(titles);

        // 模拟分批查询：总记录数250条，每个SHEET存100条，每次查询20条  则生成3个SHEET，前俩个SHEET查询次数为5， 最后一个SHEET查询次数为3 最后一次写的记录数是10

        // 注：该版本为了较少数据判断的复杂度，暂时perSheetRowCount要能够整除pageSize， 不去做过多处理  合理分配查询数据量大小不会内存溢出即可。

        Integer totalRowCount = 250;

        Integer perSheetRowCount = 100;

        Integer pageSize = 20;

        Integer sheetCount = totalRowCount % perSheetRowCount == 0 ? (totalRowCount / perSheetRowCount) : (totalRowCount / perSheetRowCount + 1);

        Integer previousSheetWriteCount = perSheetRowCount / pageSize;

        Integer lastSheetWriteCount = totalRowCount % perSheetRowCount == 0 ? previousSheetWriteCount :

                (totalRowCount % perSheetRowCount % pageSize == 0 ? totalRowCount % perSheetRowCount / pageSize : (totalRowCount % perSheetRowCount / pageSize + 1));

        for (int i = 0; i < sheetCount; i++) {

            // 创建SHEET

            Sheet sheet = new Sheet(i, 0);

            sheet.setSheetName(sheetName + i);

            if (i < sheetCount - 1) {

                // 前2个SHEET, 每个SHEET查5次 每次查20条 每个SHEET写满100行  2个SHEET合计200行  实用环境：参数： currentPage: j+1 + previousSheetWriteCount*i, pageSize: pageSize

                for (int j = 0; j < previousSheetWriteCount; j++) {

                    List<List<String>> userList = new ArrayList<>();

                    for (int k = 0; k < 20; k++) {

                        userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()), new Date().toString()));

                    }

                    writer.write0(userList, sheet, table);

                }

            } else if (i == sheetCount - 1) {

                // 最后一个SHEET 实用环境不需要将最后一次分开，合成一个即可， 参数为： currentPage = i+1;  pageSize = pageSize

                for (int j = 0; j < lastSheetWriteCount; j++) {

                    // 前俩次查询 每次查询20条

                    if (j < lastSheetWriteCount - 1) {

                        List<List<String>> userList = new ArrayList<>();

                        for (int k = 0; k < 20; k++) {

                            userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()), new Date().toString()));

                        }

                        writer.write0(userList, sheet, table);

                    } else if (j == lastSheetWriteCount - 1) {

                        // 最后一次查询 将剩余的10条查询出来

                        List<List<String>> userList = new ArrayList<>();

                        Integer lastWriteRowCount = totalRowCount - (sheetCount - 1) * perSheetRowCount - (lastSheetWriteCount - 1) * pageSize;

                        for (int k = 0; k < lastWriteRowCount; k++) {

                            userList.add(Arrays.asList("ID_" + Math.random(), "小明1", String.valueOf(Math.random()), new Date().toString()));

                        }

                        writer.write0(userList, sheet, table);

                    }

                }

            }

        }

        writer.finish();
    }

    /**
     * 每个sheet存储的记录数 100W
     */

    public static final Integer PER_SHEET_ROW_COUNT = 1000000;

    /**
     * 每次向EXCEL写入的记录数(查询每页数据大小) 20W
     */

    public static final Integer PER_WRITE_ROW_COUNT = 200000;

    @Test
    public void exportSysSystemExcel() throws Exception {

        OutputStream out = null;
        System.out.println("start");
        long st = System.currentTimeMillis();
        try {
            out = new FileOutputStream("E:\\temp\\withoutHead.xlsx");


            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称

            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            // 设置SHEET名称

            String sheetName = "用户信息sheet";

            // 设置标题

            Table table = new Table(1);

            List<List<String>> titles = new ArrayList<List<String>>();

            titles.add(Arrays.asList("姓名"));
            titles.add(Arrays.asList("邮箱"));
            titles.add(Arrays.asList("手机"));
            titles.add(Arrays.asList("创建人"));
            titles.add(Arrays.asList("创建时间"));

            table.setHead(titles);

            // 查询总数并封装相关变量(这块直接拷贝就行了不要改)
            Integer totalRowCount = userDao.count();

            Integer perSheetRowCount = 1000000;

            Integer pageSize = 10000;

            //3 个
            Integer sheetCount = totalRowCount % perSheetRowCount == 0 ? (totalRowCount / perSheetRowCount) : (totalRowCount / perSheetRowCount + 1);

            //写满一个sheet需要写 多少次
            Integer previousSheetWriteCount = perSheetRowCount / pageSize;

            //最后一次写多少次
            Integer lastSheetWriteCount = totalRowCount % perSheetRowCount == 0 ? previousSheetWriteCount :

                    (totalRowCount % perSheetRowCount % pageSize == 0 ? totalRowCount % perSheetRowCount / pageSize : (totalRowCount % perSheetRowCount / pageSize + 1));
            int num = 0;
            for (int i = 0; i < sheetCount; i++) {

                // 创建SHEET

                Sheet sheet = new Sheet(i, 0);

                sheet.setSheetName(sheetName + i);

                // 写数据 这个j的最大值判断直接拷贝就行了，不要改动

                for (int j = 0; j < (i != sheetCount - 1 ? previousSheetWriteCount : lastSheetWriteCount); j++) {

                    List<List<String>> dataList = new ArrayList<>();

                    // 此处查询并封装数据即可 currentPage, pageSize这俩个变量封装好的 不要改动

                    User user = new User();
                    user.setPageNum(num++);
                    user.setPageSize(pageSize);
                    List<User> sysSystemVOList = this.userDao.queryUser(user);

                    if (!CollectionUtils.isEmpty(sysSystemVOList)) {
                        sysSystemVOList.forEach(eachSysSystemVO -> {
                            dataList.add(Arrays.asList(
                                    eachSysSystemVO.getUserName(),
                                    eachSysSystemVO.getEmail(),
                                    eachSysSystemVO.getTelPhone(),
                                    eachSysSystemVO.getCreateUser(),
                                    eachSysSystemVO.getCreateTime().toString()
                            ));

                        });
                    }
                    writer.write0(dataList, sheet, table);

                }

            }

            // 下载EXCEL


            writer.finish();

            out.flush();

        } finally {

            if (out != null) {

                try {

                    out.close();

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }
        long end = System.currentTimeMillis();

        System.out.println("导出系统列表EXCEL成功,spend: " + (end - st) / 1000 + " ms");

    }

    @Test
    public void mutiThreadExcel() throws Exception {

        OutputStream out = null;
        System.out.println("start");
        long st = System.currentTimeMillis();
        long millis = 0;
        long milli = 0;
        try {
            out = new FileOutputStream("E:\\temp\\2019052600.xlsx");


            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称

            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            // 设置SHEET名称

            String sheetName = "用户信息sheet";

            // 设置标题

            Table table = new Table(1);

            List<List<String>> titles = new ArrayList<List<String>>();

            titles.add(Arrays.asList("姓名"));
            titles.add(Arrays.asList("邮箱"));
            titles.add(Arrays.asList("手机"));
            titles.add(Arrays.asList("创建人"));
            titles.add(Arrays.asList("创建时间"));

            table.setHead(titles);

            // 查询总数并封装相关变量(这块直接拷贝就行了不要改)
            Integer totalRowCount = userDao.count();
            milli = System.currentTimeMillis();
            System.out.println("count spend: " + (milli - st) + " ms");

            Integer pageSize = 50000;
            int queryTimes = (totalRowCount % pageSize == 0) ? (totalRowCount / pageSize) : (totalRowCount / pageSize + 1);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            CopyOnWriteArrayList<Future<List<User>>> futures = new CopyOnWriteArrayList<>();

            for (int i = 0; i < queryTimes; i++) {
                final int count = i;
                Future<List<User>> submit = executorService.submit(new Callable<List<User>>() {
                    @Override
                    public List<User> call() throws Exception {
                        User user = new User();
                        user.setPageNum(count);
                        user.setPageSize(pageSize);
                        List<User> sysSystemVOList = userDao.queryUser(user);
                        return sysSystemVOList;
                    }
                });
                futures.add(submit);
            }
            millis = System.currentTimeMillis();

            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName(sheetName);
            for (Future<List<User>> future : futures) {
                List<User> users = future.get();
                List<List<String>> dataList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(users)) {
                    users.forEach(eachSysSystemVO -> {
                        dataList.add(Arrays.asList(
                                eachSysSystemVO.getUserName(),
                                eachSysSystemVO.getEmail(),
                                eachSysSystemVO.getTelPhone(),
                                eachSysSystemVO.getCreateUser(),
                                eachSysSystemVO.getCreateTime().toString()
                        ));

                    });
                }
                writer.write0(dataList, sheet, table);
            }


            // 下载EXCEL


            writer.finish();

            out.flush();

        } finally {

            if (out != null) {

                try {

                    out.close();

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

        long end = System.currentTimeMillis();

        System.out.println("quey,spend: " + (millis - milli) + " ms");
        System.out.println("导出系统列表EXCEL成功,spend: " + (end - st) + " ms");

    }

}
