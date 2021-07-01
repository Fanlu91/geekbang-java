package com.fanlu.shardingsphere;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3316/db");
        dataSource1.setUsername("root");
//        dataSource1.setPassword("mysql123!");
        dataSourceMap.put("master", dataSource1);

        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3326/db");
        dataSource2.setUsername("root");
//        dataSource2.setPassword("mysql123!");
        dataSourceMap.put("slave", dataSource2);


        // 配置读写分离规则
        MasterSlaveRuleConfiguration masterSlaveRuleConfig =
                new MasterSlaveRuleConfiguration("master_slave", "master", Arrays.asList("slave"));

        // 配置分库分表原则
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_user");
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("f_id",
                        "database${f_id % 2}"));
        tableRuleConfiguration.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("order_id",
                        "t_order_${order_id % 2}"));

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
//        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);
        shardingRuleConfiguration.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfig);

        DataSource dataSource = ShardingDataSourceFactory
                .createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());


        Connection connection = dataSource.getConnection();
        String insertSql = "insert into t_order (f_id, f_user_id,f_order_status) values (?, ?, ?)";
        String selectSql = "select * from t_order;";

        PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
        try (ResultSet rs = selectPreparedStatement.executeQuery()) {
            System.out.println("==before insert");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getInt(2));
                System.out.println(rs.getInt(3));
            }
            System.out.println("==finish read==");
        }

        System.out.println("== do insert");

        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setInt(1, (int) Math.floor(Math.random() * 1000000));
        preparedStatement.setInt(2, 2);
        preparedStatement.setInt(3, 3);
        preparedStatement.execute();


        try (ResultSet rs = selectPreparedStatement.executeQuery()) {
            System.out.println("==after insert");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getInt(2));
                System.out.println(rs.getInt(3));
            }
            System.out.println("==finish read==");
        }

        connection.close();
    }
}
