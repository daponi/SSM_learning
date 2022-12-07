package com.atguigu.www.service.impl;

import com.atguigu.www.dao.UserDao;
import com.atguigu.www.service.UserService;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    UserDao userDao;

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
