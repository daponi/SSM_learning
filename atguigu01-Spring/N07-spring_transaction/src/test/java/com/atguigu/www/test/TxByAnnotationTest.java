package com.atguigu.www.test;


import com.atguigu.www.controller.BookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 声明式事务的配置步骤：
 * 1、在Spring的配置文件中配置事务管理器
 * 2、开启事务的注解驱动
 * 在需要被事务管理的方法上，添加@Transactional注解，该方法就会被事务管理
 * @Transactional注解标识的位置：
 * 1、标识在方法上
 * 2、标识在类上，则类中所有的方法都会被事务管理
 */

//指定当前测试类在Spring的测试环境中执行，此时就可以通过注入的方式直接获取IOC容器中bean
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring测试环境的配置文件
@ContextConfiguration("classpath:txAnnotation.xml")
public class TxByAnnotationTest {
    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook(){
        bookController.buyBook(1,1);
    }


    @Test
    public void testCheckBooks(){
        bookController.checkoutBooks(1,new Integer[]{1,2});
    }
}
