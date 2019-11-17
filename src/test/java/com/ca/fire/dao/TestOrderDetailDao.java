package com.ca.fire.dao;

import com.ca.fire.domain.bean.OrderDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestOrderDetailDao {
    private static Logger logger = LogManager.getLogger(TestOrderDetailDao.class);


    @Resource
    private OrderDetailDao orderDetailDao;

    @Test
    public void testInsert() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGoodsNo("10000000");
        orderDetail.setNum(2);
        orderDetail.setOrderNo("2018070821030001");
        orderDetail.setPrice(new BigDecimal(200));
        orderDetail.setTitle("六神花露水");
        orderDetail.setTotalFee(new BigDecimal(10));
        orderDetail.setPicPath("");
        orderDetail.setCreateUser("system");
        orderDetail.setUpdateUser("system");
        Integer insert = orderDetailDao.insert(orderDetail);
        logger.debug("inset" + insert);

    }

    @Test
    public void testSelectById() {
        OrderDetail detail = orderDetailDao.selectById(1L);
        logger.debug("detail" + detail);

    }

    @Test
    public void selectOrderInfoMap() {
        Map<String, Object> stringListMap = orderDetailDao.selectOrderInfoMap("201902232145");
        logger.debug("detail" + stringListMap);

    }

    /**
     * 状态为0,1的是一组,2的是一组
     */
    @Test
    public void selectGroup() {
        OrderDetail orderDetail = new OrderDetail();

        List<OrderDetail> orderDetails = orderDetailDao.queryOrderDetailList(orderDetail);


        logger.debug("orderDetails" + orderDetails);

    }
}
