package io.kimmking.spring01;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {

    private int id;
    private String name;

    // lombok注解自动帮忙创建set get方法
    // 因为继承 BeanNameAware, ApplicationContextAware，Spring会在bean生成时调用set方法赋值
    private String beanName;
    private ApplicationContext applicationContext;

    public void init() {
        System.out.println("hello...........");
    }

//    public Student create(){
//        return new Student(101,"KK101");
//    }

    public void print() {
        System.out.println(this.beanName);
        System.out.println("   context.getBeanDefinitionNames() ===>> "
                + String.join(",", applicationContext.getBeanDefinitionNames()));
    }


}
