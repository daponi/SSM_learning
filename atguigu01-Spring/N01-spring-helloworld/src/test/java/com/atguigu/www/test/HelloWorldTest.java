package com.atguigu.www.test;

import com.atguigu.www.pojo.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {
    @Test
    public void testHelloWorld(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContxt.xml");
        HelloWorld hello = (HelloWorld) applicationContext.getBean("hello");
        hello.sayHello();

        String s[] = System.getProperty("java.class.path").split(";");
        for (String string : s) {
            System.out.println("--->"+string);
        }
    }
}
