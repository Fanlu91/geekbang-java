package io.kimmking.homework05;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlaygroundDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Playground playground = (Playground) context.getBean("getPlayground");
        System.out.println(playground);
    }
}
