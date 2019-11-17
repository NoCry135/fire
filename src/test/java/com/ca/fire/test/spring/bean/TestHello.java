package com.ca.fire.test.spring.bean;

import com.ca.fire.domain.PragramerEmployee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHello {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext act = new ClassPathXmlApplicationContext("spring-bean.xml");

        PragramerEmployee bean = act.getBean(PragramerEmployee.class);
        System.out.println(bean);

//        DeptMent deptMent = act.getBean(DeptMent.class);
        Object tec = act.getBean("tec");
        Object dev = act.getBean("dev");

        System.out.println(tec);
        System.out.println(dev);


        Object userMap = act.getBean("userMap");
        System.out.println(userMap);

//        AbsSayHello bean = act.getBean(AbsSayHello.class);
//        bean.say("welcome");
//        ChangeMethodBean methodBean = act.getBean(ChangeMethodBean.class);
//        methodBean.method();

    }
}
