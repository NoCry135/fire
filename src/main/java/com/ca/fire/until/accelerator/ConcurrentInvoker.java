package com.ca.fire.until.accelerator;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public abstract class ConcurrentInvoker {
    public ConcurrentInvoker(List list) {
        this.list = list;
    }

    private List list;

    protected Callable invoke() {
        return new Callable() {
            @Override
            public Object call() throws Exception {
//                WmsAppThreadContext.setCurrentAppContext(baseInfo.getOrgNo(),baseInfo.getDistributeNo(),baseInfo.getWarehouseNo());
                /* *设置database 路由*/
                /*CommonUtil.initAppContext(baseInfo.getOrgNo() , baseInfo.getDistributeNo() , baseInfo.getWarehouseNo());*/
                log.info("[多线程]线程名称：" + Thread.currentThread().getName() + ",入参：" + JSON.toJSONString(list));
                return run(list);
            }
        };
    }

    public abstract Object run(List list);
}
