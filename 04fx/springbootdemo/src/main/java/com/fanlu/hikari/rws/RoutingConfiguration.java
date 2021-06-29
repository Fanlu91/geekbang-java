package com.fanlu.hikari.rws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@PropertySource("classpath:config/jdbc.properties")
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.fanlu.rwsplitting")
@Configuration
public class RoutingConfiguration {

    @Bean
    public JdbcTemplate applicationDataConnection() {
        return new JdbcTemplate(myDataSourceRouter());
    }

    @Bean
    public MyDataSourceRouter myDataSourceRouter() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource master = masterDataSource();
        DataSource slave = slaveDataSource();
        DataSource orgin = orginDataSource();

        targetDataSources.put(DataSourceEnum.MASTER, master);
        targetDataSources.put(DataSourceEnum.SLAVE, slave);
        targetDataSources.put(DataSourceEnum.ORIGIN, orgin);

        MyDataSourceRouter myDataSourceRouter = new MyDataSourceRouter();
        myDataSourceRouter.setTargetDataSources(targetDataSources);
        myDataSourceRouter.setDefaultTargetDataSource(orgin);
        return myDataSourceRouter;
    }

    @Bean("origin")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource orginDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.rw.master.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.rw.slave.datasource")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }
}
