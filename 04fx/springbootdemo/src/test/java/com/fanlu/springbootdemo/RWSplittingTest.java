package com.fanlu.springbootdemo;

import com.fanlu.hikari.rws.DataSourceEnum;
import com.fanlu.hikari.rws.DatabaseContextHolder;
import com.fanlu.hikari.rws.TestDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class RWSplittingTest {

    @Autowired
    TestDAO testDAO;

    @Test
    public void testProperties() {
        DatabaseContextHolder.set(DataSourceEnum.SLAVE);
        String id = testDAO.getFirstId();
        System.out.println(id);
        DatabaseContextHolder.clear();
    }
}
