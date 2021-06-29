package com.fanlu.hikari.rws;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class MyDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceEnum dataSourceEnum = DatabaseContextHolder.getCurrentDataSource();
        System.out.println("== routing data source address is " + dataSourceEnum.name());
        return dataSourceEnum;
    }
}