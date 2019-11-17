package com.ca.fire.manager;

import com.ca.fire.dao.OrderDetailDao;
import com.ca.fire.dao.OrderMainDao;
import com.ca.fire.domain.bean.OrderDetail;
import com.ca.fire.domain.bean.OrderMain;

import javax.annotation.Resource;
import java.util.List;

public interface OrderManager {


    Boolean save(OrderMain orderMain, List<OrderDetail> orderDetails);
}
