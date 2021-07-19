package io.kimmking.rpcfx.demo.consumer;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// @AspectJ 注解只能作用于Spring Bean 上面，所以你用 @Aspect 修饰的类要么是用 @Component注解修饰，要么是在 XML中配置过的。
@Aspect
@Component
public class Advice {

    @Pointcut("bean(io.kimmking.rpcfx.demo.api.UserService)")
    private void aspectUserService() {
        System.out.println("============进入aspect方法==============");
    }

    //前置通知等可以没有JoinPoint参数
    @Before("aspectUserService()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========执行前置通知===============");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspectUserService()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("===========执行后置通知==============");

    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("aspectUserService()")
    public void afterReturn(JoinPoint joinPoint) {
        System.out.println("===========执行后置返回通知==============");

    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "aspectUserService()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        System.out.println("===========抛出异常后通知==============");
    }

}
