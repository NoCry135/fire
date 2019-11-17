package com.ca.fire.service;

import com.ca.fire.domain.bean.Item;
import com.ca.fire.domain.bean.OrderDetail;
import com.ca.fire.domain.bean.OrderMain;
import com.ca.fire.domain.bean.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);

    User getUser(User user);

    List<User> queryUser(User user);

    Integer update(User user);

    Boolean test(OrderMain orderMain, List<OrderDetail> orderDetails, Item item, User user);
}
