package com.ca.fire.manager.impl;

import com.ca.fire.dao.OrderDetailDao;
import com.ca.fire.dao.OrderMainDao;
import com.ca.fire.domain.bean.OrderDetail;
import com.ca.fire.domain.bean.OrderMain;
import com.ca.fire.manager.OrderManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class OrderManagerImpl implements OrderManager {

    @Resource
    private OrderDetailDao orderDetailDao;
    @Resource
    private OrderMainDao orderMainDao;

    @Override
    @Transactional
    public Boolean save(OrderMain orderMain, List<OrderDetail> orderDetails) {
        orderMainDao.insert(orderMain);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailDao.insert(orderDetail);

        }
        return true;
    }
}
