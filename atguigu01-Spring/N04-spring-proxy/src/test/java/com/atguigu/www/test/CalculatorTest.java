package com.atguigu.www.test;


import com.atguigu.www.dao.Calculator;
import com.atguigu.www.dao.factory.ProxyFactory;
import com.atguigu.www.dao.impl.CalculatorImpl;
import com.atguigu.www.dao.proxy.CalculatorStaticProxy;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testStaticLog(){
        CalculatorStaticProxy proxy=new CalculatorStaticProxy(new CalculatorImpl());
        proxy.add(3,2);

    }

    @Test
    public void testDynamicLog(){
        ProxyFactory proxyFactory =  new ProxyFactory(new CalculatorImpl());
        // 不知道动态代理生成的类型，可向上转型成接口，获取其方法
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1, 3);
    }
}
