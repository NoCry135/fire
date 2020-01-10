package com.ca.fire.dao;


import com.ca.fire.domain.bean.User;
import com.ca.fire.manager.UserManager;
import com.ca.fire.test.string.RandomString;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class TestUserDao {
    private static Logger logger = LogManager.getLogger(TestUserDao.class);
    public static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String TARGET = "1234567890";
    RandomString rs = new RandomString();

    @Resource
    private UserDao userDao;

    @Resource
    private UserManager userManager;

    @Test
    public void testBatchInsert() {
        System.out.println("start!");
        int count = 150000;
        int pageSize = 500;
        long telPhone = 18500012000L;
        long start = System.currentTimeMillis();
        //创建一个对象

        Random df = new Random();
        for (int i = 0; i < count; i++) {
            List<User> list = new ArrayList<>(count);
            long st = System.currentTimeMillis();
            for (int j = 0; j < pageSize; j++) {
                String name = UUID.randomUUID().toString().substring(0, 10).toUpperCase();
                String passWord = UUID.randomUUID().toString();
                User user = new User();
                user.setUserName(name);
                user.setPassWord(passWord);
                long l = telPhone + (i * pageSize + j);
                user.setTelPhone(l + "");
                user.setEmail(user.getTelPhone() + "@qq.com");
                user.setCreateUser("sys");
                user.setUpdateUser("sys");
                user.setAge(df.nextInt(101));
                String code = rs.generateString(new Random(), TARGET, 2);
                user.setCityCode(code);
                user.setCityName(rs.generateString(new Random(), SOURCES, 2) + "-" + code);
                list.add(user);
            }
            Integer integer = userManager.batchInsert(list);
            long st1 = System.currentTimeMillis();
            logger.info("inset num:{},rows:{}, spend:{} ms", integer, (i * pageSize), (st1 - st));
            list = null;
        }

        long end = System.currentTimeMillis();
        logger.info("total spend time =:{} ms", (end - start));
        System.out.println("end!");
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("201808232313");
        user.setPassWord("201808232313");
        user.setTelPhone("201808232313");
        user.setEmail("201808232313@qq.com");
        user.setCreateUser("201808232313");
        user.setUpdateUser("苍生1");
        Integer insert = userManager.insert(user);
        logger.debug("testInsert" + insert);
    }

    @Test
    public void testSelect() {
        User userQuery = new User();
//        userQuery.setId(2L);
        userQuery.setEmail("1234567@qq.com");
//        User user = userDao.selectById(2L);
        User user = userDao.select(userQuery);

        logger.debug("testSelect" + user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2l);
        user.setPassWord("12345678");
        user.setEmail("1234567@qq.com");
        Integer update = userDao.update(user);
        logger.debug("testUpdate" + update);
    }
//    一级缓存的失效情况:
//            1.sqlSession不同.
//            2.sqlSession相同,查询条件不同.()
//            3.sqlSession相同，两次查询期间执行了增删改操作.(增删改操作可能会对当前缓存中的数据所对应的数据库的数据进行更改.)
//            4.sqlSession相同,手动了清除了缓存.


    @Test
    public void testCache() {
        User userQuery = new User();
        userQuery.setId(2L);
        userQuery.setEmail("1234567@qq.com");
//        User user = userDao.selectById(2L);
        User user1 = userDao.select(userQuery);

        logger.debug("user1" + user1.getUserName());
        User user2 = userDao.select(userQuery);
        logger.debug("user2" + user2.getUserName());
        logger.debug("user1 == user2:" + (user1 == user2));
        logger.debug("end!");

    }

    @Test
    public void testPage() {
        User userQuery = new User();
        Page<Object> page = PageHelper.startPage(0, 2);
        List<User> users = userDao.queryUser(userQuery);
        int endRow = page.getEndRow();
        int pageNum = page.getPageNum();
        int pages = page.getPages();
        long total = page.getTotal();
        logger.debug("testPage" + users + "endRow:" + endRow + "pageNum:" + pageNum + "pages:" + pages + "total:" + total);
    }


    @Test
    public void testSelectbyName() {

        List<User> users = userDao.selectByName("Jack");

        logger.debug("testPage" + users);
    }

    @Test
    public void testSelectByIds() {
        List<User> users = userDao.selectByIds(Arrays.asList(1, 2, 39, 40));
        logger.debug("testPage" + users);
    }

    @Test
    public void testSelectByNames() {
        List<User> users = userDao.selectByNames(Arrays.asList("jack", "Bom"));
        logger.debug("testPage" + users);
    }

    @Test
    public void testSelectByEmailAndName() {
        User user = userDao.selectByEmailAndName("jack", "jack@qq.com");
        logger.debug("testPage" + user);
    }

    @Test
    public void testSelectByMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", "jack");
        map.put("email", "jack@qq.com");
        List<User> users = userDao.selectByMap(map);
        logger.debug("testPage" + users);
    }

    @Test
    public void testUpdateByids() {
        User user = new User();
//        List<Long> integers = Arrays.asList(1L, 10L, 2L);
//        user.setIds(integers);
        user.setUpdateUser("admin");
        user.setUserName("雪碧");
        Long strings = userDao.updateByIDs(user);
        logger.debug("testUpdateByids:" + strings);
    }
}
