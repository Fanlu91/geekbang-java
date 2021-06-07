package com.fanlu.hikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.util.List;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.fanlu.hikari")
public class HikariApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SensorDAO sensorDAO;

    public static void main(String[] args) {
        SpringApplication.run(HikariApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Sensor> users = sensorDAO.findAll();
        users.stream().forEach(System.out::println);
    }
}