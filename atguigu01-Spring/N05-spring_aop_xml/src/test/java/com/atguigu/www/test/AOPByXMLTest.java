package com.atguigu.www.test;

import com.atguigu.www.xml.dao.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPByXMLTest {
    @Test
    public void testAopByXml(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-aop-xml.xml");
        Calculator bean = applicationContext.getBean(Calculator.class);
        // bean.add(2, 3);
        bean.div(10, 2);
    }
}
