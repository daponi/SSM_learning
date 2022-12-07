package com.atguigu.www.controller;

import com.atguigu.www.service.UserService;
import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    private UserService userService;

    public void saveUser(){
    userService.saveUser();

    }
}
