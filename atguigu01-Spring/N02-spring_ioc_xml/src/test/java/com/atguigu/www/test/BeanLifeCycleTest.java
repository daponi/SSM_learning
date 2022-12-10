package com.atguigu.www.test;

import com.atguigu.www.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean的生命周期
 * <p>
 * 1、实例化（调用无参构造器）
 * 2、依赖注入，给bean对象设置属性
 * 3、后置处理器的postProcessBeforeInitialization
 * 4、初始化，需要通过bean的init-method属性指定初始化的方法
 * 5、后置处理器的postProcessAfterInitialization
 * 6、IOC容器关闭时销毁，需要通过bean的destroy-method属性指定销毁的方法
 * <p>
 * bean的后置处理器会在生命周期的初始化前后添加额外的操作
 * 需要实现BeanPostProcessor接口且配置到IOC容器中
 * 需要注意的是，bean后置处理器不是单独针对某一个bean生效，而是针对IOC容器中所有bean都会执行
 * <p>
 * 注意：
 * 若bean的作用域为单例时，生命周期的前三个步骤会在获取IOC容器时执行
 * 若bean的作用域为多例时，生命周期的前三个步骤会在获取bean时执行
 */

@Slf4j
public class BeanLifeCycleTest {
    @Test
    public void testBeanLifeCycle() {
        // ApplicationContext context=new ClassPathXmlApplicationContext("spring-beanLifeCycle.xml");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-beanLifeCycle.xml");
        User bean = context.getBean(User.class);
        System.out.println("生命周期：4、通过IOC容器获取bean并使用");
        // 关闭IOC容器
        context.close();
    }
}
