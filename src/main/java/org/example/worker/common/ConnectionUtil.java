package org.example.worker.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public enum ConnectionUtil {
    INSTANCE;

    private HikariDataSource ds;

    ConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://1.255.178.102:13306/workerschedule");
        config.setUsername("workeruser");
        config.setPassword("workeruser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(1000 * 30);
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(1);

        ds = new HikariDataSource(config);
    }


    public HikariDataSource getDs() {
        return ds;
    }

}
