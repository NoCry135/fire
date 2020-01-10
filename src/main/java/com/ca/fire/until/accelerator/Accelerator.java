package com.ca.fire.until.accelerator;


import java.util.concurrent.Callable;


public abstract class Accelerator {


    protected Callable create() {
        return new Callable() {
            @Override
            public Object call() throws Exception {
//                WmsAppThreadContext.setCurrentAppContext(baseInfo.getOrgNo(),baseInfo.getDistributeNo(),baseInfo.getWarehouseNo());
//                /**设置database 路由*/
//                CommonUtil.initAppContext(baseInfo.getOrgNo() , baseInfo.getDistributeNo() , baseInfo.getWarehouseNo());
                return run();
            }
        };
    }

    public abstract Object run();
}
