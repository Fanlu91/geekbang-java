package io.kimmking.homework05;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

//@ComponentScan(basePackages = "io.kimmking.homework05")
@ComponentScan()
@Component
public class LabDemo {
    // <context:annotation-config>
    @Autowired()
    Lab myLab;
//    static Lab myLab;


    public void printLab() {
        System.out.println(myLab);
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("BeanDefinitionNames() ===>> " + String.join(",", context.getBeanDefinitionNames()));
        Lab lab = (Lab) context.getBean("myLab");
        System.out.println(lab);
        LabDemo labDemo = (LabDemo) context.getBean("labDemo");
        System.out.println(labDemo.myLab);
    }
}