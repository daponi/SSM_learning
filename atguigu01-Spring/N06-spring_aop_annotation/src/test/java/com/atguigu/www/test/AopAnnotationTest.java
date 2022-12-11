package com.atguigu.www.test;

import com.atguigu.www.dao.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAnnotationTest {
    @Test
    public void testAopAnnotation(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-aop-annotation.xml");
        Calculator bean = applicationContext.getBean(Calculator.class);
        bean.div(10, 2);
    }
}
