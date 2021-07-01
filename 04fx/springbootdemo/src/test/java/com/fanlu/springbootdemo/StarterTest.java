package com.fanlu.springbootdemo;

import com.fanlu.springbootstarter.PropertiesConfiguration;
import com.fanlu.springbootstarter.School;
import com.fanlu.springbootstarter.SpringBootConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class StarterTest {
    @Autowired
    School school;

    @Autowired
    PropertiesConfiguration propertiesConfiguration;
    @Test
    public void testProperties(){
        System.out.println(propertiesConfiguration.getStudentId1());
    }

    @Test
    public void test() {
        System.out.println(school.toString());
    }


}
