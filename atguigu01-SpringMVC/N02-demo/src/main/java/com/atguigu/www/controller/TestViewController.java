package com.atguigu.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestViewController {
    @RequestMapping("/test/view/thymeleaf")
    public String testViewThymeleaf(){
        return "success";
    }
}
