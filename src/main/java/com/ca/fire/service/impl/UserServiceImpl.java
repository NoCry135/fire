package com.ca.fire.service.impl;

import com.ca.fire.cache.BaseGuavaCache;
import com.ca.fire.domain.bean.Item;
import com.ca.fire.domain.bean.OrderDetail;
import com.ca.fire.domain.bean.OrderMain;
import com.ca.fire.domain.bean.User;
import com.ca.fire.manager.ItemManager;
import com.ca.fire.manager.OrderManager;
import com.ca.fire.manager.UserManager;
import com.ca.fire.service.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl extends BaseGuavaCache<User, User> implements UserService {
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Resource
    private UserManager userManager;

    @Resource
    private OrderManager orderManager;

    @Resource
    private ItemManager itemManager;

    @Override
    public User getUser(Long id) {
        return userManager.getUserById(id);
    }

    @Override
    public User getUser(User user) {
//        return get(user);
        return userManager.getUser(user);
    }

    @Override
    public List<User> queryUser(User user) {
        return userManager.queryUser(user);
    }

    @Override
    public Integer update(User user) {
        return userManager.update(user);
    }


    @Override
    @Transactional
    public Boolean test(OrderMain orderMain, List<OrderDetail> orderDetails, Item item, User user) {
        logger.debug("stat...");


        orderManager.save(orderMain, orderDetails);
        logger.debug("save...");

        itemManager.add(item);
        int i = 10 / 0;
        logger.debug("add...");
//        userManager.insert(user);
        logger.debug("insert...");
        logger.debug("end...");

        return true;
    }


    @Override
    public User get(User condition) {
        User user;
        if (cache.getUnchecked(condition) != null) {
            return cache.getUnchecked(condition);
        } else {
            user = userManager.getUser(condition);
            cache.put(condition, user);
        }
        return user;
    }


    private LoadingCache<User, User> cache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterAccess(2, TimeUnit.MINUTES)
            .build(new CacheLoader<User, User>() {
                @Override
                public User load(User key) throws Exception {
                    return userManager.getUser(key);
                }
            });
}
