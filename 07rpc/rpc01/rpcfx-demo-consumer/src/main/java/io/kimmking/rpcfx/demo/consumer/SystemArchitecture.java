package io.kimmking.rpcfx.demo.consumer;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {

    //    @Pointcut("execution (* io.kimmking.rpcfx.demo.api.UserService.*(..))")
    @Pointcut("bean(io.kimmking.rpcfx.demo.api.UserService)")
    private void aspectUserService() {
        System.out.println("============进入aspect方法==============");
    }
}
