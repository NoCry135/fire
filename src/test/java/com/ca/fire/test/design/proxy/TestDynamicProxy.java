package com.ca.fire.test.design.proxy;


import com.ca.fire.test.design.proxy.dao.BaseDao;
import com.ca.fire.test.design.proxy.dao.impl.BookDao;
import com.ca.fire.test.design.proxy.dao.impl.UserDao;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 背景:有一个BaseDao定义了增删改查基本的方法,每个表都有对应的实现,现在要统计一个每个实现类update方法的执行时间
 * 分析:每个实现类都做修改太麻烦了,使用动态代理模式
 * java.lang.reflect包下有一个代理类,Proxy类的方法,可以直接生成代理类或直接生成代理类的对象
 * <p>
 * static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 直接创建一个动态代理对象
 * * 第一个参数：  被代理者的类加载器对象
 * * 第二个参数：被代理者实现了什么接口
 * * 第三个参数：代理类要做的工作是什么    代理工作处理器
 */
public class TestDynamicProxy {

    @Test
    public void testProxy() {

        BaseDao bookDao = new BookDao();
        Class<BookDao> bookDaoClass = BookDao.class;
        MyInvoke myInvoke = new MyInvoke();
        myInvoke.setDao(bookDao);
        BaseDao agent = (BaseDao) Proxy.newProxyInstance(bookDaoClass.getClassLoader(), bookDaoClass.getInterfaces(), myInvoke);
        agent.update();

        BaseDao userDao = new UserDao();
        Class<UserDao> userDaoClass = UserDao.class;
        MyInvoke myInvoke1 = new MyInvoke();
        myInvoke1.setDao(userDao);
        BaseDao agent1 = (BaseDao) Proxy.newProxyInstance(userDaoClass.getClassLoader(), userDaoClass.getInterfaces(), myInvoke1);
        agent1.update();

    }

    class MyInvoke implements InvocationHandler {
        private BaseDao dao;

        public void setDao(BaseDao dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTime = System.currentTimeMillis();
            Object invoke = method.invoke(dao, args);
            long endTime = System.currentTimeMillis();
            System.out.println("执行时间为:" + (endTime - startTime));
            return invoke;
        }
    }
}
