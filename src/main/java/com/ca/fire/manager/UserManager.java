package com.ca.fire.manager;

import com.ca.fire.domain.bean.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserManager {

    User getUserById(Long id);

    Integer insert(User user);


    User getUser(User user);

    List<User> queryUser(User user);

    Integer update(User user);

    @Transactional
    boolean addPerson(User user);
}
