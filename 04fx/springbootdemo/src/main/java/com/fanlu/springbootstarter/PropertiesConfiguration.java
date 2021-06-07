package com.fanlu.springbootstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "fanlu")
public final class PropertiesConfiguration {
    private int studentId1;
    private String studentName1;
    private int studentId2;
    private String studentName2;
//    private Properties properties = new Properties();
}
