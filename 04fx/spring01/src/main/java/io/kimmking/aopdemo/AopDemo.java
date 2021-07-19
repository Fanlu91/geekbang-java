package io.kimmking.aopdemo;

import io.kimmking.aop.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AopDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        ISchool demoSchool = (ISchool) context.getBean("demoSchool");
        demoSchool.ding();
        System.out.println("AOP代理后的实际类型：" + demoSchool.getClass());
    }
}
