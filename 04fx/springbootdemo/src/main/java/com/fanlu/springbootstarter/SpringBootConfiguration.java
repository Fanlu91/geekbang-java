package com.fanlu.springbootstarter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(PropertiesConfiguration.class)
@ConditionalOnProperty(prefix = "fanlu", name = "enabled", havingValue = "true", matchIfMissing = true)
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@RequiredArgsConstructor
//@PropertySource("classpath:application.properties")
public class SpringBootConfiguration {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private PropertiesConfiguration configuration;

    @Bean
    public Student getStudent1() {
        int studentId1 = configuration.getStudentId1();
        String studentName1 = configuration.getStudentName1();
        return new Student(studentId1, studentName1);
    }

    @Bean
    public Student getStudent2() {
        int studentId2 = configuration.getStudentId2();
        String studentName2 = configuration.getStudentName2();
        return new Student(studentId2, studentName2);
    }

    @Bean
    public Klass getKlass() {
        List<Student> list = new ArrayList<Student>(appContext.getBeansOfType(Student.class).values());
        return new Klass(list);
    }

    @Bean
    public School getSchool() {
        return new School();
    }
}