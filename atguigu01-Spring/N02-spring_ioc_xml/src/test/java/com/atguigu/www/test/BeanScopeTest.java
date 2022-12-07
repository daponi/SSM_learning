package com.atguigu.www.test;

import com.atguigu.www.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Bean的作用域
 *     scope：设置bean的作用域
 *     scope="singleton|prototype"
 *     singleton（单例）：表示获取该bean所对应的对象都是同一个
 *     prototype（多例）：表示获取该bean所对应的对象都不是同一个
 */
@Slf4j
public class BeanScopeTest {
    @Test
    public void testBeanScope(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-scope.xml");
        Student bean = context.getBean(Student.class);
        Student bean2 = context.getBean(Student.class);
        log.debug("bean:{}",bean);
        log.debug("bean2:{}",bean2);//注释ToString()可看到地址一样，所以默认是单例
        log.debug("==:{}",bean==bean2);
    }
}
