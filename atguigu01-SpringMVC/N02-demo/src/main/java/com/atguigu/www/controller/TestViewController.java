package com.atguigu.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * springMVC的视图：
 * SpringMVC视图的种类很多，默认有转发视图InternalResourceView和重定向视图RedirectView，
 * 当工程引入jstl的依赖，转发视图会自动转换为JstlView
 * 若使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的视图解析器，由此视图解析器解析之后所得到的是ThymeleafView
 */
@Controller
public class TestViewController {
    @RequestMapping("/test/view/thymeleaf")
    public String testViewThymeleaf(){
        return "success";
    }

    @RequestMapping("/test/view/forward")
    public String testInternalResourceView(){
        return "forward:/test/model";
    }

    @RequestMapping("/test/view/redirect")
    public String testRedirectView(){
        return "redirect:/test/model";
    }
}
