package com.atguigu.www.controller;

import com.atguigu.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    public UserService userService;

    public void saveUser() {
        userService.saveUser();
    }

    /*
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    */

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
