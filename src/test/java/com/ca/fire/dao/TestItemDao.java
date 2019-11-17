package com.ca.fire.dao;

import com.ca.fire.domain.bean.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestItemDao {
    private static Logger logger = LogManager.getLogger(TestItemDao.class);

    @Resource
    private ItemDao itemDao;

    public void testIoc(){

        ApplicationContext app = new AnnotationConfigApplicationContext(ItemDao.class);
//        app.getBean();

    }


    @Test
    public void testInsert() {
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
        Integer insert = itemDao.insert(item);
        logger.debug("insert" + insert);
    }
}
