package com.fanlu.springbootdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebConfiguration.class) // 把一个类作为bean引进来
@EnableConfigurationProperties(WebProperty.class) // 可以自动配置属性
public class WebAutoConfiguration {
    @Autowired
    WebConfiguration configuration;

    @Autowired
    WebProperty property;

    @Bean
    public WebInfo createInfo() {
        return new WebInfo(configuration.name + "-" + property.getA());
    }
}
