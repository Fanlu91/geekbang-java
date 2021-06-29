package com.fanlu.hikari;

import com.fanlu.hikari.rws.DataSourceEnum;
import com.fanlu.hikari.rws.DatabaseContextHolder;
import com.fanlu.hikari.rws.MyDataSourceRouter;
import com.fanlu.hikari.rws.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * 通过设置DatabaseContextHolder
 * 调整数据源
 * 一共有三个，
 * 远程的origin，查询表sensor
 * 本地的master和slave，查询t1
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.fanlu.hikari")
public class HikariApplication implements CommandLineRunner {

    @Autowired
    private SensorDAO sensorDAO;

    @Autowired
    private TestDAO testDAO;

    public static void main(String[] args) {
        SpringApplication.run(HikariApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===run application");
        System.out.println("===origin datasource");
        List<Sensor> users = sensorDAO.findAll();
        users.stream().forEach(System.out::println);
        System.out.println("===master datasource");
        DatabaseContextHolder.set(DataSourceEnum.MASTER);
        System.out.println(testDAO.getFirstId());
        System.out.println("===slave datasource");
        DatabaseContextHolder.set(DataSourceEnum.SLAVE);
        System.out.println(testDAO.getFirstId());
        System.out.println("===Finish");
    }
}