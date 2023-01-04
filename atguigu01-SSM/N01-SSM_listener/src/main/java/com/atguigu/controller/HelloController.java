package com.atguigu.controller;

import com.atguigu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 用listener来整合spring和springMVC容器的理由：

 WEB服务器的三大组件中监听器Listener、过滤器Filter、程序Servlet中，listener首先最早被执行，然后是Filter，最后时Servlet。而SpringMVC的容器(随dispatcherServlet的初始化而创建)管理Controller层，
 且controller里有要依赖注入spring管理的service，所以spring的容器应先于SpringMVC容器创建，因此可以使用listener来创建spring的容器，因为Listener的监听方法是服务器启动后第一时间执行的，比DispatcherServlet和其它要早。

 Spring提供了监听器ContextLoaderListener，实现ServletContextListener接口，可监听 ServletContext的状态，所以在web服务器的启动后，使用监听器Listener来读取Spring的配置文件，
 创建Spring的IOC容器是最快的。web应用中必须在web.xml中配置
 */
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

}
