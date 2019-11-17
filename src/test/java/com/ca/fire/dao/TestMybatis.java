package com.ca.fire.dao;

import com.ca.fire.domain.bean.OrderMain;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.List;

public class TestMybatis {
    @Test
    public void testConnection() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-config.xml");

        SqlSessionFactoryBean factoryBean = applicationContext.getBean(SqlSessionFactoryBean.class);
        //1. 先获取SQLSessionFactory
        SqlSessionFactory sessionFactory = factoryBean.getObject();
        //2. 获取SQLSession
        SqlSession sqlSession = sessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        Configuration configuration = sqlSession.getConfiguration();
        //3.获取映射文件
        OrderMainDao mapper = sqlSession.getMapper(OrderMainDao.class);
        OrderMain condition = new OrderMain();
        //4.执行查询
        List<OrderMain> orderMains = mapper.queryOrderMainList(condition);
        orderMains.stream().forEach(System.out::println);
        sqlSession.close();


    }
}
