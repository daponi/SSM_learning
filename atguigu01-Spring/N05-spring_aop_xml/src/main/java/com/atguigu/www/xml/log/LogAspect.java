package com.atguigu.www.xml.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.util.Arrays;


/**
 * 基于XML的aop，主要功能代码在XML文件中
 */

@Component// @Component注解保证这个切面类能够放入IOC容器
public class LogAspect {
    private void myPointCut(){}


    public void beforeAdviceMethodAndParams(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名:"+signature+"，参数名："+ Arrays.toString(args));
    }

    public void afterAdviceMethod(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->后置通知，方法名:"+signature+"，参数名："+ Arrays.toString(args));
    }

    public void afterReturningAdviceMethod(JoinPoint joinPoint,Object result){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->返回通知，方法名:"+signature+"，参数名："+ Arrays.toString(args)+"，返回值："+result);
    }

    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable throwable){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->异常通知，方法名:"+signature+"，参数名："+ Arrays.toString(args)+"，异常是："+throwable.toString());
    }

    //环绕通知的方法的返回值一定要和目标对象方法的返回值一致
    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            System.out.println("Logger==>环绕通知-->前置通知");
            //表示目标对象方法的执行
            result = joinPoint.proceed();
            System.out.println("Logger==>环绕通知-->返回通知");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Logger==>环绕通知-->异常通知");
        } finally {
            System.out.println("Logger==>环绕通知-->后置通知");
        }
        return result;
    }
}
