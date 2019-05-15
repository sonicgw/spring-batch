package com.code.springbatch.service;

import com.code.springbatch.dao.UserDao;
import com.code.springbatch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author sonic
 * @date 2019/4/29 15:34
 */
@Component
public class DbService {
    @Autowired
    private UserDao userDao;

    public void ptUser(){
        List<User> userList = userDao.select();
        userList.forEach(
                user -> System.out.println(user.toString())
        );
    }

    public void insertUser(){
        List<User> userList = userDao.select();
        userList.forEach(
                user -> userDao.insert(user)
        );
    }
}
