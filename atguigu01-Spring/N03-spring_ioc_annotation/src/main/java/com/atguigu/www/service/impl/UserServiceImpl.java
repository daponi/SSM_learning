package com.atguigu.www.service.impl;

import com.atguigu.www.dao.UserDao;
import com.atguigu.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
