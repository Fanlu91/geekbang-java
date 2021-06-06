package com.fanlu.springbootstarter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(PropertiesConfiguration.class)
@ConditionalOnProperty(prefix = "fanlu", name = "enabled", havingValue = "true", matchIfMissing = true)
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@RequiredArgsConstructor
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@PropertySource("classpath:application.properties")
public class SpringBootConfiguration {

    @Autowired
    private PropertiesConfiguration configuration;

    @Bean
    public Klass getKlass() {
//        int studentId1 = Integer.parseInt(configuration.getProperties().getProperty("studentId1"));
//        String studentName1 = configuration.getProperties().getProperty("studentName1");
//        Student student1 = new Student(studentId1, studentName1);
//
//        int studentId2 = Integer.parseInt(configuration.getProperties().getProperty("studentId1"));
//        String studentName2 = configuration.getProperties().getProperty("studentName1");
//        Student student2 = new Student(studentId2, studentName2);

        List<Student> list = new ArrayList<Student>() {
//            {
//                add(student1);
//                add(student2);
//            }
        };
        return new Klass(list);
    }

    @Bean
    public School getSchool() {
        return new School();
    }
}