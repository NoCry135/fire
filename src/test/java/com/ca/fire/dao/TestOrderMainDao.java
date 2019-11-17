package com.ca.fire.dao;

import com.ca.fire.domain.bean.OrderMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestOrderMainDao {
    private static Logger logger = LogManager.getLogger(TestOrderMainDao.class);
    private List<Integer> initList = Arrays.asList(0, 1);

    private List<Integer> payList = Arrays.asList(2);
    @Resource
    private OrderMainDao orderMainDao;

    @Test
    public void testInsert() {
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderNo("2018070821030001");
        orderMain.setPayment(new BigDecimal(3000));
        orderMain.setPaymentType(1);
        orderMain.setPostFee(new BigDecimal(20));
        orderMain.setStatus(0);
        orderMain.setPaymentTime(new Date());
        orderMain.setCreateUser("system");
        orderMain.setUpdateUser("system");
        Integer insert = orderMainDao.insert(orderMain);
        logger.debug("insert" + insert);
    }

    @Test
    public void testSelect() {
        OrderMain orderMain = new OrderMain();

        List<OrderMain> orderMains = orderMainDao.queryOrderMainList(orderMain);

        Map<Integer, List<OrderMain>> listMap = orderMains.stream().collect(Collectors.groupingBy(t -> getCode(t.getStatus())));


        logger.debug("insert" + listMap);
    }

    private Integer getCode(Integer code) {
        if (initList.contains(code)) {
            return 1;
        } else if (payList.contains(code)) {
            return 2;
        } else {
            return 3;
        }
    }

}
