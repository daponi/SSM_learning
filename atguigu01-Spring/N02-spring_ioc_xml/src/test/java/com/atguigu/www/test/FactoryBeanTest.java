package com.atguigu.www.test;

import com.atguigu.www.factory.UserFactoryBean;
import com.atguigu.www.pojo.User;
import com.atguigu.www.process.MyBeanProcessor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * FactoryBean是一个接口，需要创建一个类实现该接口
 * 其中有三个方法：
 * getObject()：通过一个对象交给IOC容器管理
 * getObjectType()：设置所提供对象的类型
 * isSingleton()：所提供的对象是否单例
 * 当把FactoryBean的实现类配置为bean时，会将当前类中getObject()所返回的对象交给IOC容器管理
 *
 */
public class FactoryBeanTest {
    @Test
    public void testFactoryBean(){
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-factoryBean.xml");
        User bean = context.getBean(User.class);//配置文件没配置User.class仍能获得该Bean
        System.out.println(bean);

        UserFactoryBean bean1 = context.getBean(UserFactoryBean.class);
        bean1.say();
    }
}
