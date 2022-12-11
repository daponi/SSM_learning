package com.atguigu.www.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.util.Arrays;


/**
 * 1、在切面中，需要通过指定的注解将方法标识为通知方法
 * @Before：前置通知，在目标对象方法执行之前执行
 * @After：后置通知，在目标对象方法的finally字句中执行
 * @AfterReturning：返回通知，在目标对象方法返回值之后执行
 * @AfterThrowing：异常通知，在目标对象方法的catch字句中执行
 *
 *
 * 2、切入点表达式：设置在标识通知的注解的value属性中
 * execution(public int com.atguigu.spring.aop.annotation.CalculatorImpl.add(int, int)
 * execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..)
 * 第一个*表示任意的访问修饰符和返回值类型
 * 第二个*表示类中任意的方法
 * ..表示任意的参数列表
 * 类的地方也可以使用*，表示包下所有的类
 * 3、重用切入点表达式
 * //@Pointcut声明一个公共的切入点表达式
 * @Pointcut("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
 * public void pointCut(){}
 * 使用方式：@Before("pointCut()")
 *
 * 4、获取连接点的信息
 * 在通知方法的参数位置，设置JoinPoint类型的参数，就可以获取连接点所对应方法的信息
 * //获取连接点所对应方法的签名信息
 * Signature signature = joinPoint.getSignature();
 * //获取连接点所对应方法的参数
 * Object[] args = joinPoint.getArgs();
 *
 * 5、切面的优先级
 * 可以通过@Order注解的value属性设置优先级，默认值Integer的最大值
 * @Order注解的value属性值越小，优先级越高
 *
 */

@Component// @Component注解保证这个切面类能够放入IOC容器
@Aspect// @Aspect表示这个类是一个切面类
public class LogAspect {
    @Pointcut("execution(* com.atguigu.www.dao.impl.*.*(..))")
    private void myPointCut(){}

    // @Before("execution(public int com.atguigu.www.dao.impl.CalculatorImpl.add(int,int))")
    // @Before("execution(* com.atguigu.www.dao.impl.CalculatorImpl.*(..))")
    // @Before("myPointCut()")
    // public void beforeAdviceMethod(){
    //     System.out.println("Logger-->前置通知，方法名");
    // }

    @Before("myPointCut()")
    public void beforeAdviceMethodAndParams(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名:"+signature+"，参数名："+ Arrays.toString(args));
    }

    @After("myPointCut()")
    public void afterAdviceMethod(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->后置通知，方法名:"+signature+"，参数名："+ Arrays.toString(args));
    }

    @AfterReturning(value = "myPointCut()",returning = "result")
    public void afterReturningAdviceMethod(JoinPoint joinPoint,Object result){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->返回通知，方法名:"+signature+"，参数名："+ Arrays.toString(args)+"，返回值："+result);
    }

    @AfterThrowing(value = "myPointCut()",throwing = "throwable")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable throwable){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->异常通知，方法名:"+signature+"，参数名："+ Arrays.toString(args)+"，异常是："+throwable.toString());
    }

    @Around("myPointCut()")
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
