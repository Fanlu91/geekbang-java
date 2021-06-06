package com.fanlu.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// SpringBootApplication 是一个组合注解
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

    @Autowired
    WebInfo webInfo;

    public void printInfo() {
        System.out.println(webInfo.getName());
    }
}
