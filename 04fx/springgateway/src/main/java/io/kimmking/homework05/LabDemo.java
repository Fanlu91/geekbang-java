package io.kimmking.homework05;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@ComponentScan(basePackages = "io.kimmking.homework05")
@ComponentScan()
public class LabDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Lab lab = (Lab) context.getBean("myLab");
        System.out.println(lab);
    }
}