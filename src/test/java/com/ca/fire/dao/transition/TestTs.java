package com.ca.fire.dao.transition;

import com.ca.fire.dao.TestItemDao;
import com.ca.fire.domain.bean.Item;
import com.ca.fire.domain.bean.OrderDetail;
import com.ca.fire.domain.bean.OrderMain;
import com.ca.fire.domain.bean.User;
import com.ca.fire.manager.UserManager;
import com.ca.fire.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestTs {
    private static Logger logger = LogManager.getLogger(TestTs.class);

    @Resource
    private UserService userService;
    @Resource
    private UserManager userManager;

    @Test
    public void test01() {
        User user = new User();
        user.setUserName("201808232313");
        user.setPassWord("201808232313");
        user.setTelPhone("201808232313");
        user.setEmail("201808232313@qq.com");
        user.setCreateUser("201808232313");
        user.setUpdateUser("苍生1");

        List<OrderDetail> list = new ArrayList<>();
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
        list.add(orderDetail);

        OrderMain orderMain = new OrderMain();
        orderMain.setOrderNo("2018070821030001");
        orderMain.setPayment(new BigDecimal(3000));
        orderMain.setPaymentType(1);
        orderMain.setPostFee(new BigDecimal(20));
        orderMain.setStatus(0);
        orderMain.setPaymentTime(new Date());
        orderMain.setCreateUser("system");
        orderMain.setUpdateUser("system");


        Item item = new Item();
        item.setGoodsNo("");
        item.setPrice(new BigDecimal(200));
        item.setImage("xiaoping");
        item.setSellPoint("happy");
        item.setTitle("sixGod");
        item.setStock(2000);
        item.setBarcode("123456");
        item.setCreateUser("system");
        item.setUpdateUser("system");
        Boolean test = userService.test(orderMain, list, item, user);

        logger.debug("finish!" + test);
    }

    @Test
    public void test02() {
        User user = new User();
        user.setUserName("201808232313");
        user.setPassWord("201808232313");
        user.setTelPhone("201808232313");
        user.setEmail("201808232313@qq.com");
        user.setCreateUser("201808232313");
        user.setUpdateUser("苍生1");
        Integer insert = userManager.insert(user);

        logger.debug("finish!" + insert);
    }
}
