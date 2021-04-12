package org.example.ioc.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

/**
 * @Classname LogUtils
 * @Description TODO
 * @Date 2021-04-11 20:07
 * @Created by Klein
 */
public class LogUtils {

  /**
   * 业务逻辑开始之前
   */
  public void beforeMethod(JoinPoint joinPoint) {
    System.out.println(JsonUtils.object2Json(joinPoint.getArgs()));
    System.out.println("业务逻辑开始执行之前.....");
  }

  /**
   * 业务逻辑结束时候执行(无论异常与否)
   */
  public void afterMethod() {
    System.out.println("业务逻辑结束时候执行，无论异常与否都执行.....");
  }

  /**
   * 异常时候执行
   */
  public void exceptionMethod() {
    System.out.println("业务逻辑异常时候执行.....");
  }


  /**
   * 业务逻辑正常时候执行
   */
  public void successMethod() {
    System.out.println("业务逻辑正常时候执行.....");
  }

  /**
   * 业务环绕通知
   */
  public void arroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("业务环绕通知Before.....");

    Object result=null;

    try {
      System.out.println("业务环绕通知Running.....");
      // 类似Method.invoke();
      // 可以控制原有业务是否可以执行
       result=proceedingJoinPoint.proceed();
    } catch (Exception exception) {
      System.out.println("环绕通知中的Exception....");
    } finally {
      System.out.println("业务环绕通知After.....");
    }
  }

}
