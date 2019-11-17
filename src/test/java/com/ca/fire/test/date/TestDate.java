package com.ca.fire.test.date;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestDate {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Test
    public void testCompareDate() {
        String date1 = "2018-07-01 00:00:00";
        String date2 = "2018-07-02 00:00:00";
        String date3 = "2018-07-03 00:00:00";
        String date4 = "2018-07-04 00:00:00";
        String date5 = "2018-07-04 00:00:00";
        Set<Date> groupExpireDateSet = Sets.newHashSet();
        Set<Date> normalExpireDateSet = Sets.newHashSet();
        try {
            Date parse01 = sdf.parse(date1);
            Date parse02 = sdf.parse(date2);
            System.out.println(parse01.compareTo(parse02));
            groupExpireDateSet.add(sdf.parse(date1));
            groupExpireDateSet.add(sdf.parse(date2));
            groupExpireDateSet.add(sdf.parse(date3));
            normalExpireDateSet.add(sdf.parse(date4));
            normalExpireDateSet.add(sdf.parse(date5));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean isLocateGroup = getNeareastExpirStockIsLocateGroup(groupExpireDateSet, normalExpireDateSet);
        System.out.println(isLocateGroup);
    }

    /**
     * 确定是否需要定位团购区
     *
     * @param groupExpireDateSet
     * @param normalExpireDateSet
     * @return fasle:不需要;true:需要
     */
    private boolean getNeareastExpirStockIsLocateGroup(Set<Date> groupExpireDateSet, Set<Date> normalExpireDateSet) {
        boolean flag = false;
        if (CollectionUtils.isEmpty(groupExpireDateSet) && CollectionUtils.isEmpty(normalExpireDateSet)) {
            throw new RuntimeException("客单保质期策略异常,保质期商品在团购区和非团购库存都为空!");
        }
        if (CollectionUtils.isEmpty(groupExpireDateSet)) {
            return flag;
        }
        for (Date groupExpireDate : groupExpireDateSet) {
            for (Date normalExpireDate : normalExpireDateSet) {
                if (groupExpireDate.compareTo(normalExpireDate) < 0) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Test
    public void testParse() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> dateStrList = Lists.newArrayList(
                "2018-04-01 10:00:01",
                "2018-04-02 11:00:02",
                "2018-04-03 12:00:03",
                "2018-04-04 13:00:04",
                "2018-04-05 14:00:05"
        );

//        多线程时不能使用SimpleDateFormat。原因很简单SimpleDateFormat是线程不安全的。
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (String str : dateStrList) {
            executorService.execute(() -> {
                try {
                    Date parse = simpleDateFormat.parse(str);
                    System.out.println(parse);
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    public void testDate() throws ParseException {
        String t1 = "2019-04-04";
        String t2 = "2019-02-27";
        String t3 = "2019-03-04";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf.parse(t1);
        Date date2 = sdf.parse(t2);
        Date date3 = sdf.parse(t3);
        long remainDays = (date1.getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000) + 2;
        System.out.println(remainDays);
        remainDays = (date2.getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000) + 2;
        System.out.println(remainDays);

        remainDays = (date3.getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000) + 2;
        System.out.println(remainDays);

    }
}
