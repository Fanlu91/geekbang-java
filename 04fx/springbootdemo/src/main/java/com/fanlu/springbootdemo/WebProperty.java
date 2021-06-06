package com.fanlu.springbootdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

//application.properties
@ConfigurationProperties(prefix = "web")
public class WebProperty {
    private String a = "defualtvalueaaa";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
