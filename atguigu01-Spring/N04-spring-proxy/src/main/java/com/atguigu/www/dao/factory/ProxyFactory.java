package com.atguigu.www.dao.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 使用jkd代理类写日志
 */
public class ProxyFactory {
    //要被代理的对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        ClassLoader loader = this.getClass().getClassLoader();
        Class<?>[] interfaces=target.getClass().getInterfaces();
        InvocationHandler h=new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //proxy表示代理对象，method表示要执行的方法，args表示要执行的方法到的参数列表
                Object result = null;

                try {
                    System.out.println("日志，方法："+method.getName()+"，参数："+ Arrays.toString(args));

                    // 存储方法的结果
                    result = null;
                    result = method.invoke(target, args);

                    System.out.println("日志，方法："+method.getName()+"，结果："+ result);
                } catch (Exception e) {
                    // e.printStackTrace();
                    System.out.println("日志，方法："+method.getName()+"，异常："+ e);
                    throw new Exception(e);
                } finally {
                    System.out.println("日志，方法："+method.getName()+"，方法执行完毕");
                }

                return result;
            }
        };

        return Proxy.newProxyInstance(loader,interfaces,h);
    }
}
