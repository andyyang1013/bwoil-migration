package com.bwoil.c2b.migration.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({OriginDataSourceProperties.class, TargetDataSourceProperties.class})
public class DataSourceConfigure {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfigure.class);

    private final OriginDataSourceProperties originDataSourceProperties;

    private final TargetDataSourceProperties targetDataSourceProperties;

    @Autowired
    public DataSourceConfigure(OriginDataSourceProperties originDataSourceProperties, TargetDataSourceProperties targetDataSourceProperties) {
        this.originDataSourceProperties = originDataSourceProperties;
        this.targetDataSourceProperties = targetDataSourceProperties;
    }

    @Primary
    @Bean(name = "targetDataSource")
    public DataSource targetDataSource() {
        log.info("target data source: [{}]", targetDataSourceProperties.getUrl());
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setPoolName("target data source");
        hikariDataSource.setDriverClassName(targetDataSourceProperties.getDriverClassName());
        hikariDataSource.setJdbcUrl(targetDataSourceProperties.getUrl());
        hikariDataSource.setUsername(targetDataSourceProperties.getUsername());
        hikariDataSource.setPassword(targetDataSourceProperties.getPassword());
        hikariDataSource.setMaximumPoolSize(targetDataSourceProperties.getMaximumPoolSize());
        return hikariDataSource;
    }

    @Bean(name = "originDataSource")
    public DataSource dataSource() {
        log.info("origin data source: [{}]", originDataSourceProperties.getUrl());
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setPoolName("origin data source");
        hikariDataSource.setDriverClassName(originDataSourceProperties.getDriverClassName());
        hikariDataSource.setJdbcUrl(originDataSourceProperties.getUrl());
        hikariDataSource.setUsername(originDataSourceProperties.getUsername());
        hikariDataSource.setPassword(originDataSourceProperties.getPassword());
        hikariDataSource.setMaximumPoolSize(targetDataSourceProperties.getMaximumPoolSize());
        return hikariDataSource;
    }

}
