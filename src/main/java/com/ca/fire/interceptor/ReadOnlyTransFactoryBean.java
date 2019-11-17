package com.ca.fire.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;

public class ReadOnlyTransFactoryBean<T> implements MethodInterceptor, FactoryBean<T>, ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(ReadOnlyTransFactoryBean.class);

    /**
     * 代理的Service类
     */
    Class<T> serviceInterface;

    /**
     * 代理的Service名
     */
    String delegateBeanName;

    ApplicationContext applicationContext;

    public Class<T> getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(Class<T> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public String getDelegateBeanName() {
        return delegateBeanName;
    }

    public void setDelegateBeanName(String delegateBeanName) {
        this.delegateBeanName = delegateBeanName;
    }

    Enhancer enhancer = new Enhancer();

    @Override
    public T getObject() throws Exception {
        // 使用CGlib增强，提供代理功能
        enhancer.setSuperclass(serviceInterface);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Class<?> getObjectType() {
        return this.serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        T service = applicationContext.getBean(delegateBeanName, serviceInterface);
        DataSourceTransactionManager txManager = applicationContext.getBean(DataSourceTransactionManager.class);
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        definition.setReadOnly(true);
        logger.info("Start ReadOnly Transactional!");
        TransactionStatus transaction = txManager.getTransaction(definition);
        Object result = method.invoke(service, args);
        if (!transaction.isCompleted()) {
            txManager.commit(transaction);
        }
        logger.info("End ReadOnly Transactional!");
        return result;
    }
}
