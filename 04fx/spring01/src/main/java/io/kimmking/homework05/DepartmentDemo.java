package io.kimmking.homework05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DepartmentDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Department department = (Department) context.getBean("dep1");
        System.out.println("*********depart  " + department.getClass());
        System.out.println("*********depart  " + department.toString());

        Department department2 = (Department) context.getBean("dep2");
        System.out.println("*********depart  " + department2.toString());
    }
}
