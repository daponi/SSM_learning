package com.atguigu.www.test;

import com.atguigu.www.dao.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AOP的注释用法
 */
public class AopAnnotationTest {
    @Test
    public void testAopAnnotation() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-annotation.xml");
        Calculator bean = applicationContext.getBean(Calculator.class);
        bean.div(10, 2);
    }

    /**
     * List<Map>转Map
     */
    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        map.put("4", "D");
        map.put("4", "D");
        map.put("4", "D");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        Map<Object, Object> collect = list.stream().flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
        System.out.println(collect);


    }
}
