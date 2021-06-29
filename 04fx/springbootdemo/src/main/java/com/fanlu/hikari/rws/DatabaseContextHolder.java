package com.fanlu.hikari.rws;

import org.springframework.util.Assert;

public class DatabaseContextHolder {
    private static ThreadLocal<DataSourceEnum> CONTEXT = ThreadLocal.withInitial(() -> DataSourceEnum.ORIGIN);

    public static void set(DataSourceEnum clientDatabase) {
        Assert.notNull(clientDatabase, "clientDatabase cannot be null");
        CONTEXT.set(clientDatabase);
    }

    public static DataSourceEnum getCurrentDataSource() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
