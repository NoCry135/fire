package com.ca.fire.test.spring.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InitBean implements InitializingBean, DisposableBean {
    private Long id;

    private String name;

    private Integer age;

    public InitBean() {
        System.out.println("构造器执行。。。");
    }

    public InitBean(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "InitBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行初始化bean方法。。。");
    }

    public void before(){
        System.out.println("执行初始化before方法。。。");
    }
    public void after(){
        System.out.println("执行初始化after方法。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行初始化destroy方法。。。");

    }
}
