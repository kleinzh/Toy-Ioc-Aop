package org.example.ioc.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class XmlAnnoLogUtils {

    /**
     * 我们在xml中已经使用了通用切入点表达式，供多个切面使用
     * 注解里面怎么使用呢
     * 第一步: 编写一个方法
     * 第二步： 在方法使用@Pointcut注解
     * 第三步: 给注解的value熟悉提供切入点表达式
     * 引入切入点时候，必须是方法名+() 例如pointcut()
     * 在当前切面中使用，可以直接写方法名。在其他切面中使用必须使用全限定方法名
     */
    @Pointcut("execution(* org.example.ioc.service.impl.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforePrintLog(JoinPoint jp) {
        Object[] args = jp.getArgs();
        System.out.println("前置通知：beforePrintLog，参数是：" +
                Arrays.toString(args));
    }

    @AfterReturning(value = "pointcut()", returning = "rtValue")
    public void afterReturningPrintLog(Object rtValue) {
        System.out.println("后置通知：afterReturningPrintLog，返回值 是：" + rtValue);
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowingPrintLog(Throwable e) {
        System.out.println("异常通知：afterThrowingPrintLog，异常是：" + e);
    }

    @After("pointcut()")
    public void afterPrintLog() {
        System.out.println("最终通知：afterPrintLog");
    }

    public void arroundPrintLog(ProceedingJoinPoint proceedingJoinPoint) {
        //定义返回值
        Object rtValue=null;

        try {
            //前置通知
            System.out.println("前置通知");

            //1.获取参数
            Object[] args=proceedingJoinPoint.getArgs();

            //2.执行切入点方法
            rtValue=proceedingJoinPoint.proceed(args);

            // 后置通知
            System.out.println("后置通知");
        } catch (Throwable throwable) {

            // 异常通知
            System.out.println("异常通知");
        } finally {
            System.out.println("最终通知");
        }
    }

}