package com.ca.fire.dao;

import com.ca.fire.domain.bean.OrderDetail;
import com.ca.fire.domain.bean.OrderMain;

import java.util.List;
import java.util.Map;

public interface OrderDetailDao {

    Integer insert(OrderDetail orderDetail);

    Integer update(OrderDetail orderDetail);

    Integer updateById(Long id);

    Integer deleteById(Long id);

    OrderDetail selectById(Long id);

    OrderDetail selectOrderDetailByCondition(OrderDetail orderDetail);

    List<OrderDetail> queryOrderDetailList(OrderDetail orderDetail);

    List<OrderDetail> queryOrderDetailPage(OrderDetail orderDetail);

    Map<String, Object> selectOrderInfoMap(String orderNo);

}
