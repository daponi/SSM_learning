package com.atguigu.www.test;

import org.junit.Test;
import test.Student;
import test.Teacher;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 测试反射
 */
public class Reflect {
    @Test
    public void testReflect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class className = Class.forName("test.Student");
        Object obj = className.newInstance();
        Method smile = className.getMethod("smile");//只能获取public方法
        smile.setAccessible(true);//设置后，即可调用私有方法
        Method[] methods = className.getMethods();// 全部方法
        Method[] declaredMethods = className.getDeclaredMethods();//该类实现的方法，不包括继承的方法
        smile.invoke(obj);

        System.out.println("\n------------------打印getMethods()----------------\n");
        Arrays.stream(methods).collect(Collectors.toList()).forEach(ele-> System.out.println("======methods         "+ele));
        System.out.println("\n------------------getDeclaredMethods()----------------\n");
        Arrays.stream(declaredMethods).collect(Collectors.toList()).forEach(ele-> System.out.println(">>>>>>>>declaredMethods         "+ele));

        declaredMethods[2].setAccessible(true);
        declaredMethods[2].invoke(obj);
        declaredMethods[3].setAccessible(true);
        declaredMethods[3].invoke(obj);
    }
    @Test
    public void testTeacher() throws ClassNotFoundException, IllegalAccessException {
        Class clazz = Class.forName("test.Teacher");
        Teacher teacher = new Teacher(1,"Li");

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);//Teacher类中的成员变量为private,故必须进行此操作强制访问
            System.out.println(field.get(teacher));//获取当前对象中当前Field的value
        }
    }
}
