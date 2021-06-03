package io.kimmking.homework05;

import io.kimmking.aop.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过 aspectj-autoproxy 可强制使用cglib
 * <aop:aspectj-autoproxy proxy-target-class="true" />
 */
public class AopDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ISchool mySchool = (ISchool) context.getBean("myschool");
        mySchool.ding();
        System.out.println("AOP代理后的实际类型：" + mySchool.getClass());
    }
}
