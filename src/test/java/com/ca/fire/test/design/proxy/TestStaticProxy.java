package com.ca.fire.test.design.proxy;

import org.junit.Test;

/**
 * 代理模式
 * 生活中:中介公司,经纪人,被代理者做实际需要的事情,代理人在被代理者做事前后做一些工作
 * 举一个明星开演唱会和租房子的例子
 * 总结:代理人与被代理人都需要实现共同的接口; 代理人类又有一个被代理的对象,通过构造器或set注入
 * <p>
 * 不足：
 * *
 * * 1、当增加委托人,导致 需要添加更多的代理人
 * * 2、委托人和代理人之间依赖性很高,委托人完全依赖于特定的代理人,比较适合私人定制
 * *
 */


public class TestStaticProxy {

    @Test
    public void testShow() {
        Show agent = new Agent(new Singner());
        agent.sing();
    }
}


/**
 * 主题:表演唱歌,代理者与被代理人都需要实现这个接口
 */
interface Show {
    void sing();
}

/**
 * 明星表演唱歌一曲西海情歌
 */
class Singner implements Show {

    @Override
    public void sing() {
        System.out.println("开始表演:你曾答应过我不会让我把你找不见....");
    }
}

/**
 * 经纪人,负责安排场地,收门票,打扫场地
 */
class Agent implements Show {

    private Show show;

    public Agent(Show show) {
        this.show = show;
    }


    @Override
    public void sing() {
        doBefore();
        show.sing();
        doAfter();
    }


    private void doBefore() {
        System.out.println("为刀郎演唱会准备场地,售票....");
    }

    private void doAfter() {
        System.out.println("演唱会结束,交还场地,分钱...");
    }
}