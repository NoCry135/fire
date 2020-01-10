package com.ca.fire.manager.impl;

import com.ca.fire.dao.UserDao;
import com.ca.fire.domain.bean.User;
import com.ca.fire.manager.UserManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component("userManager")
public class UserManagerImpl implements UserManager {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    @Transactional
    public Integer insert(User user) {
        Integer insert = 0;
        insert = userDao.insert(user);

        try {

//            int i = 10;
//            int i1 = i / 0;
            addPerson(user);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
        return insert;
    }

    @Override
    public User getUser(User user) {
        return userDao.select(user);
    }

    @Override
    public List<User> queryUser(User user) {
        return userDao.queryUser(user);
    }

    @Override
    public Integer update(User user) {
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean addPerson(User user) {
        System.out.println(1 / 0);
        return false;
    }

    @Override
    @Transactional
    public Integer batchInsert(List<User> userList) {
        return userDao.batchInsert(userList);
    }
}
