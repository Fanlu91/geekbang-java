package io.kimmking.homework05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@ComponentScan(basePackages = "io.kimmking.homework05")
@ComponentScan
@Import(MySchool.class)
public class BeanDemo {

    @Autowired
    static Playground playground;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Department department = (Department) context.getBean("dep1");
        System.out.println("*********depart  " + department.getClass());
        System.out.println("*********depart  " + department.toString());
        System.out.println("*********playground  " + playground.getClass());


        System.out.println("BeanDefinitionNames() ===>> " + String.join(",", context.getBeanDefinitionNames()));

    }
}
