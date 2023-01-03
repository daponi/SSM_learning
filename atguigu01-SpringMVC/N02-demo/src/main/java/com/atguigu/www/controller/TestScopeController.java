package com.atguigu.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Date:2022/7/7
 * Author:ybc
 * Description:
 * 向域对象共享数据：
 * 1、通过ModelAndView向请求域共享数据
 * 使用ModelAndView时，可以使用其Model功能向请求域共享数据
 * 使用View功能设置逻辑视图，但是控制器方法一定要将ModelAndView作为方法的返回值
 * 2、使用Model向请求域共享数据
 * 3、使用ModelMap向请求域共享数据
 * 4、使用map向请求域共享数据
 * 5、Model和ModelMap和map的关系
 * 其实在底层中，这些类型的形参最终都是通过BindingAwareModelMap创建
 * public class BindingAwareModelMap extends ExtendedModelMap {}
 * public class ExtendedModelMap extends ModelMap implements Model {}
 * public class ModelMap extends LinkedHashMap<String, Object> {}
 */
@Controller
public class TestScopeController {
    @RequestMapping("/test/mav")
    public ModelAndView testMav(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testRequestScope" , "hello ,ModelAndView");
        modelAndView.setViewName("success");
        System.out.println(modelAndView.getClass().getName());
        return  modelAndView;
    }

    @RequestMapping("/test/model")
    public String testModel(Model model){
        model.addAttribute("testRequestScope" , "hello ,Model");
        System.out.println(model.getClass().getName());
        return "success";
    }

    @RequestMapping("/test/modelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope" , "hello ,Model");
        System.out.println(modelMap.getClass().getName());
        return "success";
    }

    @RequestMapping("/test/map")
    public String testMap(Map<String, Object> map){ //交给Dispatcherservlet来创建,表示往请求域中共享数据
        map.put("testScope", "hello,Map");
        System.out.println(map.getClass().getName());
        return "success";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope", "hello,session");
        return "success";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope", "hello,application");
        return "success";
    }
}
